package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SifreDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Sifre;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("sifraDAO")
public class SifreDAOImpl implements SifreDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void unesiSifru(Sifre sifra) {
		sifra.setVersion(0);
		sifra.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sifra.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(sifra);
	}

	@Override
	public void izmeniSifru(Sifre sifra) {
		sifra.setVersion(sifra.getVersion() + 1);
		sifra.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(sifra);
	}

	@Override
	public void izbrisiSifru(Sifre sifra) {
		sessionFactory.getCurrentSession().delete(sifra);
	}

	@Override
	public Sifre nadjiSifruPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sifre.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Sifre sifra = (Sifre)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return sifra;
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Sifre> nadjiSveSifre(Korisnici korisnik) {
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sifre.class);
			criteria.addOrder(Order.desc("id"));
			ArrayList<Sifre> lista = new ArrayList<>();
			ArrayList<Sifre> lista2 = (ArrayList<Sifre>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(lista2 != null) {
				lista.addAll(lista2);
			}
			return lista;
		}else {
			
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Sifre> nadjiSveSifrePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan) {
		ArrayList<Sifre> lista = new ArrayList<>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sifre.class);
		if(pretplatnik != null) {
			criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		}
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
		}
		if(izbrisan) {
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		criteria.addOrder(Order.desc("id"));
		ArrayList<Sifre> lista2 = (ArrayList<Sifre>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

}

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

import pratiBaza.dao.GrupeDAO;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("grupaDAO")
public class GrupeDAOImpl implements GrupeDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGrupu(Grupe grupa) {
		grupa.setVersion(0);
		grupa.setIzmenjeno(new Timestamp((new Date()).getTime()));
		grupa.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().save(grupa);
	}

	public void azurirajGrupu(Grupe grupa) {
		grupa.setVersion(grupa.getVersion() + 1);
		grupa.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(grupa);
	}

	public void izbrisiGrupu(Grupe grupa) {
		grupa.setAktivan(false);
		grupa.setIzbrisan(true);
		azurirajGrupu(grupa);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Grupe> vratiGrupe(Korisnici korisnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grupe.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
			}
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
		}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Grupe> lista = (ArrayList<Grupe>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	public Grupe nadjiGrupuPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grupe.class);
		criteria.add(Restrictions.eq("id", id));
		Grupe grupa = (Grupe)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return grupa;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Grupe> vratiGrupeAktivne(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grupe.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
			}
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.add(Restrictions.eq("aktivan", true));
		criteria.addOrder(Order.asc("naziv"));
		ArrayList<Grupe> lista = (ArrayList<Grupe>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}
	
}

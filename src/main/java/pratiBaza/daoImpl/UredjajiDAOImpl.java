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
import pratiBaza.dao.UredjajiDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

@Repository("uredjajDAO")
public class UredjajiDAOImpl implements UredjajiDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiUredjaj(Uredjaji uredjaj) {
		uredjaj.setVersion(0);
		uredjaj.setIzmenjeno(new Timestamp((new Date()).getTime()));
		uredjaj.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(uredjaj);
	}

	public void izmeniUredjaj(Uredjaji uredjaj) {
		uredjaj.setVersion(uredjaj.getVersion() + 1);
		uredjaj.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(uredjaj);
	}

	public void izbrisiUredjaj(Uredjaji uredjaj) {
		if(uredjaj.getObjekti() != null) {
			uredjaj.getObjekti().setUredjaji(null);
		}
		if(uredjaj.getSim() != null) {
			uredjaj.getSim().setUredjaji(null);
		}
		if(uredjaj.getSim2() != null) {
			uredjaj.getSim2().setUredjaji(null);
		}
		uredjaj.setObjekti(null);
		uredjaj.setSim(null);
		uredjaj.setSim2(null);
		uredjaj.setAktivno(false);
		uredjaj.setIzbrisan(true);
		izmeniUredjaj(uredjaj);
	}

	public ArrayList<Uredjaji> nadjiSveUredjaje(Korisnici korisnik, boolean aktivan) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uredjaji.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));		
			}
		if(aktivan) {
			criteria.add(Restrictions.eq("aktivan", true));
			criteria.add(Restrictions.eq("izbrisan", false));
			}
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.desc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("objekti"));
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Uredjaji> lista = (ArrayList<Uredjaji>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	public ArrayList<Uredjaji> nadjiSveAktivneSlobodneUredjajePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, Uredjaji uredjaj) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uredjaji.class);
		criteria.add(Restrictions.eq("aktivan", true));
		criteria.add(Restrictions.eq("izbrisan", false));
		if(pretplatnik != null ) {
			criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		}
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija",  organizacija));
		}
		criteria.add(Restrictions.isNull("objekti"));
		criteria.addOrder(Order.asc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Uredjaji> lista = (ArrayList<Uredjaji>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(uredjaj != null) {
			lista.add(uredjaj);
		}
		return lista;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ArrayList<Uredjaji> nadjiSveAktivneUredjaje(Korisnici korisnik, Uredjaji uredjaj) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uredjaji.class);
		criteria.add(Restrictions.eq("aktivan", true));
		criteria.add(Restrictions.eq("izbrisan", false));
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
		}
		criteria.add(Restrictions.isNull("objekti"));
		criteria.addOrder(Order.asc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Uredjaji> lista = (ArrayList<Uredjaji>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(uredjaj != null) {
			lista.add(uredjaj);
		}
		return lista;
	}

	@Override
	public Uredjaji nadjiUredjajPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uredjaji.class);
		criteria.add(Restrictions.eq("id", id));
		Uredjaji uredjaj = (Uredjaji)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return uredjaj;
	}
	
}

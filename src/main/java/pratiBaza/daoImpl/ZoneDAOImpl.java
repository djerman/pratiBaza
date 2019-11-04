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
import pratiBaza.dao.ZoneDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Zone;

@Repository("zonaDAO")
public class ZoneDAOImpl implements ZoneDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiZonu(Zone zona) {
		zona.setVersion(0);
		zona.setIzmenjeno(new Timestamp((new Date()).getTime()));
		zona.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(zona);
	}

	public void izmeniZonu(Zone zona) {
		zona.setVersion(zona.getVersion() + 1);
		zona.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(zona);
	}

	public void izbrisiZonu(Zone zona) {
		zona.setAktivan(false);
		zona.setIzbrisan(true);
		izmeniZonu(zona);
	}

	public ArrayList<Zone> nadjiSveZone(Korisnici korisnik, boolean aktivan) {
		ArrayList<Zone> lista = new ArrayList<Zone>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Zone.class);
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			
		}else {
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
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Zone> lista2 = (ArrayList<Zone>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Zone> nadjiSveZonePoPretplatniku(SistemPretplatnici pretplatnik) {
		ArrayList<Zone> lista = new ArrayList<Zone>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Zone.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		ArrayList<Zone> lista2 = (ArrayList<Zone>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Zone> nadjiSveZonePoOrganizaciji(Organizacije organizacija) {
		ArrayList<Zone> lista = new ArrayList<Zone>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Zone.class);
		criteria.add(Restrictions.eq("organizacija", organizacija));
		ArrayList<Zone> lista2 = (ArrayList<Zone>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Zone nadjiZonuPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Zone.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Zone zona = (Zone)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return zona;
		}else {
			return null;
		}
		
	}

}

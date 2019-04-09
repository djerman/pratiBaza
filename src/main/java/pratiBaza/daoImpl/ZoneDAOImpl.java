package pratiBaza.daoImpl;

import java.util.ArrayList;

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
		sessionFactory.getCurrentSession().persist(zona);
	}

	public void izmeniZonu(Zone zona) {
		sessionFactory.getCurrentSession().update(zona);
	}

	public void izbrisiZonu(Zone zona) {
		zona.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(zona);
	}

	public ArrayList<Zone> nadjiSveZone(Korisnici korisnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Zone.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
		}
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Zone> lista = (ArrayList<Zone>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	public ArrayList<Zone> nadjiSveZonePoPretplatniku(SistemPretplatnici pretplatnik) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Zone> nadjiSveZonePoOrganizaciji(Organizacije organizacija) {
		// TODO Auto-generated method stub
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

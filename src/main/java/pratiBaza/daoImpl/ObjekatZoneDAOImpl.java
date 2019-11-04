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
import pratiBaza.dao.ObjekatZoneDAO;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Zone;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.ObjekatZone;

@Repository("zonaObjekatDAO")
public class ObjekatZoneDAOImpl implements ObjekatZoneDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiZonaObjekat(ObjekatZone zonaObjekat) {
		zonaObjekat.setIzmenjeno(new Timestamp((new Date()).getTime()));
		zonaObjekat.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(zonaObjekat);
	}

	public void izmeniZonaObjekat(ObjekatZone zonaObjekat) {
		zonaObjekat.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(zonaObjekat);
	}

	public void izbrisiZonaObjekat(ObjekatZone zonaObjekat) {
		sessionFactory.getCurrentSession().delete(zonaObjekat);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ObjekatZone> nadjiZoneObjektePoObjektu(Objekti objekat) {
		ArrayList<ObjekatZone> lista = new ArrayList<ObjekatZone>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObjekatZone.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		ArrayList<ObjekatZone> lista2 = (ArrayList<ObjekatZone>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ObjekatZone> nadjiZoneObjektePoZoni(Zone zona) {
		ArrayList<ObjekatZone> lista = new ArrayList<ObjekatZone>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObjekatZone.class);
		criteria.add(Restrictions.eq("zone", zona));
		ArrayList<ObjekatZone> lista2 = (ArrayList<ObjekatZone>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

	@Override
	public void izbrisiZoneObjektiPoObjektu(Objekti objekat) {
		ArrayList<ObjekatZone> lista = nadjiZoneObjektePoObjektu(objekat);
		for(ObjekatZone zonaObjekat : lista) {
			izbrisiZonaObjekat(zonaObjekat);
		}
	}

	@Override
	public void izbrisiZoneObjektiPOZoni(Zone zona) {
		ArrayList<ObjekatZone> lista = nadjiZoneObjektePoZoni(zona);
		for(ObjekatZone zonaObjekat : lista) {
			izbrisiZonaObjekat(zonaObjekat);
		}
	}

	@Override
	public ObjekatZone nadjiObjekatZoniPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObjekatZone.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			ObjekatZone objekatZona = (ObjekatZone)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return objekatZona;
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ObjekatZone> vratiSveObjekatZone(Korisnici korisnik, boolean aktivan) {
		ArrayList<ObjekatZone> lista = new ArrayList<ObjekatZone>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObjekatZone.class);
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
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<ObjekatZone> lista2 = (ArrayList<ObjekatZone>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@Override
	public ArrayList<Zone> nadjiZonePoObjektu(Objekti objekat) {
		ArrayList<Zone> lista = new ArrayList<Zone>();
		for(ObjekatZone objekatZona : nadjiZoneObjektePoObjektu(objekat)) {
			lista.add(objekatZona.getZone());
		}
		return lista;
	}

	@Override
	public ObjekatZone nadjiObjekatZonuPoZoniObjektu(Objekti objekat, Zone zona) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObjekatZone.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.eq("zone", zona));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			ObjekatZone objekatZona = (ObjekatZone)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return objekatZona;
		}else {
			return null;
		}
	}
}

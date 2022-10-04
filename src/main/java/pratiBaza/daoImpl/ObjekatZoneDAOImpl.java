package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
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

	public ArrayList<ObjekatZone> nadjiZoneObjektePoObjektu(Objekti objekat) {
		ArrayList<ObjekatZone> lista = new ArrayList<ObjekatZone>();
		String upit = "SELECT oz FROM ObjekatZone oz WHERE oz.objekti = :objekat";
		TypedQuery<ObjekatZone> query = sessionFactory.getCurrentSession().createQuery(upit, ObjekatZone.class);
		query.setParameter("objekat", objekat);
		if(query.getResultList() != null) {
			lista.addAll(query.getResultList());
		}
		return lista;
	}

	public ArrayList<ObjekatZone> nadjiZoneObjektePoZoni(Zone zona) {
		ArrayList<ObjekatZone> lista = new ArrayList<ObjekatZone>();
		String upit = "SELECT oz FROM ObjekatZone oz WHERE oz.zone = :zona";
		TypedQuery<ObjekatZone> query = sessionFactory.getCurrentSession().createQuery(upit, ObjekatZone.class);
		query.setParameter("zona", zona);
		if(query.getResultList() != null) {
			lista.addAll(query.getResultList());
			}
		return lista;
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
		String upit = "SELECT oz FROM ObjekatZone oz where oz.id = :id";
		TypedQuery<ObjekatZone> query = sessionFactory.getCurrentSession().createQuery(upit, ObjekatZone.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<ObjekatZone> vratiSveObjekatZone(Korisnici korisnik, boolean aktivan) {
		ArrayList<ObjekatZone> lista = new ArrayList<ObjekatZone>();
		String pretp = "";
		String akt = "";
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			pretp = "oz.sistemPretplatnici = :pretplatnik AND oz.izbrisan = false AND ";
		}
		if(aktivan) {
			akt = " AND oz.aktivan = :akt";
		}
		
		String upit = "SELECT oz FROM ObjekatZone oz where " + pretp + "(:organizacija is null or oz.organizacija = :organizacija) "
				+ akt + " ORDER BY oz.sistemPretplatnici.naziv, oz.id, oz.izbrisan, oz.aktivan desc";
		
		TypedQuery<ObjekatZone> query = sessionFactory.getCurrentSession().createQuery(upit, ObjekatZone.class);
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		}
		query.setParameter("organizacija", korisnik.getOrganizacija());
		if(aktivan) {
			query.setParameter("akt", aktivan);
		}
		if(query.getResultList() != null) {
			lista.addAll(query.getResultList());
			}
		return lista;
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
		String upit = "SELECT oz FROM ObjekatZone oz where oz.objekti = :objekat AND oz.zone = :zona ORDER BY oz.id desc";
		TypedQuery<ObjekatZone> query = sessionFactory.getCurrentSession().createQuery(upit, ObjekatZone.class);
		query.setParameter("objekat", objekat);
		query.setParameter("zona", zona);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
}

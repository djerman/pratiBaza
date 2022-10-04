package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
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

	@Override
	public void unesiZonu(Zone zona) {
		zona.setVersion(0);
		zona.setIzmenjeno(new Timestamp((new Date()).getTime()));
		zona.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(zona);
	}

	@Override
	public void izmeniZonu(Zone zona) {
		zona.setVersion(zona.getVersion() + 1);
		zona.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(zona);
	}

	@Override
	public void izbrisiZonu(Zone zona) {
		try {
			sessionFactory.getCurrentSession().delete(zona);
		}catch (Exception e) {
			zona.setAktivan(false);
			zona.setIzbrisan(true);
			izmeniZonu(zona);
		}
	}

	@Override
	public ArrayList<Zone> nadjiSveZone(Korisnici korisnik, boolean aktivan) {
		ArrayList<Zone> lista = new ArrayList<Zone>();
		String upit = "SELECT z FROM Zone z WHERE (:sistem = true OR z.sistemPretplatnici = :pretplatnik)"
				+ " AND (:organizacija IS NULL OR z.organizacija = :organizacija)"
				+ " AND (:aktivan = false OR z.aktivan = true)"
				+ " AND (:aktivan = false OR z.izbrisan = false)"
				+ " ORDER BY z.izbrisan ASC, z.aktivan DESC, z.sistemPretplatnici.naziv ASC, z.id DESC";
		TypedQuery<Zone> query = sessionFactory.getCurrentSession().createQuery(upit, Zone.class);
		query.setParameter("sistem", korisnik.isSistem());
		query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		query.setParameter("organizacija", korisnik.getOrganizacija());
		query.setParameter("aktivan", aktivan);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<Zone> nadjiSveZonePoPretplatniku(SistemPretplatnici pretplatnik) {
		ArrayList<Zone> lista = new ArrayList<Zone>();
		String upit = "SELECT z FROM Zone z WHERE z.sistemPretplatnici = :pretplatnik"
				+ " ORDER BY z.izbrisan ASC, z.aktivan DESC, z.id DESC";
		TypedQuery<Zone> query = sessionFactory.getCurrentSession().createQuery(upit, Zone.class);
		query.setParameter("pretplatnik", pretplatnik);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<Zone> nadjiSveZonePoOrganizaciji(Organizacije organizacija) {
		ArrayList<Zone> lista = new ArrayList<Zone>();
		String upit = "SELECT z FROM Zone z WHERE z.organizacija = :organizacija"
				+ " ORDER BY z.izbrisan ASC, z.aktivan DESC, z.id DESC";
		TypedQuery<Zone> query = sessionFactory.getCurrentSession().createQuery(upit, Zone.class);
		query.setParameter("organizacija", organizacija);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
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
	public Zone nadjiZonuPoId(int id) {
		String upit = "SELECT z FROM Zone z WHERE z.id = :id";
		TypedQuery<Zone> query = sessionFactory.getCurrentSession().createQuery(upit, Zone.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

}

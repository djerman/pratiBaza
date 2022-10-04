package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.VozaciDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Vozaci;

@Repository("vozacDAO")
public class VozaciDAOImpl implements VozaciDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void unesiVozaca(Vozaci vozac) {
		vozac.setVersion(0);
		vozac.setKreirano(new Timestamp((new Date()).getTime()));
		vozac.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(vozac);
	}

	@Override
	public void izmeniVozaca(Vozaci vozac) {
		vozac.setVersion(vozac.getVersion() + 1);
		vozac.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(vozac);
	}

	@Override
	public void izbrisiVozaca(Vozaci vozac) {
		vozac.setIzbrisan(true);
		izmeniVozaca(vozac);
	}

	@Override
	public Vozaci nadjiVozacaPoId(int id) {
		String upit = "SELECT v FROM Vozaci v WHERE v.id = :id";
		TypedQuery<Vozaci> query = sessionFactory.getCurrentSession().createQuery(upit, Vozaci.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
			}catch (Exception e) {
				return null;
				}
		}

	@Override
	public Vozaci nadjiVozacaPoKorisniku(Korisnici korisnik) {
		String upit = "SELECT v FROM Vozaci v WHERE v.korisnici = :korisnik"
				+ " AND v.izbrisan = false"
				+ " ORDER BY v.id DESC";
		TypedQuery<Vozaci> query = sessionFactory.getCurrentSession().createQuery(upit, Vozaci.class);
		query.setParameter("korisnik", korisnik);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
			}catch (Exception e) {
				return null;
				}
	}

	@Override
	public ArrayList<Vozaci> nadjiSveVozacePoKorisniku(Korisnici korisnik) {
		ArrayList<Vozaci> lista = new ArrayList<Vozaci>();
		if(korisnik.isVozac()) {
			String upit = "SELECT v FROM Vozaci v WHERE v.korisnici = :korisnik"
					+ " AND v.izbrisan = false"
					+ " ORDER BY v.id DESC";
			TypedQuery<Vozaci> query = sessionFactory.getCurrentSession().createQuery(upit, Vozaci.class);
			query.setParameter("korisnik", korisnik);
			try {
				lista.addAll(query.getResultList());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public ArrayList<Vozaci> nadjiSveVozace(Korisnici korisnik) {
		ArrayList<Vozaci> lista = new ArrayList<Vozaci>();
		String upit = "SELECT v FROM Vozaci v WHERE (:sistem = true OR v.sistemPretplatnici = :pretplatnik)"
				+ " AND (:organizacija IS NULL OR v.korisnici.organizacija = :organizacija)"
				+ " AND v.izbrisan = false"
				+ " ORDER BY v.izbrisan ASC, v.sistemPretplatnici.naziv ASC, v.id DESC";
		TypedQuery<Vozaci> query = sessionFactory.getCurrentSession().createQuery(upit, Vozaci.class);
		query.setParameter("sistem", korisnik.isSistem());
		query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		query.setParameter("organizacija", korisnik.getOrganizacija());
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
	public ArrayList<Vozaci> nadjiSveVozacePoPretplatniku(SistemPretplatnici pretplatnik) {
		ArrayList<Vozaci> lista = new ArrayList<Vozaci>();
		String upit = "SELECT v FROM Vozaci v WHERE v.sistemPretplatnici = :pretplatnik"
				+ " AND v.izbrisan = false"
				+ " ORDER BY v.id DESC";
		TypedQuery<Vozaci> query = sessionFactory.getCurrentSession().createQuery(upit, Vozaci.class);
		query.setParameter("pretplatnik", pretplatnik);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<Vozaci> nadjiSveVozacePoOrganizaciji(Organizacije organizacija) {
		ArrayList<Vozaci> lista = new ArrayList<Vozaci>();
		String upit = "SELECT v FROM Vozaci v WHERE v.korisnici.organizacija = :organizacija"
				+ " AND v.izbrisan = false"
				+ " ORDER BY v.id DESC";
		TypedQuery<Vozaci> query = sessionFactory.getCurrentSession().createQuery(upit, Vozaci.class);
		query.setParameter("organizacija", organizacija);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}

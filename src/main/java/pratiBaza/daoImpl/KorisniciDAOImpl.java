package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.KorisniciDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("korisnikDAO")
public class KorisniciDAOImpl implements KorisniciDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public void unesiKorisnika(Korisnici korisnik) {
		korisnik.setVersion(0);
		korisnik.setIzmenjeno(new Timestamp((new Date()).getTime()));
		korisnik.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(korisnik);
	}

	public void azurirajKorisnika(Korisnici korisnik) {
		korisnik.setVersion(korisnik.getVersion() + 1);
		korisnik.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(korisnik);
	}

	public void izbrisiKorisnika(Korisnici korisnik) {
		korisnik.setAktivan(false);
		korisnik.setIzbrisan(true);
		azurirajKorisnika(korisnik);
	}

	public Korisnici nadjiKorisnikaPoKorisnickom(String email, String lozinka) {
		String upit = "SELECT k FROM Korisnici k where k.email = :email AND k.lozinka = :lozinka";
		TypedQuery<Korisnici> query = sessionFactory.getCurrentSession().createQuery(upit, Korisnici.class);
		query.setParameter("email", email);
		query.setParameter("lozinka", lozinka);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	public ArrayList<Korisnici> nadjiKorisnikePoPretplatniku(SistemPretplatnici pretplatnik) {
		ArrayList<Korisnici> lista = new ArrayList<Korisnici>();
		String upit = "SELECT k FROM Korisnici k where k.sistemPretplatnici = :pretplatnik AND k.aktivan = true";
		TypedQuery<Korisnici> query = sessionFactory.getCurrentSession().createQuery(upit, Korisnici.class);
		query.setParameter("pretplatnik", pretplatnik);
		if(query.getResultList() != null) {
			lista.addAll(query.getResultList());
		}
		return lista;
	}

	public Korisnici nadjiKorisnikaPoIButton(String iButton) {
		String upit = "SELECT k FROM Korisnici k where k.ibutton = :iButton";
		TypedQuery<Korisnici> query = sessionFactory.getCurrentSession().createQuery(upit, Korisnici.class);
		query.setParameter("iButton", iButton);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ArrayList<Korisnici> nadjiSveKorisnike(Korisnici korisnik, boolean aktivan) {
		ArrayList<Korisnici> lista = new ArrayList<Korisnici>();
		String pretp = "";
		String akt = "";
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			pretp = "k.sistemPretplatnici = :pretplatnik AND k.izbrisan = false AND ";
		}
		if(aktivan) {
			akt = " AND k.aktivan = :akt";
		}
		
		String upit = "Select k FROM Korisnici k where " + pretp + "(:organizacija is null or k.organizacija = :organizacija)"
				+ akt + " ORDER BY k.sistemPretplatnici.naziv, k.id, k.izbrisan, k.aktivan desc";
		
		TypedQuery<Korisnici> query = sessionFactory.getCurrentSession().createQuery(upit, Korisnici.class);
		
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

	public Korisnici nadjiKorisnikaPoId(int id) {
		String upit = "SELECT k FROM Korisnici k where k.id = :id";
		TypedQuery<Korisnici> query = sessionFactory.getCurrentSession().createNamedQuery(upit, Korisnici.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	public ArrayList<Korisnici> nadjiKorisnikeAktivneIzbrisane(boolean aktivan, boolean izbrisan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Korisnici> nadjiKorisnikePoOrganizaciji(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		ArrayList<Korisnici> lista = new ArrayList<Korisnici>();
		String upit = "SELECT k FROM Korisnici k where k.sistemPretplatnici = :pretplatnik AND k.aktivan = true AND (:organizacija IS NULL or k.organizacija = :organizacija)";
		TypedQuery<Korisnici> query = sessionFactory.getCurrentSession().createQuery(upit, Korisnici.class);
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);
		if(query.getResultList() != null) {
			lista.addAll(query.getResultList());
		}
		return lista;
	}

	@Override
	public ArrayList<Korisnici> nadjiSveKorisnikeVozace(Korisnici korisnik, boolean aktivan) {
		ArrayList<Korisnici> lista = new ArrayList<Korisnici>();
		String pretp = "";
		String akt = "";
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			pretp = "k.sistemPretplatnici = :pretplatnik AND k.izbrisan = false AND ";
		}
		if(aktivan) {
			akt = " AND k.aktivan = :akt";
		}
		
		String upit = "Select k FROM Korisnici k where " + pretp + "(:organizacija is null or k.organizacija = :organizacija) "
				+ akt + " AND k.vozac = true ORDER BY k.sistemPretplatnici.naziv, k.id, k.izbrisan, k.aktivan desc";
		
		TypedQuery<Korisnici> query = sessionFactory.getCurrentSession().createQuery(upit, Korisnici.class);
		
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
	public ArrayList<Korisnici> nadjiSveKorisnikeVozace(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean aktivan) {
		ArrayList<Korisnici> lista = new ArrayList<Korisnici>();
		String akt = "";
		if(aktivan) {
			akt = " AND k.aktivan = :akt";
		}
		
		String upit = "Select k FROM Korisnici k where k.sistemPretplatnici = :pretplatnik AND k.izbrisan = false AND (:organizacija is null or k.organizacija = :organizacija) "
				+ akt + " ORDER BY k.sistemPretplatnici.naziv, k.id, k.izbrisan, k.aktivan desc";
		
		TypedQuery<Korisnici> query = sessionFactory.getCurrentSession().createQuery(upit, Korisnici.class);
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);
		if(aktivan) {
			query.setParameter("akt", aktivan);
		}
		if(query.getResultList() != null) {
			lista.addAll(query.getResultList());
			}
		return lista;
	}

}

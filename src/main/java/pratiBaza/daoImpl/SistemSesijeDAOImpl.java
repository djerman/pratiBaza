package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemSesijeDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.SistemSesije;

@Repository("sistemSesijaDAO")
public class SistemSesijeDAOImpl implements SistemSesijeDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiSesiju(SistemSesije sesija) {
		sesija.setVersion(0);
		sessionFactory.getCurrentSession().persist(sesija);
	}

	public void izmeniSesiju(SistemSesije sesija) {
		sesija.setVersion(sesija.getVersion() + 1);
		sessionFactory.getCurrentSession().update(sesija);
	}

	public void izbrisiSesiju(SistemSesije sesija) {
		sesija.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(sesija);
	}

	public ArrayList<SistemSesije> nadjiSveSesije(Korisnici korisnik) {
		ArrayList<SistemSesije> lista = new ArrayList<SistemSesije>();
		String upit = "SELECT s FROM SistemSesije s WHERE (:sistem = true OR s.sistemPretplatnici = :pretplatnik)"
				+ " AND (:sistem = true OR s.izbrisan = false)"
				+ " AND (:organizacija IS NULL OR s.organizacija = :organizacija)"
				+ " ORDER BY s.sistemPretplatnici.naziv ASC, s.izbrisan ASC, s.id DESC";
		TypedQuery<SistemSesije> query = sessionFactory.getCurrentSession().createQuery(upit, SistemSesije.class);
		query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		query.setParameter("sistem", korisnik.isSistem());
		query.setParameter("organizacija", korisnik.getOrganizacija());
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public ArrayList<SistemSesije> nadjiSveSesijeKorisnika(Korisnici korisnik) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<SistemSesije> nadjiSveSesijeKorisnikaPoVremenu(Korisnici korisnik, Timestamp datumVremeOd,
			Timestamp datumVremeDo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<SistemSesije> nadjiSveSesijePretplatnika(SistemPretplatnici pretplatnik) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<SistemSesije> nadjiSveSesijePretplatnikaPoVremenu(SistemPretplatnici pratplatnika,
			Timestamp datumVremeOd, Timestamp datumVremDo) {
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

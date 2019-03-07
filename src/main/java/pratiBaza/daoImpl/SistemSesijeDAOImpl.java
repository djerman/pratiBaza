package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
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
		// TODO Auto-generated method stub
		
	}

	public void izmeniSesiju(SistemSesije sesija) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiSesiju(SistemSesije sesija) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<SistemSesije> nadjiSveSesije() {
		// TODO Auto-generated method stub
		return null;
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

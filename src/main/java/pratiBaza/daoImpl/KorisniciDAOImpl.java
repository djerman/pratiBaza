package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.KorisniciDAO;
import pratiBaza.tabele.Korisnici;

@Repository("korisnikDAO")
public class KorisniciDAOImpl implements KorisniciDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiKorisnika(Korisnici korisnik) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajKorisnika(Korisnici korisnik) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiKorisnika(Korisnici korisnik) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}

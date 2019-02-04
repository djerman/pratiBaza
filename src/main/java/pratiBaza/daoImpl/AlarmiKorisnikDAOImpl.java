package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.AlarmiKorisnikDAO;
import pratiBaza.tabele.AlarmiKorisnik;

@Repository("alarmKorisnikDAO")
public class AlarmiKorisnikDAOImpl implements AlarmiKorisnikDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

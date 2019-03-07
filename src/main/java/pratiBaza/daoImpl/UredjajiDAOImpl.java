package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.UredjajiDAO;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

@Repository("uredjajDAO")
public class UredjajiDAOImpl implements UredjajiDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiUredjaj(Uredjaji uredjaj) {
		// TODO Auto-generated method stub
		
	}

	public void izmeniUredjaj(Uredjaji uredjaj) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiUredjaj(Uredjaji uredjaj) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Uredjaji> nadjiSveUredjaje() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Uredjaji> nadjiSveUredjajePoPretplatniku(SistemPretplatnici pretplatnik) {
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

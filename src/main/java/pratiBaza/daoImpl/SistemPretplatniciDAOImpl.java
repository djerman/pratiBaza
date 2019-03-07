package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.SistemPretplatniciDAO;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("sistemPretplatnikDAO")
public class SistemPretplatniciDAOImpl implements SistemPretplatniciDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiPretplatnika(SistemPretplatnici pretplatnik) {
		// TODO Auto-generated method stub
		
	}

	public void izmeniPretplatnika(SistemPretplatnici pretplatnik) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiPretplatnika(SistemPretplatnici pretplatnik) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<SistemPretplatnici> nadjiSvePretplatnike() {
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

package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.ZoneDAO;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Zone;

@Repository("zonaDAO")
public class ZoneDAOImpl implements ZoneDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiZonu(Zone zona) {
		// TODO Auto-generated method stub
		
	}

	public void izmeniZonu(Zone zona) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiZonu(Zone zona) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Zone> nadjiSveZone() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Zone> nadjiSveZonePoPretplatniku(SistemPretplatnici pretplatnik) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Zone> nadjiSveZonePoOrganizaciji(Organizacije organizacija) {
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

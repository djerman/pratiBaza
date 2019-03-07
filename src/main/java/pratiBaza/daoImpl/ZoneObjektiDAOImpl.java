package pratiBaza.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.ZoneObjektiDAO;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Zone;
import pratiBaza.tabele.ZoneObjekti;

@Repository("zonaObjekatDAO")
public class ZoneObjektiDAOImpl implements ZoneObjektiDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiZonaObjekat(ZoneObjekti zonaObjekat) {
		// TODO Auto-generated method stub
		
	}

	public void izmeniZonaObjekat(ZoneObjekti zonaObjekat) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiZonaObjekat(ZoneObjekti zonaObjekat) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<ZoneObjekti> nadjiZoneObjektePoObjektu(Objekti objekat) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ZoneObjekti> nadjiZoneObjektePoZoni(Zone zona) {
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

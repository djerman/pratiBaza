package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.ObdDAO;
import pratiBaza.tabele.Obd;

@Repository("obdDAO")
public class ObdDAOImpl implements ObdDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiObd(Obd obd) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajObd(Obd obd) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiObd(Obd obd) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

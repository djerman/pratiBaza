package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SimDAO;
import pratiBaza.tabele.Sim;

@Repository("simDAO")
public class SimDAOImpl implements SimDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiSim(Sim sim) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajSim(Sim sim) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiSim(Sim sim) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

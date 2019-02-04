package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.ObjektiDAO;
import pratiBaza.tabele.Objekti;

@Repository("objekatDAO")
public class ObjektiDAOImpl implements ObjektiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiObjekte(Objekti objekat) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajObjekte(Objekti objekat) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiObjekte(Objekti objekat) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

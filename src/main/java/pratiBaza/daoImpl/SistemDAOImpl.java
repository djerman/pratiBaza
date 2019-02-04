package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.SistemDAO;
import pratiBaza.tabele.Sistem;

@Repository("sistemDAO")
public class SistemDAOImpl implements SistemDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiSistem(Sistem sistem) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajSistem(Sistem sistem) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiSistem(Sistem sistem) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

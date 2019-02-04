package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.JavljanjaDAO;
import pratiBaza.tabele.Javljanja;

@Repository("javljanjeDAO")
public class JavljanjaDAOImpl implements JavljanjaDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiJavljanja(Javljanja javljanje) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajJavljanja(Javljanja javljanje) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiJavljanja(Javljanja javljanje) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

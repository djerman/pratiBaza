package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.JavljanjaPoslednjaDAO;
import pratiBaza.tabele.JavljanjaPoslednja;

@Repository("javljanjePoslednjeDAO")
public class JavljanjaPoslednjaDAOImpl implements JavljanjaPoslednjaDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

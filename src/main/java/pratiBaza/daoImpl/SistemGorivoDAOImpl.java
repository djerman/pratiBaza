package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.SistemGorivaDAO;
import pratiBaza.tabele.SistemGoriva;

@Repository("sistemGorivoDAO")
public class SistemGorivoDAOImpl implements SistemGorivaDAO{
	
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGorivo(SistemGoriva gorivo) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajGorivo(SistemGoriva gorivo) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiGorivo(SistemGoriva gorivo) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

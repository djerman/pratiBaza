package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.SistemAlarmiDAO;
import pratiBaza.tabele.SistemAlarmi;

@Repository("sistemAlarmiDAO")
public class SistemAlarmiDAOImpl implements SistemAlarmiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiAlarme(SistemAlarmi alarm) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajAlarme(SistemAlarmi alarm) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiAlarme(SistemAlarmi alarm) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

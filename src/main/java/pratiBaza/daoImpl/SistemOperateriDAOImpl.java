package pratiBaza.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemOperateriDAO;
import pratiBaza.tabele.SistemOperateri;

@Repository("sistemOperaterDAO")
public class SistemOperateriDAOImpl implements SistemOperateriDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiOperatera(SistemOperateri operater) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajOperatera(SistemOperateri operater) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiOperatera(SistemOperateri operater) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<SistemOperateri> nadjiSveOperatere() {
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

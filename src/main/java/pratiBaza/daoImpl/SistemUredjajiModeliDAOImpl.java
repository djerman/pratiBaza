package pratiBaza.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemUredjajiModeliDAO;
import pratiBaza.tabele.SistemUredjajiModeli;

@Repository("sistemUredjajModelDAO")
public class SistemUredjajiModeliDAOImpl implements SistemUredjajiModeliDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiUredjajModel(SistemUredjajiModeli model) {
		// TODO Auto-generated method stub
		
	}

	public void izmeniUredjajModel(SistemUredjajiModeli model) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiUredjajModel(SistemUredjajiModeli model) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<SistemUredjajiModeli> nadjiSveUredjajModele() {
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

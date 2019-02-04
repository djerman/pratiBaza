package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.GrupeObjektiDAO;
import pratiBaza.tabele.GrupeObjekti;

@Repository("grupaObjekatDAO")
public class GrupeObjektiDAOImpl implements GrupeObjektiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGrupaObjekat(GrupeObjekti grupaObjekat) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajGrupaObjekat(GrupeObjekti grupaObjekat) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiGrupaObjekat(GrupeObjekti grupaObjekat) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

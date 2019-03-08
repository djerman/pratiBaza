package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.ObjektiDetaljiDAO;
import pratiBaza.tabele.ObjektiDetalji;

@Repository("objekatDetaljDAO")
public class ObjektiDetaljiDAOImpl implements ObjektiDetaljiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiObjektiDetalji(ObjektiDetalji objekatDetalj) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajObjektiDetalji(ObjektiDetalji objekatDetalj) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiObjektiDetalji(ObjektiDetalji objekatDetalj) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

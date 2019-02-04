package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.GrupeDAO;
import pratiBaza.tabele.Grupe;

@Repository("grupaDAO")
public class GrupeDAOImpl implements GrupeDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGrupu(Grupe grupa) {
		sessionFactory.getCurrentSession().save(grupa);
	}

	public void azurirajGrupu(Grupe grupa) {
		sessionFactory.getCurrentSession().update(grupa);
	}

	public void izbrisiGrupu(Grupe grupa) {
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

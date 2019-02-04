package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.OrganizacijeDAO;
import pratiBaza.tabele.Organizacije;

@Repository("organizacijaDAO")
public class OrganizacijeDAOImpl implements OrganizacijeDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiOrganizacije(Organizacije organizacija) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajOrganizacije(Organizacije organizacija) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiOrganizacije(Organizacije organizacija) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

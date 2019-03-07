package pratiBaza.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemUredjajiProizvodjaciDAO;
import pratiBaza.tabele.SistemUredjajiProizvodjac;

@Repository("sistemUredjajiProizvodjacDAO")
public class SistemUredjajiProizvodjaciDAOImpl implements SistemUredjajiProizvodjaciDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		// TODO Auto-generated method stub
		
	}

	public void izmeniSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<SistemUredjajiProizvodjac> nadjiSveSistemUredjajeProizvodjace() {
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

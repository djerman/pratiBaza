package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemUredjajiProizvodjaciDAO;
import pratiBaza.tabele.SistemUredjajiProizvodjac;

@Repository("sistemUredjajProizvodjacDAO")
public class SistemUredjajiProizvodjaciDAOImpl implements SistemUredjajiProizvodjaciDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		sessionFactory.getCurrentSession().persist(proizvodjac);
	}

	public void izmeniSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		sessionFactory.getCurrentSession().update(proizvodjac);
	}

	public void izbrisiSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		proizvodjac.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(proizvodjac);
	}

	public ArrayList<SistemUredjajiProizvodjac> nadjiSveSistemUredjajeProizvodjace() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemUredjajiProizvodjac.class);
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<SistemUredjajiProizvodjac> lista = (ArrayList<SistemUredjajiProizvodjac>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

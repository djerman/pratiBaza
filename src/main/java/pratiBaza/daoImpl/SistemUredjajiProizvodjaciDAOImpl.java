package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
		proizvodjac.setVersion(0);
		sessionFactory.getCurrentSession().persist(proizvodjac);
	}

	public void izmeniSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		proizvodjac.setVersion(proizvodjac.getVersion() + 1);
		sessionFactory.getCurrentSession().update(proizvodjac);
	}

	public void izbrisiSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		proizvodjac.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(proizvodjac);
	}

	public ArrayList<SistemUredjajiProizvodjac> nadjiSveSistemUredjajeProizvodjace() {
		ArrayList<SistemUredjajiProizvodjac> lista = new ArrayList<SistemUredjajiProizvodjac>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemUredjajiProizvodjac.class);
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<SistemUredjajiProizvodjac> lista2 = (ArrayList<SistemUredjajiProizvodjac>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SistemUredjajiProizvodjac nadjiProizvodjacaPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemUredjajiProizvodjac.class);
		criteria.add(Restrictions.eq("id", id));
		SistemUredjajiProizvodjac proizvodjac = (SistemUredjajiProizvodjac)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return proizvodjac;
	}
	
}

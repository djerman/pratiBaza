package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
		model.setVersion(0);
		sessionFactory.getCurrentSession().persist(model);
	}

	public void izmeniUredjajModel(SistemUredjajiModeli model) {
		model.setVersion(model.getVersion() + 1);
		sessionFactory.getCurrentSession().update(model);
	}

	public void izbrisiUredjajModel(SistemUredjajiModeli model) {
		model.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(model);
	}

	public ArrayList<SistemUredjajiModeli> nadjiSveUredjajModele() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemUredjajiModeli.class);
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<SistemUredjajiModeli> lista = (ArrayList<SistemUredjajiModeli>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SistemUredjajiModeli nadjiModelPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemUredjajiModeli.class);
		criteria.add(Restrictions.eq("id", id));
		SistemUredjajiModeli model = (SistemUredjajiModeli)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return model;
	}
	
}

package pratiBaza.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.SistemDAO;
import pratiBaza.tabele.Sistem;

@Repository("sistemDAO")
public class SistemDAOImpl implements SistemDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiSistem(Sistem sistem) {
		sessionFactory.getCurrentSession().persist(sistem);
	}

	public void azurirajSistem(Sistem sistem) {
		sessionFactory.getCurrentSession().update(sistem);
	}

	public void izbrisiSistem(Sistem sistem) {
		sessionFactory.getCurrentSession().delete(sistem);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Sistem vratiSistem() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sistem.class);
		criteria.addOrder(Order.asc("id"));
		criteria.setMaxResults(1);
		Sistem rezultat = (Sistem)criteria.uniqueResult();
		return rezultat;
	}

	public Sistem nadjiSistemPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sistem.class);
		criteria.add(Restrictions.eq("id", id));
		Sistem sistem = (Sistem)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return sistem;
	}
	
}

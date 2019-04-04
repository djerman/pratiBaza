package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.SistemPretplatniciDAO;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("sistemPretplatnikDAO")
public class SistemPretplatniciDAOImpl implements SistemPretplatniciDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiPretplatnika(SistemPretplatnici pretplatnik) {
		sessionFactory.getCurrentSession().persist(pretplatnik);
	}

	public void izmeniPretplatnika(SistemPretplatnici pretplatnik) {
		sessionFactory.getCurrentSession().update(pretplatnik);
	}

	public void izbrisiPretplatnika(SistemPretplatnici pretplatnik) {
		pretplatnik.setIzbrisan(true);
	}

	public ArrayList<SistemPretplatnici> nadjiSvePretplatnike() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemPretplatnici.class);
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<SistemPretplatnici> lista = (ArrayList<SistemPretplatnici>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

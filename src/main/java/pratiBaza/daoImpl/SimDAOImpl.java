package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SimDAO;
import pratiBaza.tabele.Sim;

@Repository("simDAO")
public class SimDAOImpl implements SimDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiSim(Sim sim) {
		sessionFactory.getCurrentSession().persist(sim);
	}

	public void azurirajSim(Sim sim) {
		sessionFactory.getCurrentSession().update(sim);
	}

	public void izbrisiSim(Sim sim) {
		sim.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(sim);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Sim> vratiSveSimKartice() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sim.class);
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivno"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Sim> lista = (ArrayList<Sim>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}
	
}

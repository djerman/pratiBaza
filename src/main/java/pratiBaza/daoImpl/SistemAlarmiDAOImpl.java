package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.SistemAlarmiDAO;
import pratiBaza.tabele.SistemAlarmi;

@Repository("sistemAlarmiDAO")
public class SistemAlarmiDAOImpl implements SistemAlarmiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiAlarme(SistemAlarmi alarm) {
		sessionFactory.getCurrentSession().persist(alarm);
	}

	public void azurirajAlarme(SistemAlarmi alarm) {
		sessionFactory.getCurrentSession().update(alarm);
	}

	public void izbrisiAlarme(SistemAlarmi alarm) {
		alarm.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(alarm);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ArrayList<SistemAlarmi> vratiSveAlarme() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemAlarmi.class);
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<SistemAlarmi> lista = (ArrayList<SistemAlarmi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}
	
}

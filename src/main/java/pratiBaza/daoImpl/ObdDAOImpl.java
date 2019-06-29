package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.ObdDAO;
import pratiBaza.tabele.Obd;
import pratiBaza.tabele.Objekti;

@Repository("obdDAO")
public class ObdDAOImpl implements ObdDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiObd(Obd obd) {
		sessionFactory.getCurrentSession().persist(obd);
	}

	public void azurirajObd(Obd obd) {
		sessionFactory.getCurrentSession().update(obd);
	}

	public void izbrisiObd(Obd obd) {
		sessionFactory.getCurrentSession().delete(obd);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Obd nadjiObdPoslednji(Objekti objekat) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Obd.class);
		criteria.add(Restrictions.eq("objekti", objekat)).addOrder(Order.desc("id")).setMaxResults(1);
		return (Obd) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Obd> nadjiObdPoObjektuOdDo(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Obd.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.ge("datumVreme", datumVremeOd));
		criteria.add(Restrictions.lt("datumVreme", datumVremeDo));
		criteria.addOrder(Order.asc("datumVreme"));
		 ArrayList<Obd> obd = (ArrayList<Obd>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return obd;
	}

	@Override
	public ArrayList<Obd> nadjiObdPoObjektuOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		ArrayList<Obd> lista = new ArrayList<Obd>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Obd.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.ge("datumVreme", datumVremeOd));
		criteria.add(Restrictions.lt("datumVreme", datumVremeDo));
		criteria.addOrder(Order.asc("datumVreme"));
		criteria.setMaxResults(1);
		if(criteria.uniqueResult() != null) {
			lista.add((Obd)criteria.uniqueResult());
		}
		Criteria criteria2 = sessionFactory.getCurrentSession().createCriteria(Obd.class);
		criteria2.add(Restrictions.eq("objekti", objekat));
		criteria2.add(Restrictions.ge("datumVreme", datumVremeOd));
		criteria2.add(Restrictions.lt("datumVreme", datumVremeDo));
		criteria2.addOrder(Order.desc("datumVreme"));
		criteria2.setMaxResults(1);
		if(criteria.uniqueResult() != null) {
			lista.add((Obd)criteria2.uniqueResult());
		}
		return lista;
	}
	
}

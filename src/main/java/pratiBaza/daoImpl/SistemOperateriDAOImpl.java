package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemOperateriDAO;
import pratiBaza.tabele.SistemOperateri;

@Repository("sistemOperaterDAO")
public class SistemOperateriDAOImpl implements SistemOperateriDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiOperatera(SistemOperateri operater) {
		operater.setVersion(0);
		sessionFactory.getCurrentSession().persist(operater);
	}

	public void azurirajOperatera(SistemOperateri operater) {
		operater.setVersion(operater.getVersion() + 1);
		sessionFactory.getCurrentSession().update(operater);
	}

	public void izbrisiOperatera(SistemOperateri operater) {
		operater.setIzbrisan(true);
		operater.setVersion(operater.getVersion() + 1);
		sessionFactory.getCurrentSession().update(operater);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SistemOperateri> nadjiSveOperatere() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemOperateri.class);
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<SistemOperateri> lista = (ArrayList<SistemOperateri>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SistemOperateri nadjiOperateraPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemOperateri.class);
		criteria.add(Restrictions.eq("id", id));
		SistemOperateri operater = (SistemOperateri)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return operater;
	}
	
}

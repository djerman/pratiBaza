package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.SistemGorivaDAO;
import pratiBaza.tabele.SistemGoriva;

@Repository("sistemGorivoDAO")
public class SistemGorivoDAOImpl implements SistemGorivaDAO{
	
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGorivo(SistemGoriva gorivo) {
		gorivo.setVersion(0);
		sessionFactory.getCurrentSession().persist(gorivo);
	}

	public void azurirajGorivo(SistemGoriva gorivo) {
		gorivo.setVersion(gorivo.getVersion() + 1);
		sessionFactory.getCurrentSession().update(gorivo);
	}

	public void izbrisiGorivo(SistemGoriva gorivo) {
		gorivo.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(gorivo);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SistemGoriva> vratiSvaGoriva(boolean izbrisan) {
		ArrayList<SistemGoriva> lista = new ArrayList<SistemGoriva>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemGoriva.class);
		if(!izbrisan) {
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<SistemGoriva> lista2 = (ArrayList<SistemGoriva>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	public SistemGoriva nadjiGorivoPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemGoriva.class);
		criteria.add(Restrictions.eq("id", id));
		SistemGoriva gorivo = (SistemGoriva)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return gorivo;
	}
	
}

package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
		sessionFactory.getCurrentSession().persist(gorivo);
	}

	public void azurirajGorivo(SistemGoriva gorivo) {
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
	public ArrayList<SistemGoriva> vratiSvaGoriva() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemGoriva.class);
		criteria.addOrder(Order.asc("id"));
		ArrayList<SistemGoriva> lista = (ArrayList<SistemGoriva>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}
	
}

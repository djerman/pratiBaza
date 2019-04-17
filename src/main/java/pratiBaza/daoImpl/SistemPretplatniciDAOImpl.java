package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
		pretplatnik.setKreirano(new Timestamp((new Date()).getTime()));
		pretplatnik.setIzmenjeno(new Timestamp((new Date()).getTime()));
		pretplatnik.setVersion(0);
		sessionFactory.getCurrentSession().persist(pretplatnik);
	}

	public void izmeniPretplatnika(SistemPretplatnici pretplatnik) {
		pretplatnik.setIzmenjeno(new Timestamp((new Date()).getTime()));
		pretplatnik.setVersion(pretplatnik.getVersion() + 1);
		sessionFactory.getCurrentSession().update(pretplatnik);
	}

	public void izbrisiPretplatnika(SistemPretplatnici pretplatnik) {
		pretplatnik.setIzbrisan(true);
		pretplatnik.setVersion(pretplatnik.getVersion() + 1);
		sessionFactory.getCurrentSession().update(pretplatnik);
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

	public SistemPretplatnici nadjiPretplatnikaPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemPretplatnici.class);
		criteria.add(Restrictions.eq("id", id));
		SistemPretplatnici pretplatnik = (SistemPretplatnici)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return pretplatnik;
	}
	
}

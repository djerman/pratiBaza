package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
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
		pretplatnik.setAktivan(false);
		pretplatnik.setIzbrisan(true);
		pretplatnik.setVersion(pretplatnik.getVersion() + 1);
		sessionFactory.getCurrentSession().update(pretplatnik);
	}

	public ArrayList<SistemPretplatnici> nadjiSvePretplatnike() {
		ArrayList<SistemPretplatnici> lista = new ArrayList<SistemPretplatnici>();
		String upit = "SELECT p FROM SistemPretplatnici p ORDER BY p.izbrisan ASC, p.aktivan DESC, p.id DESC";
		TypedQuery<SistemPretplatnici> query = sessionFactory.getCurrentSession().createQuery(upit, SistemPretplatnici.class);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemPretplatnici.class);
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<SistemPretplatnici> lista2 = (ArrayList<SistemPretplatnici>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}*/
	}
	
	public ArrayList<SistemPretplatnici> nadjiSveAktivnePretplatnike() {
		ArrayList<SistemPretplatnici> lista = new ArrayList<SistemPretplatnici>();
		String upit = "SELECT p FROM SistemPretplatnici p WHERE p.izbrisan = false"
				+ " AND p.aktivan = true"
				+ " ORDER BY p.naziv ASC";
		TypedQuery<SistemPretplatnici> query = sessionFactory.getCurrentSession().createQuery(upit, SistemPretplatnici.class);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemPretplatnici.class);
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.add(Restrictions.eq("aktivan", true));
		criteria.addOrder(Order.asc("naziv"));
		@SuppressWarnings("unchecked")
		ArrayList<SistemPretplatnici> lista2 = (ArrayList<SistemPretplatnici>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}*/
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SistemPretplatnici nadjiPretplatnikaPoId(int id) {
		String upit = "SELECT p FROM SistemPretplatnici p WHERE p.id = :id";
		TypedQuery<SistemPretplatnici> query = sessionFactory.getCurrentSession().createQuery(upit, SistemPretplatnici.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<SistemPretplatnici> nadjiSveAktivneSistemskePretplatnike() {
		ArrayList<SistemPretplatnici> lista = new ArrayList<SistemPretplatnici>();
		String upit = "SELECT p FROM SistemPretplatnici p WHERE p.izbrisan = false"
				+ " AND p.aktivan = true"
				+ " AND p.sistem = true"
				+ " ORDER BY p.naziv ASC";
		TypedQuery<SistemPretplatnici> query = sessionFactory.getCurrentSession().createQuery(upit, SistemPretplatnici.class);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemPretplatnici.class);
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.add(Restrictions.eq("aktivan", true));
		criteria.add(Restrictions.eq("sistem", true));
		criteria.addOrder(Order.asc("naziv"));
		@SuppressWarnings("unchecked")
		ArrayList<SistemPretplatnici> lista2 = (ArrayList<SistemPretplatnici>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}*/
	}
	
}

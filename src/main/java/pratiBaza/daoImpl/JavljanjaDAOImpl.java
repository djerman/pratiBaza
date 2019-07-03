package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.JavljanjaDAO;
import pratiBaza.tabele.Javljanja;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemAlarmi;

@Repository("javljanjeDAO")
public class JavljanjaDAOImpl implements JavljanjaDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiJavljanja(Javljanja javljanje) {
		sessionFactory.getCurrentSession().persist(javljanje);
	}

	public void azurirajJavljanja(Javljanja javljanje) {
		sessionFactory.getCurrentSession().update(javljanje);
	}

	public void izbrisiJavljanja(Javljanja javljanje) {
		sessionFactory.getCurrentSession().delete(javljanje);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Javljanja nadjiPoslednjeJavljanjePoObjektu(Objekti objekat) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat)).addOrder(Order.desc("id")).setMaxResults(1);
		return (Javljanja) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDo(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.ge("datumVreme", vremeOd));
		criteria.add(Restrictions.lt("datumVreme", vremeDo));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("datumVreme"));
		 ArrayList<Javljanja> javljanja = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return javljanja;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmima(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.ge("datumVreme", vremeOd));
		criteria.add(Restrictions.lt("datumVreme", vremeDo));
		criteria.add(Restrictions.eq("valid", true));
		criteria.createAlias("sistemAlarmi", "alarmi").add(Restrictions.ne("alarmi.sifra","0"));
		criteria.addOrder(Order.asc("datumVreme"));
		 ArrayList<Javljanja> javljanja = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return javljanja;
	}

	@Override
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		ArrayList<Javljanja> lista = new ArrayList<Javljanja>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.ge("datumVreme", datumVremeOd));
		criteria.add(Restrictions.lt("datumVreme", datumVremeDo));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("datumVreme"));
		criteria.setMaxResults(1);
		if(criteria.uniqueResult() != null) {
			lista.add((Javljanja)criteria.uniqueResult());
		}
		Criteria criteria2 = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria2.add(Restrictions.eq("objekti", objekat));
		criteria2.add(Restrictions.ge("datumVreme", datumVremeOd));
		criteria2.add(Restrictions.lt("datumVreme", datumVremeDo));
		criteria2.add(Restrictions.eq("valid", true));
		criteria2.addOrder(Order.desc("datumVreme"));
		criteria2.setMaxResults(1);
		if(criteria2.uniqueResult() != null){
			lista.add((Javljanja)criteria2.uniqueResult());
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmimaZona(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo,  ArrayList<SistemAlarmi> alarmi) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.in("sistemAlarmi", alarmi));
		criteria.add(Restrictions.ge("datumVreme", vremeOd));
		criteria.add(Restrictions.lt("datumVreme", vremeDo));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("datumVreme"));
		ArrayList<Javljanja> javljanja = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return javljanja;
	}
}

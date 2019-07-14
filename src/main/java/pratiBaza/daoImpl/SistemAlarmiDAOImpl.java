package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
		alarm.setVersion(0);
		sessionFactory.getCurrentSession().persist(alarm);
	}

	public void azurirajAlarme(SistemAlarmi alarm) {
		alarm.setVersion(alarm.getVersion() + 1);
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

	@SuppressWarnings("unchecked")
	public ArrayList<SistemAlarmi> vratiSveAlarme() {
		ArrayList<SistemAlarmi> lista = new ArrayList<SistemAlarmi>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemAlarmi.class);
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<SistemAlarmi> lista2 = (ArrayList<SistemAlarmi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	public SistemAlarmi nadjiAlaramPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemAlarmi.class);
		criteria.add(Restrictions.eq("id", id));
		SistemAlarmi alarm = (SistemAlarmi)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return alarm;
	}

	@Override
	public SistemAlarmi nadjiAlarmPoSifri(String sifra) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemAlarmi.class);
		criteria.add(Restrictions.eq("sifra", sifra));
		SistemAlarmi alarm = (SistemAlarmi)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return alarm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SistemAlarmi> vratiAlarmePoZahtevu(boolean aktivan, boolean email, boolean pregled) {
		ArrayList<SistemAlarmi> lista = new ArrayList<SistemAlarmi>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemAlarmi.class);
		if(aktivan) {
			criteria.add(Restrictions.eq("aktivan", aktivan));
		}
		if(email) {
			criteria.add(Restrictions.eq("email", email));
		}
		if(pregled)
			criteria.add(Restrictions.eq("pregled", pregled));
		ArrayList<SistemAlarmi> lista2 = (ArrayList<SistemAlarmi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SistemAlarmi> vratiAlarmeZaPregled(boolean aktivan, boolean pregled) {
		ArrayList<SistemAlarmi> lista = new ArrayList<SistemAlarmi>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemAlarmi.class);
		if(aktivan) {
			criteria.add(Restrictions.eq("aktivan", aktivan));
		}
		if(pregled) {
			criteria.add(Restrictions.eq("pregled", pregled));
		}
		ArrayList<SistemAlarmi> lista2 = (ArrayList<SistemAlarmi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}
	
}

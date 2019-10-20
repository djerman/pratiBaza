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
import pratiBaza.pomocne.PredjeniPutOBD;
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
	public Obd nadjiObdPoslednji(Objekti objekat, Timestamp datumVreme) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Obd.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		if(datumVreme != null) {
			criteria.add(Restrictions.lt("datumVreme", datumVreme));
		}
		criteria.addOrder(Order.desc("id")).setMaxResults(1);
		return (Obd) criteria.uniqueResult();
	}

	@Override
	public ArrayList<Obd> nadjiObdPoslednji(ArrayList<Objekti> objekti, Timestamp datumVreme) {
		ArrayList<Obd> lista = new ArrayList<Obd>();
		for(Objekti objekat: objekti) {
			Obd obd = nadjiObdPoslednji(objekat, datumVreme);
			if(obd != null) {
				lista.add(obd);
				}
			}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Obd> nadjiObdPoObjektuOdDo(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		ArrayList<Obd> lista = new ArrayList<Obd>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Obd.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.ge("datumVreme", datumVremeOd));
		criteria.add(Restrictions.lt("datumVreme", datumVremeDo));
		criteria.addOrder(Order.asc("datumVreme"));
		ArrayList<Obd> lista2 = (ArrayList<Obd>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
			}else {
				return lista;
				}
		}

	@Override
	public ArrayList<Obd> nadjiObdPoObjektuOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		ArrayList<Obd> lista = new ArrayList<Obd>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Obd.class)
				.add(Restrictions.eq("objekti", objekat))
				.add(Restrictions.ge("datumVreme", datumVremeOd))
				.add(Restrictions.lt("datumVreme", datumVremeDo))
				.addOrder(Order.asc("datumVreme"))
				.setMaxResults(1);
		if(criteria.uniqueResult() != null) {
			lista.add((Obd)criteria.uniqueResult());
		}
		Criteria criteria2 = sessionFactory.getCurrentSession().createCriteria(Obd.class)
				.add(Restrictions.eq("objekti", objekat))
				.add(Restrictions.ge("datumVreme", datumVremeOd))
				.add(Restrictions.lt("datumVreme", datumVremeDo))
				.addOrder(Order.desc("datumVreme"))
				.setMaxResults(1);
		if(criteria.uniqueResult() != null) {
			lista.add((Obd)criteria2.uniqueResult());
		}
		return lista;
	}
	
	@Override
	public ArrayList<PredjeniPutOBD> nadjiPredjeniPutOBD(ArrayList<Objekti> objekti, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		ArrayList<PredjeniPutOBD> lista = new ArrayList<PredjeniPutOBD>();
		for(Objekti objekat: objekti) {
			ArrayList<Obd> obd = nadjiObdPoObjektuOdDoPrvoPoslednje(objekat, datumVremeOd, datumVremeDo);
			if( obd != null && !obd.isEmpty() && obd.size() > 1) {
				int pocetakKm = obd.get(0).getUkupnoKm();
				int krajKm = obd.get(1).getUkupnoKm();
				int razlikaKm = krajKm - pocetakKm;
				float pocetakGorivo = obd.get(0).getUkupnoGorivo();
				float krajGorivo = obd.get(1).getUkupnoGorivo();
				float razlikaGorivo = krajGorivo - pocetakGorivo;
				float prosPotrosnja = 0.0f;
				if(razlikaKm != 0) {
					prosPotrosnja = razlikaGorivo*100/razlikaKm;
				}
				
				PredjeniPutOBD put = new PredjeniPutOBD(objekat.getOznaka(), pocetakKm, krajKm, razlikaKm,pocetakGorivo, krajGorivo, razlikaGorivo, prosPotrosnja);
				lista.add(put);
			}
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Obd> nadjiObdPoslednjaStajanja(Objekti objekat, Timestamp datumVremeOd) {
		ArrayList<Obd> lista = new ArrayList<Obd>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Obd.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.gt("datumVreme", datumVremeOd));
		criteria.addOrder(Order.asc("datumVreme"));
		ArrayList<Obd> lista2 = (ArrayList<Obd>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
			}else {
				return lista;
				}
	}

}

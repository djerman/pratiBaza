package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.JavljanjaDAO;
import pratiBaza.pomocne.PredjeniPut;
import pratiBaza.pomocne.PredjeniPutGPS;
import pratiBaza.pomocne.StajanjeMirovanje;
import pratiBaza.tabele.Javljanja;
import pratiBaza.tabele.Obd;
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
		criteria.add(Restrictions.eq("objekti", objekat)).addOrder(Order.desc("datumVreme")).setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (Javljanja) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDo(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		ArrayList<Javljanja> javljanja = new ArrayList<Javljanja>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.ge("datumVreme", vremeOd));
		criteria.add(Restrictions.lt("datumVreme", vremeDo));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("datumVreme"));
		ArrayList<Javljanja> javljanja2 = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		if(javljanja2 != null) {
			return javljanja2;
		}else {
			return javljanja;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmima(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		ArrayList<Javljanja> javljanja = new ArrayList<Javljanja>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.ge("datumVreme", vremeOd));
		criteria.add(Restrictions.lt("datumVreme", vremeDo));
		criteria.add(Restrictions.eq("valid", true));
		criteria.createAlias("sistemAlarmi", "alarmi").add(Restrictions.ne("alarmi.sifra","0"));
		criteria.addOrder(Order.asc("datumVreme"));
		ArrayList<Javljanja> javljanja2 = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		if(javljanja2 != null) {
			return javljanja2;
		}else {
			return javljanja;
		}
	}

	@Override
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		ArrayList<Javljanja> lista = new ArrayList<Javljanja>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class)
				.add(Restrictions.eq("objekti", objekat))
				.add(Restrictions.ge("datumVreme", datumVremeOd))
				.add(Restrictions.lt("datumVreme", datumVremeDo))
				.add(Restrictions.eq("valid", true))
				.addOrder(Order.asc("datumVreme"))
				.setMaxResults(1);
		if(criteria.uniqueResult() != null) {
			lista.add((Javljanja)criteria.uniqueResult());
		}
		
		Criteria criteria2 = sessionFactory.getCurrentSession().createCriteria(Javljanja.class)
				.add(Restrictions.eq("objekti", objekat))
				.add(Restrictions.ge("datumVreme", datumVremeOd))
				.add(Restrictions.lt("datumVreme", datumVremeDo))
				.add(Restrictions.eq("valid", true))
				.addOrder(Order.desc("datumVreme"))
				.setMaxResults(1);
		if(criteria2.uniqueResult() != null){
			lista.add((Javljanja)criteria2.uniqueResult());
		}
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		return lista;
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
		if(criteria2.uniqueResult() != null){
			lista.add((Obd)criteria2.uniqueResult());
		}
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		return lista;
	}
	

	@Override
	public ArrayList<PredjeniPutGPS> nadjiPredjeniPutGPS(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo) {
		ArrayList<PredjeniPutGPS> lista = new ArrayList<PredjeniPutGPS>();
		for(Objekti objekat: objekti) {
			ArrayList<Javljanja> javljanja = vratiJavljanjaObjektaOdDoPrvoPoslednje(objekat, vremeOd, vremeDo);
			if(javljanja != null && !javljanja.isEmpty() && javljanja.size() > 1) {
				PredjeniPutGPS put = new PredjeniPutGPS(objekat.getOznaka(), javljanja.get(0).getVirtualOdo(), javljanja.get(1).getVirtualOdo(), 
						javljanja.get(1).getVirtualOdo() - javljanja.get(0).getVirtualOdo());
				lista.add(put);
			}
		}
		return lista;
	}
	
	//obračun po projections
	@Override
	public ArrayList<PredjeniPut> nadjiPredjeniPut(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo) {
		ArrayList<PredjeniPut> lista = new ArrayList<PredjeniPut>();
		for(Objekti objekat : objekti) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
			criteria.add(Restrictions.eq("objekti", objekat));
			criteria.add(Restrictions.ge("datumVreme", vremeOd));
			criteria.add(Restrictions.lt("datumVreme", vremeDo));
			criteria.add(Restrictions.eq("valid", true));
			if(!criteria.list().isEmpty()) {
				PredjeniPut put = new PredjeniPut(objekat.getOznaka(), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
				criteria.setProjection(Projections.min("virtualOdo"));
				Float gpsMin = (Float)criteria.uniqueResult();
				criteria.setProjection(Projections.max("virtualOdo"));
				Float gpsMax = (Float)criteria.uniqueResult();
				put.setVirtualOdo(gpsMax.floatValue() - gpsMin.floatValue());
				
				Criteria criteriaObd = sessionFactory.getCurrentSession().createCriteria(Obd.class);
				criteriaObd.add(Restrictions.eq("objekti", objekat));
				criteriaObd.add(Restrictions.ge("datumVreme", vremeOd));
				criteriaObd.add(Restrictions.lt("datumVreme", vremeDo));
				System.out.println(objekat.getOznaka() + " " + criteriaObd.list().size());
				if(!criteriaObd.list().isEmpty()) {
					criteriaObd.setProjection(Projections.min("ukupnoGorivo"));
					Float gorivoMin = (Float)criteria.uniqueResult();
					criteriaObd.setProjection(Projections.max("ukupnoGorivo"));
					Float gorivoMax = (Float)criteria.uniqueResult();
					put.setUkupnoGorivo(gorivoMax.floatValue() - gorivoMin.floatValue());
					
					criteriaObd.setProjection(Projections.min("ukupnoKm"));
					Integer kmMin = (Integer)criteria.uniqueResult();
					criteriaObd.setProjection(Projections.max("ukupnoKm"));
					Integer kmMax = (Integer)criteria.uniqueResult();
					put.setUkupnoKm(kmMax.intValue() - kmMin.intValue());
					
					if(put.getVirtualOdo() != 0.0f) {
						put.setProsPotGps(put.getUkupnoGorivo()/put.getVirtualOdo()/100);
					}
					if(put.getUkupnoKm() != 0.0f) {
						put.setProsPotr(put.getUkupnoGorivo()/put.getUkupnoKm()/100);
					}
				}
				lista.add(put);
			}
			
		}
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmimaZona(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo,  ArrayList<SistemAlarmi> alarmi) {
		ArrayList<Javljanja> javljanja = new ArrayList<Javljanja>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.in("sistemAlarmi", alarmi));
		criteria.add(Restrictions.ge("datumVreme", vremeOd));
		criteria.add(Restrictions.lt("datumVreme", vremeDo));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("datumVreme"));
		ArrayList<Javljanja> javljanja2 = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		if(javljanja2 != null) {
			return javljanja2;
		}else {
			return javljanja;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Javljanja> vratiJavljanjaObjekataOdDoSaAlarmima(ArrayList<Objekti> objekti, Timestamp vremeOd,Timestamp vremeDo, boolean pregled) {
		ArrayList<Javljanja> javljanja = new ArrayList<Javljanja>();
		for(Objekti objekat : objekti) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
			criteria.add(Restrictions.eq("objekti", objekat));
			criteria.add(Restrictions.ge("datumVreme", vremeOd));
			criteria.add(Restrictions.lt("datumVreme", vremeDo));
			criteria.add(Restrictions.eq("valid", true));
			criteria.createAlias("sistemAlarmi", "alarm").add(Restrictions.ne("alarm.sifra","0"));
			if(pregled) {
				criteria.add(Restrictions.ne("alarm.pregled", true));
			}
			criteria.addOrder(Order.asc("datumVreme"));
			ArrayList<Javljanja> javljanja2 = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			javljanja.addAll(javljanja2);
		}
		javljanja.sort(Comparator.comparing(Javljanja::getDatumVreme).reversed());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		return javljanja;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Javljanja> vratiJavljanjaObjekataOdDoSaBrzinama(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo) {
		ArrayList<Javljanja> javljanja = new ArrayList<Javljanja>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.in("objekti", objekti));
		criteria.add(Restrictions.ge("datumVreme", vremeOd));
		criteria.add(Restrictions.lt("datumVreme", vremeDo));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.desc("brzina"));
		criteria.setMaxResults(10);
		ArrayList<Javljanja> javljanja2 = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		if(javljanja2 != null) {
			return javljanja2;
		}else {
			return javljanja;
		}
	}

	@Override
	public Javljanja vratiJavljanjePoslednjeObjektaDo(Objekti objekat, Timestamp vremeDo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		//criteria.add(Restrictions.lt("datumVreme", vremeDo));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.desc("datumVreme"));
		criteria.setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (Javljanja) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Javljanja> vratiJavljanjaZaStajanja(Objekti objekat) {
		//ArrayList<Javljanja> javljanja = new ArrayList<Javljanja>();
		Disjunction disjunction = Restrictions.disjunction();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.desc("datumVreme"));
		//for(Iterator iterator = integerArray.iterator; iterator.hasNext())
		ArrayList<Javljanja> javljanja2 = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		for(Iterator<Javljanja> iterator = javljanja2.iterator(); iterator.hasNext();) {
			disjunction.add(Restrictions.lt("brzina", 6));
		}
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		return javljanja2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<StajanjeMirovanje> vratiStajanjaMirovanja(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, int duzina) {
		ArrayList<StajanjeMirovanje> lista = new ArrayList<StajanjeMirovanje>();
		for(Objekti objekat: objekti) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
			criteria.add(Restrictions.eq("objekti", objekat));
			criteria.add(Restrictions.ge("datumVreme", vremeOd));
			criteria.add(Restrictions.lt("datumVreme", vremeDo));
			criteria.add(Restrictions.eq("valid", true));
			criteria.addOrder(Order.asc("datumVreme"));
			ArrayList<Javljanja> javljanja = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(javljanja != null && !javljanja.isEmpty() && javljanja.size() > 2) {
				ArrayList<Javljanja> stajanje = new ArrayList<Javljanja>();//brzina < 6
				ArrayList<Javljanja> mirovanje = new ArrayList<Javljanja>(); //kontakt true
				String event = "";
				for(Javljanja javljanje : javljanja) {
					if(javljanje.getBrzina() < 6 && !javljanje.equals(javljanja.get(javljanja.size() - 1))) {
						stajanje.add(javljanje);
						if(javljanje.getSistemAlarmi().getSifra().equals("1091") || javljanje.getSistemAlarmi().getSifra().equals("1092")
								|| javljanje.getSistemAlarmi().getSifra().equals("1095") || !javljanje.getEventData().equals("0")) {
							event = javljanje.getEventData();
						}
						if(javljanje.isKontakt()) {
							mirovanje.add(javljanje);
						}
					}else {
						if(stajanje.size() > 1) {
							StajanjeMirovanje sm = new StajanjeMirovanje();
							sm.setObjekat(objekat.getOznaka());
							sm.setPocetak(stajanje.get(0).getDatumVreme());
							sm.setKraj(stajanje.get(stajanje.size() - 1).getDatumVreme());
							long razlikaStajanje = sm.getKraj().getTime() - sm.getPocetak().getTime();
							razlikaStajanje /= 1000;//sekunde
							long sati = razlikaStajanje / 3600;
							long minuta = (razlikaStajanje - sati * 3600) / 60;
							long sekundi = razlikaStajanje - sati * 3600 - minuta * 60;
							sm.setVremeStajanja(sati + ":" + minuta + ":" + sekundi);
							if(mirovanje.size() > 1) {
								long razlikaMirovanje = mirovanje.get(mirovanje.size() - 1).getDatumVreme().getTime() - mirovanje.get(0).getDatumVreme().getTime();
								razlikaMirovanje /= 1000;
								long satiM = razlikaMirovanje / 3600;
								long minutaM = (razlikaMirovanje - satiM * 3600) / 60;
								long sekundiM = razlikaMirovanje - satiM * 3600 - minutaM * 60;
								sm.setVremeMirovanja(satiM + ":" + minutaM + ":" + sekundiM);
							}
							sm.setOpis(event);
							lista.add(sm);
						}
						stajanje.clear();
						mirovanje.clear();
					}
				}
			}
		}
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		return lista;
	}

	@Override
	public Javljanja vratiJavljanjeZaStajanje(Objekti objekat) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.eq("valid", true));
		criteria.add(Restrictions.gt("brzina", 5));
		criteria.addOrder(Order.desc("datumVreme"));
		criteria.setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (Javljanja) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@Override
	public ArrayList<PredjeniPut> vratiPredjeniPut(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo) {
		ArrayList<PredjeniPut> lista = new ArrayList<PredjeniPut>();
		//ObdServis obdDAO = new ObdServ
		for(Objekti objekat : objekti) {
			ArrayList<Javljanja> javljanja = vratiJavljanjaObjektaOdDoPrvoPoslednje(objekat, vremeOd, vremeDo);
			ArrayList<Obd> obd = nadjiObdPoObjektuOdDoPrvoPoslednje(objekat, vremeOd, vremeDo);
			PredjeniPut predjeniPut = new PredjeniPut(objekat.getOznaka(), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
			
			if(javljanja != null && !javljanja.isEmpty()) {
				predjeniPut.setVirtualOdo(javljanja.get(1).getVirtualOdo() - javljanja.get(0).getVirtualOdo());
				if(obd != null && !obd.isEmpty()) {
					predjeniPut.setUkupnoKm(obd.get(1).getUkupnoKm() - obd.get(0).getUkupnoKm());
					predjeniPut.setUkupnoGorivo(obd.get(1).getUkupnoGorivo() - obd.get(0).getUkupnoGorivo());
					if(predjeniPut.getVirtualOdo() != 0.0f) {
						predjeniPut.setProsPotGps(predjeniPut.getUkupnoGorivo()/(predjeniPut.getVirtualOdo()/100));
						}
					if(predjeniPut.getUkupnoKm() != 0.0f) {
						predjeniPut.setProsPotr(predjeniPut.getUkupnoGorivo()/(predjeniPut.getUkupnoKm()/100));
						}
					}
				}
			lista.add(predjeniPut);
			}
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		return lista;
		}

}

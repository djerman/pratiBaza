package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
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
import pratiBaza.tabele.Vozila;

@Repository("javljanjeDAO")
public class JavljanjaDAOImpl implements JavljanjaDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiJavljanja(Javljanja javljanje) {
		sessionFactory.getCurrentSession().saveOrUpdate(javljanje);
	}

	@Override
	public void azurirajJavljanja(Javljanja javljanje) {
		sessionFactory.getCurrentSession().saveOrUpdate(javljanje);
	}

	@Override
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
		String upit = "SELECT jv FROM Javljanja jv where jv.objekti = :objekat ORDER BY jv.datumVreme desc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
			}catch (Exception e) {
				return null;
				}
		
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat)).addOrder(Order.desc("datumVreme")).setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (Javljanja) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
		*/
	}

	@Override
	public List<Javljanja> vratiJavljanjaObjektaOdDo(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		String upit = "SELECT jv FROM Javljanja jv where jv.objekti = :objekat AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo ORDER BY jv.datumVreme asc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		query.setParameter("vremeOd", vremeOd);
		query.setParameter("vremeDo", vremeDo);
		return query.getResultList();
		/*
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
			sessionFactory.getCurrentSession().flush();
			sessionFactory.getCurrentSession().clear();
			return javljanja2;
		}else {
			sessionFactory.getCurrentSession().flush();
			sessionFactory.getCurrentSession().clear();
			return javljanja;
		}
		*/
	}

	@Override
	public List<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmima(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		String upit = "SELECT jv FROM Javljanja jv where jv.objekti = :objekat AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo "
				+ "AND jv.valid = TRUE AND jv.sistemAlarmi.sifra <> 0 ORDER BY jv.datumVreme asc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		query.setParameter("vremeOd", vremeOd);
		query.setParameter("vremeDo", vremeDo);
		return query.getResultList();
		/*
		ArrayList<Javljanja> javljanja = new ArrayList<Javljanja>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.ge("datumVreme", vremeOd));
		criteria.add(Restrictions.lt("datumVreme", vremeDo));
		criteria.add(Restrictions.eq("valid", true));
		
		criteria.createAlias("sistemAlarmi", "alarmi");
		criteria.add(Restrictions.ne("alarmi.sifra","0"));
		
		criteria.addOrder(Order.asc("datumVreme"));
		ArrayList<Javljanja> javljanja2 = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(javljanja2 != null) {
			javljanja.addAll(javljanja2);
		}
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		return javljanja;
		*/
	}

	@Override
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		ArrayList<Javljanja> lista = new ArrayList<Javljanja>();
		Javljanja prvo = vratiJavljanjeObjektaDoIliOd(objekat, datumVremeOd, false);
		if(prvo != null) {
			lista.add(prvo);
			Javljanja poslednje = vratiJavljanjeObjektaDoIliOd(objekat, datumVremeDo, true);
			if(poslednje != null) {
				lista.add(poslednje);
			}
		}
		return lista;
	}
	
	@Override
	public ArrayList<Obd> nadjiObdPoObjektuOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		ArrayList<Obd> lista = new ArrayList<Obd>();
		Obd prvo = vratiObdObjektaDoIliOd(objekat, datumVremeOd, false);
		if(prvo != null) {
			lista.add(prvo);
			Obd poslednje = vratiObdObjektaDoIliOd(objekat, datumVremeDo, true);
			if(poslednje != null) {
				lista.add(poslednje);
			}
		}
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
			ArrayList<Javljanja> javljanja = vratiJavljanjaObjektaOdDoPrvoPoslednje(objekat, vremeOd, vremeDo);
			if(!javljanja.isEmpty()) {
				PredjeniPut put = new PredjeniPut(objekat.getOznaka(), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
				put.setVirtualOdo(javljanja.get(1).getVirtualOdo() - javljanja.get(0).getVirtualOdo());
				
				ArrayList<Obd> obd = nadjiObdPoObjektuOdDoPrvoPoslednje(objekat, vremeOd, vremeDo);
				if(!obd.isEmpty()) {
					put.setUkupnoGorivo(obd.get(1).getUkupnoGorivo() - obd.get(0).getUkupnoGorivo());
					put.setUkupnoKm(obd.get(1).getUkupnoKm() - obd.get(0).getUkupnoKm());
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
		return lista;
	}

	@Override
	public List<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmimaZona(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo,  ArrayList<SistemAlarmi> alarmi) {
		String upit = "SELECT jv FROM Javljanja jv where jv.objekti = :objekat AND jv.valid = TRUE AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo "
				+ "AND jv.sistemAlarmi IN :alarmi ORDER BY jv.datumVreme asc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		query.setParameter("vremeOd", vremeOd);
		query.setParameter("vremeDo", vremeDo);
		query.setParameter("alarmi", alarmi);
		return query.getResultList();
		/*
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
		*/
	}

	@Override
	public List<Javljanja> vratiJavljanjaObjekataOdDoSaAlarmima(ArrayList<Objekti> objekti, Timestamp vremeOd,Timestamp vremeDo, boolean pregled) {
		String upit = "SELECT jv FROM Javljanja jv where jv.objekti IN :objekti AND jv.valid = TRUE AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo "
				+ "AND jv.sistemAlarmi <> 0 ORDER BY jv.datumVreme asc";
		String upit2 = "SELECT jv FROM Javljanja jv where jv.objekti IN :objekti AND jv.valid = TRUE AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo "
				+ "AND jv.sistemAlarmi <> 0 AND jv.sistemAlarmi.pregled = TRUE ORDER BY jv.datumVreme asc";
		TypedQuery<Javljanja> query;
		if(pregled) {
			query = sessionFactory.getCurrentSession().createQuery(upit2, Javljanja.class);
		}else {
			query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		}
		query.setParameter("objekti", objekti);
		query.setParameter("vremeOd", vremeOd);
		query.setParameter("vremeDo", vremeDo);
		return query.getResultList();
		/*
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
		*/
	}

	@Override
	public List<Javljanja> vratiJavljanjaObjekataOdDoSaBrzinama(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo) {
		String upit = "SELECT jv FROM Javljanja jv where jv.objekti IN :objekti AND jv.valid = TRUE AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo "
				+ "AND jv.sistemAlarmi <> 0 ORDER BY jv.brzina desc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekti", objekti);
		query.setParameter("vremeOd", vremeOd);
		query.setParameter("vremeDo", vremeDo);
		query.setMaxResults(10);
		return query.getResultList();
		/*
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
		*/
	}

	@Override
	public Javljanja vratiJavljanjeObjektaDoIliOd(Objekti objekat, Timestamp datumVreme, boolean vremeDo) {
		String vreme = "SELECT jv FROM Javljanja jv where jv.objekti = :objekat AND jv.valid = TRUE AND jv.datumVreme <= :datumVreme ORDER BY jv.datumVreme desc";
		String vremeOd = "SELECT jv FROM Javljanja jv where jv.objekti = :objekat AND jv.valid = TRUE AND jv.datumVreme >= :datumVreme ORDER BY jv.datumVreme asc";
		TypedQuery<Javljanja> query ;
		if(vremeDo) {
			query = sessionFactory.getCurrentSession().createQuery(vreme, Javljanja.class);
		}else {
			query = sessionFactory.getCurrentSession().createQuery(vremeOd, Javljanja.class);
		}
		query.setParameter("objekat", objekat);
		query.setParameter("datumVreme", datumVreme);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SQLQuery query;
		if(vremeDo) {
			query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM prati.ce_javljanja where objekatId = :objId and datumVreme < :dV "
				+ "order by datumVreme desc limit 1");
		}else {
			query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM prati.ce_javljanja where objekatId = :objId and datumVreme > :dV "
				+ "order by datumVreme asc limit 1");
		}

		@SuppressWarnings("unchecked")
		List<Object[]> javljanjeData = query
				.setTimestamp("dV", datumVreme)
				.setLong("objId", objekat.getId()).list();
		
		Javljanja javljanje = new Javljanja();
		if(!javljanjeData.isEmpty() && javljanjeData != null) {
			for (Object[] row : javljanjeData) {
				Criteria criteriaAlarm = sessionFactory.getCurrentSession().createCriteria(SistemAlarmi.class);
				Criteria criteriaZona = sessionFactory.getCurrentSession().createCriteria(Zone.class);
				Criteria criteriaKorisnik = sessionFactory.getCurrentSession().createCriteria(Korisnici.class);
				if(row[0].toString() != null && !row[0].toString().equals("")) {
					javljanje.setId(Long.parseLong(row[0].toString()));
					javljanje.setVersion(Integer.parseInt(row[1].toString()));
					javljanje.setValid(Boolean.valueOf(row[2].toString()));
					javljanje.setObjekti(objekat);
					try {
						javljanje.setDatumVreme(sdf.parse(row[4].toString()));
						} catch (ParseException e) {
							e.printStackTrace();
							}
					javljanje.setLon(Double.parseDouble(row[5].toString()));
					javljanje.setLat(Double.parseDouble(row[6].toString()));
					javljanje.setPravac(Float.parseFloat(row[7].toString()));
					javljanje.setVisina(Float.parseFloat(row[8].toString()));
					javljanje.setBrzina(Integer.parseInt(row[9].toString()));
					javljanje.setKontakt(Boolean.parseBoolean(row[10].toString()));
			
					String alarm = row[11].toString();
					if(alarm != null && !alarm.isEmpty()) {
						long id = Long.parseLong(alarm);
						criteriaAlarm.add(Restrictions.eq("id", id));
						if(criteriaAlarm.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
							javljanje.setSistemAlarmi((SistemAlarmi)criteriaAlarm.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult());
							}else {
								javljanje.setSistemAlarmi(null);
								}
						}else {
							javljanje.setSistemAlarmi(null);
							}
					javljanje.setVirtualOdo(Float.parseFloat(row[12].toString()));
					javljanje.setEventData(row[13].toString());
					
					if(row[14] != null) {
						String zona = row[14].toString();
						if(zona != null && !zona.isEmpty()) {
							long zonaId = Long.parseLong(zona);
							criteriaZona.add(Restrictions.eq("id", zonaId));
							if(criteriaZona.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
								javljanje.setZona((Zone)criteriaZona.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult());
								}else {
									javljanje.setZona(null);
								}
							}else {
								javljanje.setZona(null);
							}
						}else {
							javljanje.setZona(null);
							}
					
					javljanje.setIbutton(row[15].toString());
					
					if(row[16] != null) {
						String korisnik = row[16].toString();
						if(korisnik != null && !korisnik.isEmpty()) {
							long korisnikId = Long.parseLong(korisnik);
							criteriaKorisnik.add(Restrictions.eq("id", korisnikId));
							if(criteriaKorisnik.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
								javljanje.setKorisnik((Korisnici)criteriaKorisnik.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult());
								}else {
									javljanje.setKorisnik(null);
									}
							}else {
								javljanje.setKorisnik(null);
							}
						}else {
							javljanje.setKorisnik(null);
						}
					}else {
						javljanje = null;
					}
				}
			}else {
				javljanje = null;
				}
		return javljanje;
		*/
	}

	@Override
	public Obd vratiObdObjektaDoIliOd(Objekti objekat, Timestamp datumVreme, boolean vremeDo) {
		String vreme = "SELECT ob FROM Obd ob where ob.objekti = :objekat AND ob.datumVreme <= :datumVreme ORDER BY ob.datumVreme desc";
		String vremeOd = "SELECT ob FROM Obd ob where ob.objekti = :objekat AND ob.datumVreme >= :datumVreme ORDER BY ob.datumVreme asc";
		TypedQuery<Obd> query ;
		if(vremeDo) {
			query = sessionFactory.getCurrentSession().createQuery(vreme, Obd.class);
		}else {
			query = sessionFactory.getCurrentSession().createQuery(vremeOd, Obd.class);
		}
		query.setParameter("objekat", objekat);
		query.setParameter("datumVreme", datumVreme);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SQLQuery query;
		if(vremeDo) {
			query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM cg_obd where objekatId = :objId and datumVreme < :dV "
				+ "order by datumVreme desc limit 1");
		}else {
			query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM cg_obd where objekatId = :objId and datumVreme > :dV "
				+ "order by datumVreme asc limit 1");
		}

		@SuppressWarnings("unchecked")
		List<Object[]> obdData = query
				.setTimestamp("dV", datumVreme)
				.setLong("objId", objekat.getId()).list();
		
		Obd obd = new Obd();
		if(!obdData.isEmpty() && obdData != null) {
			for (Object[] row : obdData) {
				if(row[0].toString() != null && !row[0].toString().equals("")) {
					obd.setId(Long.parseLong(row[0].toString()));
					obd.setVersion(Integer.parseInt(row[1].toString()));
					obd.setObjekti(objekat);
					obd.setRpm(Integer.parseInt(row[4].toString()));
					obd.setTemperatura(Integer.parseInt(row[5].toString()));
					obd.setOpterecenje(Float.parseFloat(row[6].toString()));
					obd.setGas(Float.parseFloat(row[7].toString()));
					obd.setNivoGoriva(Float.parseFloat(row[8].toString()));
					obd.setAkumulator(Float.parseFloat(row[9].toString()));
					obd.setTripKm(Float.parseFloat(row[10].toString()));
					obd.setTripGorivo(Float.parseFloat(row[11].toString()));
					obd.setUkupnoVreme(Float.parseFloat(row[12].toString()));
					obd.setUkupnoKm(Integer.parseInt(row[13].toString()));
					obd.setUkupnoGorivo(Float.parseFloat(row[14].toString()));
					obd.setProsecnaPotrosnja(Float.parseFloat(row[15].toString()));
					obd.setGreske(row[16].toString());
					try {
						obd.setDatumVreme(sdf.parse(row[3].toString()));
						} catch (ParseException e) {
							e.printStackTrace();
							}
				}else {
					obd = null;
				}
			}
		}else {
			obd = null;
		}
		return obd;
		*/
	}

	//metod nije pozvan u ServisImpl
	@Override
	public List<Javljanja> vratiJavljanjaZaStajanja(Objekti objekat) {
		String upit = "SELECT jv FROM Javljanja jv where jv.objekti = :objekat AND jv.valid = TRUE ORDER BY jv.datumVreme asc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		return query.getResultList();
		/*
		Disjunction disjunction = Restrictions.disjunction();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.desc("datumVreme"));
		//for(Iterator iterator = integerArray.iterator; iterator.hasNext())
		ArrayList<Javljanja> javljanja2 = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(javljanja2 == null) {
			javljanja2 = new ArrayList<Javljanja>();
		}
		for(Iterator<Javljanja> iterator = javljanja2.iterator(); iterator.hasNext();) {
			disjunction.add(Restrictions.lt("brzina", 6));
		}
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		return javljanja2;
		*/
	}

	@Override
	public ArrayList<StajanjeMirovanje> vratiStajanjaMirovanja(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, int duzina) {			
		ArrayList<StajanjeMirovanje> lista = new ArrayList<StajanjeMirovanje>();
		for(Objekti objekat: objekti) {
			String upit = "SELECT jv FROM Javljanja jv where jv.objekti = :objekat AND jv.valid = TRUE AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo ORDER BY jv.datumVreme asc";
			TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
			query.setParameter("objekat", objekat);
			query.setParameter("vremeOd", vremeOd);
			query.setParameter("vremeDo", vremeDo);
			List<Javljanja> javljanja = query.getResultList();
			/*
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
			criteria.add(Restrictions.eq("objekti", objekat));
			criteria.add(Restrictions.ge("datumVreme", vremeOd));
			criteria.add(Restrictions.lt("datumVreme", vremeDo));
			criteria.add(Restrictions.eq("valid", true));
			criteria.addOrder(Order.asc("datumVreme"));
			ArrayList<Javljanja> javljanja = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			*/
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
		return lista;
		
	}

	@Override
	public Javljanja vratiJavljanjeZaStajanje(Objekti objekat) {
		String upit = "SELECT jv FROM Javljanja jv where jv.objekti = :objekat AND jv.valid = TRUE AND jv.brzina > 5 ORDER BY jv.datumVreme desc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.eq("valid", true));
		criteria.add(Restrictions.gt("brzina", 5));
		criteria.addOrder(Order.desc("datumVreme"));
		criteria.setMaxResults(1);//ovde je problem
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			sessionFactory.getCurrentSession().flush();
			sessionFactory.getCurrentSession().clear();
			return (Javljanja) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			sessionFactory.getCurrentSession().flush();
			sessionFactory.getCurrentSession().clear();
			return null;
		}
		*/
	}

	@Override
	public ArrayList<PredjeniPut> vratiPredjeniPut(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo) {
		ArrayList<PredjeniPut> lista = new ArrayList<PredjeniPut>();
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
		return lista;
		}

	@Override
	public ArrayList<Vozila> vratiVozilaZaServise(ArrayList<Objekti> objekti, int tipServisa, int doServisa){
		ArrayList<Vozila> lista = new ArrayList<>();
		long dan = 86400000;
		Date sad = new Date();
		Timestamp sada = new Timestamp(sad.getTime());
		for(Objekti objekat : objekti) {
			Vozila vozilo = objekat.getVozilo();
			if(vozilo != null) {
				Javljanja poslednje = vratiJavljanjeObjektaDoIliOd(objekat, sada, true);
				Obd poslednjeObd = vratiObdObjektaDoIliOd(objekat, sada, true);
				float razlikaGps = 0;
				int razlikaObd = 0;
				int razlikaDana = 0;
				if(poslednje != null && poslednje.isValid()) {
					switch (tipServisa) {
					//mali servis
					case 2: razlikaGps = poslednje.getVirtualOdo() - vozilo.getMaliPoslednjiGPSkm();
					if(poslednjeObd != null) {
						razlikaObd = poslednjeObd.getUkupnoKm() - vozilo.getMaliPoslednjiOBDkm();
						}
				        if(vozilo.getMaliPoslednjiDatum() != null) {
				        	razlikaDana = (int) ((sada.getTime() - vozilo.getMaliPoslednjiDatum().getTime()) / dan);
				        }
				        if(vozilo.getMaliServisKm() - razlikaGps < doServisa || vozilo.getMaliServisKm() - razlikaObd < doServisa || 
				        		razlikaDana > vozilo.getMaliServisMeseci() * 30) {
				        	vozilo.setKmOdGpsMs(razlikaGps);
				        	vozilo.setDanaOdMs(razlikaDana);
				        	if(poslednjeObd != null) {
				        		vozilo.setKmOdObdMs(razlikaObd);
				        		}
				        	lista.add(vozilo);
				        	}
				     break;
				     //veliki servis
				     case 3: razlikaGps = poslednje.getVirtualOdo() - vozilo.getVelikiPoslednjiGPSkm();
				     if(poslednjeObd != null) {
				    	 razlikaObd = poslednjeObd.getUkupnoKm() - vozilo.getVelikiPoslednjiOBDkm();
				    	 }
		                if(vozilo.getVelikiPoslednjiDatum() != null) {
		                	razlikaDana = (int) ((sada.getTime() - vozilo.getVelikiPoslednjiDatum().getTime()) / dan);
		                }
		                if(vozilo.getVelikiServisKm() - razlikaGps < doServisa || vozilo.getVelikiServisKm() - razlikaObd < doServisa || 
		                		razlikaDana > vozilo.getVelikiServisMeseci() * 30) {
		                	vozilo.setKmOdGpsVs(razlikaGps);
		                	vozilo.setDanaOdVs(razlikaDana);
		                	if(poslednjeObd != null) {
		                		vozilo.setKmOdObdVs(poslednjeObd.getUkupnoKm() - vozilo.getVelikiPoslednjiOBDkm());
		                		}else {
		                			vozilo.setKmOdObdVs(razlikaObd);
		                		}
		                	lista.add(vozilo);
		                	}
				     break;
				//registracija
				case 4: if(vozilo.getDatumPoslednjeRegistracije() != null) {
					        razlikaDana = (int) ((sada.getTime() - vozilo.getDatumPoslednjeRegistracije().getTime()) / dan);
					        if(razlikaDana > 365 - doServisa) {
					        	vozilo.setDanaOdRegistracije(razlikaDana);
					        	lista.add(vozilo);
					        	}
					        }
				     break;
				
				default:
				     break;
				     }
					}
				}
			}
		return lista;
	}
}

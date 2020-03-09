package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM cg_obd where objekatId = :objId and datumVreme < :dV "
				+ "order by datumVreme desc limit 1");

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
			lista.addAll(lista2);
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
			lista.addAll(lista2);
			}
		return lista;
	}
	
	private Obd vratiObdObjektaDoIliOd(Objekti objekat, Timestamp datumVreme, boolean vremeDo) {
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
					try {
						obd.setGreske(row[16].toString());
					}catch (Exception e) {
						obd.setGreske("");
					}
					
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
	}

}

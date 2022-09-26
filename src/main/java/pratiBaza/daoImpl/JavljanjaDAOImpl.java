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
import pratiBaza.pomocne.GorivoSaCenama;
import pratiBaza.pomocne.KontrolaGoriva;
import pratiBaza.pomocne.KontrolaTocenja;
import pratiBaza.pomocne.PredjeniPut;
import pratiBaza.pomocne.PredjeniPutGPS;
import pratiBaza.pomocne.StajanjeMirovanje;
import pratiBaza.tabele.Javljanja;
import pratiBaza.tabele.Obd;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemAlarmi;
import pratiBaza.tabele.Troskovi;
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
		String upit = "SELECT jv FROM Javljanja jv"
				+ " WHERE jv.objekti = :objekat"
				+ " ORDER BY jv.datumVreme desc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
			}catch (Exception e) {
				return null;
				}
	}

	@Override
	public List<Javljanja> vratiJavljanjaObjektaOdDo(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		String upit = "SELECT jv FROM Javljanja jv"
				+ " WHERE jv.objekti = :objekat"
				+ " AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo"
				+ " ORDER BY jv.datumVreme asc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		query.setParameter("vremeOd", vremeOd);
		query.setParameter("vremeDo", vremeDo);
		return query.getResultList();
	}

	@Override
	public List<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmima(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		String upit = "SELECT jv FROM Javljanja jv"
				+ " WHERE jv.objekti = :objekat"
				+ " AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo"
				+ " AND jv.sistemAlarmi.sifra <> 0"
				+ " ORDER BY jv.datumVreme asc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		query.setParameter("vremeOd", vremeOd);
		query.setParameter("vremeDo", vremeDo);
		//Query q = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		//q.setParameter("objekat", objekat);
		//CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
		//CriteriaQuery<Javljanja> criteriaQuery = criteriaBuilder.createQuery(Javljanja.class);
		return query.getResultList();
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

	//izbačeno jv.valid = TRUE 
	@Override
	public List<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmimaZona(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo,  ArrayList<SistemAlarmi> alarmi) {
		String upit = "SELECT jv FROM Javljanja jv"
				+ " WHERE jv.objekti = :objekat"
				+ " AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo"
				+ " AND jv.sistemAlarmi IN :alarmi"
				+ " ORDER BY jv.datumVreme asc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		query.setParameter("vremeOd", vremeOd);
		query.setParameter("vremeDo", vremeDo);
		query.setParameter("alarmi", alarmi);
		return query.getResultList();
	}

	//AND jv.valid = TRUE 
	@Override
	public List<Javljanja> vratiJavljanjaObjekataOdDoSaAlarmima(ArrayList<Objekti> objekti, Timestamp vremeOd,Timestamp vremeDo, boolean pregled) {
		String upit = "SELECT jv FROM Javljanja jv"
				+ " where jv.objekti IN :objekti"
				+ " AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo"
				+ " AND jv.sistemAlarmi <> 0"
				+ " ORDER BY jv.datumVreme asc";
		String upit2 = "SELECT jv FROM Javljanja jv"
				+ " where jv.objekti IN :objekti"
				+ " AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo"
				+ " AND jv.sistemAlarmi <> 0 AND jv.sistemAlarmi.pregled = TRUE"
				+ " ORDER BY jv.datumVreme asc";
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
	}

	//AND jv.valid = TRUE 
	@Override
	public List<Javljanja> vratiJavljanjaObjekataOdDoSaBrzinama(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo) {
		String upit = "SELECT jv FROM Javljanja jv"
				+ " WHERE jv.objekti IN :objekti"
				+ " AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo"
				+ " AND jv.sistemAlarmi <> 0"
				+ " ORDER BY jv.brzina desc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekti", objekti);
		query.setParameter("vremeOd", vremeOd);
		query.setParameter("vremeDo", vremeDo);
		query.setMaxResults(10);
		return query.getResultList();
	}

	//zamenjen sa before i after, izbačeno AND jv.valid = TRUE 
	@Override
	public Javljanja vratiJavljanjeObjektaDoIliOd(Objekti objekat, Timestamp datumVreme, boolean vremeDo) {
		String upit = "";
		TypedQuery<Javljanja> query;
		if(vremeDo) {
			//System.out.println("do...");
			upit = "SELECT jv FROM Javljanja jv"
					+ " WHERE jv.objekti = :objekat"
					+ " AND jv.datumVreme <= :datumVreme"
					+ " ORDER BY jv.datumVreme desc";
		}else {
			//System.out.println("od...");
			upit = "SELECT jv FROM Javljanja jv"
					+ " WHERE jv.objekti = :objekat"
					+ " AND jv.datumVreme >= :datumVreme"
					+ " ORDER BY jv.datumVreme asc";
		}
		query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class)
				.setParameter("objekat", objekat)
				.setParameter("datumVreme", datumVreme)
				.setMaxResults(1);
		
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public Obd vratiObdObjektaDoIliOd(Objekti objekat, Timestamp datumVreme, boolean vremeDo) {
		String vreme = "SELECT ob FROM Obd ob"
				+ " WHERE ob.objekti = :objekat"
				+ " AND ob.datumVreme <= :datumVreme"
				+ " ORDER BY ob.datumVreme desc";
		
		String vremeOd = "SELECT ob FROM Obd ob"
				+ " WHERE ob.objekti = :objekat"
				+ " AND ob.datumVreme >= :datumVreme"
				+ " ORDER BY ob.datumVreme asc";
		
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
	}

	//metod nije pozvan u ServisImpl, izbačeno AND jv.valid = TRUE 
	@Override
	public List<Javljanja> vratiJavljanjaZaStajanja(Objekti objekat) {
		String upit = "SELECT jv FROM Javljanja jv"
				+ " WHERE jv.objekti = :objekat"
				+ " ORDER BY jv.datumVreme asc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		return query.getResultList();
	}

	@Override
	public ArrayList<StajanjeMirovanje> vratiStajanjaMirovanja(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, int duzina) {			
		ArrayList<StajanjeMirovanje> lista = new ArrayList<StajanjeMirovanje>();
		for(Objekti objekat: objekti) {
			String upit = "SELECT jv FROM Javljanja jv"
					+ " WHERE jv.objekti = :objekat"
					+ " AND jv.datumVreme BETWEEN :vremeOd AND :vremeDo"
					+ " ORDER BY jv.datumVreme asc";
			TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
			query.setParameter("objekat", objekat);
			query.setParameter("vremeOd", vremeOd);
			query.setParameter("vremeDo", vremeDo);
			List<Javljanja> javljanja = query.getResultList();

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

	//izbačeno AND jv.valid = TRUE 
	@Override
	public Javljanja vratiJavljanjeZaStajanje(Objekti objekat) {
		String upit = "SELECT jv FROM Javljanja jv"
				+ " WHERE jv.objekti = :objekat"
				+ " AND jv.brzina > 5"
				+ " ORDER BY jv.datumVreme desc";
		TypedQuery<Javljanja> query = sessionFactory.getCurrentSession().createQuery(upit, Javljanja.class);
		query.setParameter("objekat", objekat);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * pregled predjenog puta sa gps i obd podacima
	 */
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
	
	/*
	 * izveštaj za kontrolu goriva gps + obd ako ga ima
	 */
	@Override
	public ArrayList<KontrolaGoriva> vratiKontroluGoriva(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo){
		ArrayList<KontrolaGoriva> lista = new ArrayList<KontrolaGoriva>();
		
		for(Objekti objekat : objekti) {
			ArrayList<Javljanja> javljanja = vratiJavljanjaObjektaOdDoPrvoPoslednje(objekat, vremeOd, vremeDo);
			ArrayList<Obd> obd = nadjiObdPoObjektuOdDoPrvoPoslednje(objekat, vremeOd, vremeDo);
			String registracija = objekat.getVozilo() == null ? "" : objekat.getVozilo().getRegistracija();
			KontrolaGoriva kon = new KontrolaGoriva(objekat.getOznaka(), registracija, 0.0f, 0.0f,
					0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
			
			kon.setPotrosnja(objekat.getVozilo() == null ? 0.0f : objekat.getVozilo().getPotrosnja());
			kon.setKolicina(nadjiSumuPotroseneKolicine(objekat, vremeOd, vremeDo));
		
			if(javljanja != null && !javljanja.isEmpty()) {
				
				kon.setVirtualOdo(javljanja.get(1).getVirtualOdo() - javljanja.get(0).getVirtualOdo());
				//prosečna potrošnja po gps i kupljenom gorivu
				if(kon.getVirtualOdo() != 0.0f)
					kon.setGpsKolicina(kon.getKolicina()/(kon.getVirtualOdo()/100));
				
				//ako imamo obd podatke
				if(obd != null && !obd.isEmpty()) {
					//km po obd
					kon.setUkupnoKm(obd.get(1).getUkupnoKm() - obd.get(0).getUkupnoKm());
					//potrošeno gorivo
					kon.setUkupnoGorivo(obd.get(1).getUkupnoGorivo() - obd.get(0).getUkupnoGorivo());
					//prosečna potrošnja po gps km i potrošenom gorivu sa obd
					if(kon.getVirtualOdo() != 0.0f) {
						kon.setProsPotGps(kon.getUkupnoGorivo()/(kon.getVirtualOdo()/100));
						}
					//prosečna potrošnja po obd km i potrošenom gorivu obd
					if(kon.getUkupnoKm() != 0.0f) {
						kon.setProsPotr(kon.getUkupnoGorivo()/(kon.getUkupnoKm()/100));
						kon.setOdoKolicina(kon.getKolicina()/(kon.getUkupnoKm()/100));
						}
					}
				}
			
			lista.add(kon);
		}
		
		return lista;
	}
	
	@Override
	public ArrayList<GorivoSaCenama> vratiGorivoSaCenama(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo){
		ArrayList<GorivoSaCenama> lista = new ArrayList<GorivoSaCenama>();
		
		for(Objekti objekat : objekti) {
			ArrayList<Javljanja> javljanja = vratiJavljanjaObjektaOdDoPrvoPoslednje(objekat, vremeOd, vremeDo);
			ArrayList<Obd> obd = nadjiObdPoObjektuOdDoPrvoPoslednje(objekat, vremeOd, vremeDo);
			ArrayList<Troskovi> troskovi = nadjiSveTroskoveGoriva(objekat, vremeOd, vremeDo);
			
			String registracija = "";
			String markaTip = "";
			float dozvoljenaPotrosnja = 0;
			if(objekat.getVozilo() != null) {
				registracija = objekat.getVozilo().getRegistracija();
				markaTip = objekat.getVozilo().getMarka() + " " + objekat.getVozilo().getModel();
				dozvoljenaPotrosnja = objekat.getVozilo().getPotrosnja();
			}
			float cena = 0;
			float kolicina = 0;
			float ukupnoCena = 0;
			if(troskovi != null && !troskovi.isEmpty() && troskovi.size() > 0) {
				cena = (float)troskovi.stream().mapToDouble(t -> t.getCena()).average().orElse(0);
				kolicina = (float)troskovi.stream().mapToDouble(t -> t.getKolicina()).sum();
				ukupnoCena = (float)troskovi.stream().mapToDouble(t -> t.getUkupno()).sum();
			}
			float predjenoGps = 0;
			if(javljanja != null && !javljanja.isEmpty() && javljanja.size() > 0) {
				predjenoGps = javljanja.get(1).getVirtualOdo() - javljanja.get(0).getVirtualOdo();
			}

			float ukupnaPotrosnja = 0;
			int predjenoObd = 0;
			float prosPotrosnja = 0;
			if(obd != null && !obd.isEmpty() && obd.size() > 0) {
				ukupnaPotrosnja = obd.get(1).getUkupnoGorivo() - obd.get(0).getUkupnoGorivo();
				predjenoObd = (obd.get(1).getUkupnoKm() - obd.get(0).getUkupnoKm());
				prosPotrosnja = ukupnaPotrosnja == 0 || predjenoObd == 0 ? 0 : ukupnaPotrosnja /(predjenoObd/100);
			}
			float razlikaUPotrosnji = prosPotrosnja - dozvoljenaPotrosnja;
			float potrosenoViseGoriva = razlikaUPotrosnji == 0 || predjenoObd == 0 ? 0 : razlikaUPotrosnji * predjenoObd / 100;
			float cenaVisePotrosenogGoriva = potrosenoViseGoriva * cena;
			
			GorivoSaCenama gor = new GorivoSaCenama(objekat.getOznaka(), "", markaTip, registracija, prosPotrosnja, dozvoljenaPotrosnja, razlikaUPotrosnji, 
					predjenoGps, predjenoObd, cena, kolicina, ukupnoCena, potrosenoViseGoriva, cenaVisePotrosenogGoriva);
			
			lista.add(gor);
		}
		return lista;
	}

	@Override
	public ArrayList<KontrolaTocenja> vratiSipanja(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo){
		ArrayList<KontrolaTocenja> lista = new ArrayList<KontrolaTocenja>();
		for(Objekti objekat : objekti) {
			ArrayList<Troskovi> troskovi = nadjiSveTroskoveGoriva(objekat, vremeOd, vremeDo);
			System.out.println("broj točenja: " + troskovi.size());
			if(troskovi != null && !troskovi.isEmpty() && troskovi.size() > 1) {
				Timestamp pocetak =  troskovi.get(0).getDatumVreme();
				Timestamp poslednjeSipanje = troskovi.get(troskovi.size() - 1).getDatumVreme();
				ArrayList<Javljanja> javljanja = vratiJavljanjaObjektaOdDoPrvoPoslednje(objekat, pocetak, poslednjeSipanje);
				ArrayList<Obd> obd = nadjiObdPoObjektuOdDoPrvoPoslednje(objekat, pocetak, poslednjeSipanje);
				
				if(javljanja != null && !javljanja.isEmpty() && javljanja.size() == 2) {
					String registracija = "";
					String markaTip = "";
					if(objekat.getVozilo() != null) {
						registracija = objekat.getVozilo().getRegistracija();
						markaTip = objekat.getVozilo().getMarka() + " " + objekat.getVozilo().getModel();
					}
					float gpsPut = javljanja.get(1).getVirtualOdo() - javljanja.get(0).getVirtualOdo();
					float ukupno = (float)troskovi.stream().mapToDouble(t -> t.getKolicina()).sum();
					int obdPut = 0;
					if(obd != null && !obd.isEmpty() && obd.size() == 2) {
						obdPut = obd.get(1).getUkupnoKm() - obd.get(0).getUkupnoKm();
					}
					float prosGps = 0;
					float prosoObd = 0;
					if(gpsPut != 0){
						prosGps = ukupno / (gpsPut / 100);
					}
					if(obdPut != 0) 
						prosoObd = ukupno / (obdPut / 100);
					KontrolaTocenja kon = new KontrolaTocenja(objekat.getOznaka(), registracija, markaTip, troskovi.size() - 1, new Date(pocetak.getTime()), 
							new Date(poslednjeSipanje.getTime()), ukupno, gpsPut, obdPut, prosGps, prosoObd);
					lista.add(kon);
				}
			}
		}
		return lista;
	}
	
	@Override
	public float nadjiSumuPotroseneKolicine(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		String upit = "SELECT SUM(t.kolicina) FROM Troskovi t WHERE t.objekti = :objekat"
				+ " AND t.datumVreme BETWEEN :datumVremeOd AND :datumVremeDo"
				+ " AND t.tipServisa = 0"
				+ " AND t.izbrisan = false";
		TypedQuery<Double> query = sessionFactory.getCurrentSession().createQuery(upit, Double.class)
				.setParameter("objekat", objekat)
				.setParameter("datumVremeOd", datumVremeOd)
				.setParameter("datumVremeDo", datumVremeDo);
		try {
			return query.getSingleResult().floatValue();
		}catch (Exception e) {
			return 0.0f;
		}
	}
	
	private ArrayList<Troskovi> nadjiSveTroskoveGoriva(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo){
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		String upit = "SELECT t FROM Troskovi t WHERE t.objekti = :objekat"
				+ " AND t.datumVreme BETWEEN :datumVremeOd AND :datumVremeDo"
				+ " AND t.tipServisa = 0"
				+ " AND t.izbrisan = false";
		TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class)
				.setParameter("objekat", objekat)
				.setParameter("datumVremeOd", datumVremeOd)
				.setParameter("datumVremeDo", datumVremeDo);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			// TODO: handle exception
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

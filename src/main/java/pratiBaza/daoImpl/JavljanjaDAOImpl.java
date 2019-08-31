package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.JavljanjaDAO;
import pratiBaza.pomocne.StajanjeMirovanje;
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
		if(javljanja2 != null) {
			return javljanja2;
		}else {
			return javljanja;
		}
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
		ArrayList<Javljanja> javljanja = new ArrayList<Javljanja>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Javljanja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.in("sistemAlarmi", alarmi));
		criteria.add(Restrictions.ge("datumVreme", vremeOd));
		criteria.add(Restrictions.lt("datumVreme", vremeDo));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("datumVreme"));
		ArrayList<Javljanja> javljanja2 = (ArrayList<Javljanja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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
		return javljanja2;
		/*if(javljanja2 != null) {
			return javljanja2;
		}else {
			return javljanja;
		}**/
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
}

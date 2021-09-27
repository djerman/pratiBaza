package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.AlarmiKorisnikDAO;
import pratiBaza.tabele.AlarmiKorisnik;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemAlarmi;

@Repository("alarmKorisnikDAO")
public class AlarmiKorisnikDAOImpl implements AlarmiKorisnikDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		alarmKorisnik.setVersion(0);
		alarmKorisnik.setIzmenjeno(new Timestamp((new Date()).getTime()));
		alarmKorisnik.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(alarmKorisnik);
	}

	@Override
	public List<AlarmiKorisnik> vratiAlarmePoKorisniku(Korisnici korisnik, boolean aktivno, boolean email, boolean obavestenje) {
		String aktivan = "";
		if(aktivno) {
			aktivan = " AND ak.aktivan = :akt";
		}
		String mail = "";
		if(email) {
			mail = " AND ak.email = :email";
		}
		String obav = "";
		if(obavestenje) {
			obav = " AND ak.obavestenje = :obavestenje";
		}
		String upit = "SELECT ak FROM AlarmiKorisnik ak where ak.sistemPretplatnici = :pretplatnik AND ak.korisnik = :korisnik AND "
				+ "(:organizacija is null or ak.organizacija = :organizacija)" + aktivan + mail + obav;
		
		TypedQuery<AlarmiKorisnik> query = sessionFactory.getCurrentSession().createQuery(upit, AlarmiKorisnik.class);
		
		query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		query.setParameter("korisnik", korisnik);
		query.setParameter("organizacija", korisnik.getOrganizacija());
		if(aktivno) {
			query.setParameter("akt", aktivno);
		}
		if(email) {
			query.setParameter("email", email);
		}
		if(obavestenje) {
			query.setParameter("obavestenje", obavestenje);
		}
		return query.getResultList();
		
		//"SELECT c FROM Customer c WHERE (:name is null or c.name = :name) and (:email is null or c.email = :email)"
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AlarmiKorisnik.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
		criteria.add(Restrictions.eq("korisnik", korisnik));
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
		}
		if(aktivno) {
			criteria.add(Restrictions.eq("aktivan", aktivno));
		}
		if(email) {
			criteria.add(Restrictions.eq("email", true));
		}
		if(obavestenje) {
			criteria.add(Restrictions.eq("obavestenje", true));
		}
		ArrayList<AlarmiKorisnik> alarmi = (ArrayList<AlarmiKorisnik>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return alarmi;
		*/
	}
	
	@Override
	public void azurirajAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		alarmKorisnik.setVersion(alarmKorisnik.getVersion() + 1);
		sessionFactory.getCurrentSession().update(alarmKorisnik);
	}

	@Override
	public void izbrisiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		sessionFactory.getCurrentSession().delete(alarmKorisnik);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public AlarmiKorisnik nadjiAlarmKorisnikPoId(int id) {
		String upit = "SELECT ak FROM AlarmiKorisnik ak where ak.id = :id";
		TypedQuery<AlarmiKorisnik> query = sessionFactory.getCurrentSession().createQuery(upit, AlarmiKorisnik.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AlarmiKorisnik.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			AlarmiKorisnik alarmKorisnik = (AlarmiKorisnik)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return alarmKorisnik;
		}else {
			return null;
		}
		*/
	}

	@Override
	public AlarmiKorisnik nadjiAlarmePoKorisnikObjekatAlarm(Korisnici korisnik, Objekti objekat, SistemAlarmi alarm) {
		String upit = "SELECT ak FROM AlarmiKorisnik ak where ak.korisnik = :korisnik AND ak.objekti = :objekat AND ak.sistemAlarm = :sistemAlarm";
		TypedQuery<AlarmiKorisnik> query = sessionFactory.getCurrentSession().createQuery(upit, AlarmiKorisnik.class);
		query.setParameter("korisnik", korisnik);
		query.setParameter("objekat", objekat);
		query.setParameter("sistemAlarm", alarm);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AlarmiKorisnik.class);
		criteria.add(Restrictions.eq("korisnik", korisnik));
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.eq("sistemAlarm", alarm));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			AlarmiKorisnik alarmKorisnik = (AlarmiKorisnik)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return alarmKorisnik;
		}else {
			return null;
		}
		*/
	}


	@Override
	public List<AlarmiKorisnik> nadjiSveAlarmePoKorisniku(Korisnici korisnik, boolean aktivno, boolean email, boolean obavestenje) {
		String aktivan = "";
		String mail = "";
		String obav = "";
		String pretp = "";
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			pretp = "ak.sistemPretplatnici = :pretplatnik AND ";
		}
		if(aktivno) {
			aktivan = " AND ak.aktivan = :akt";
		}
		if(email) {
			mail = " AND ak.email = :email";
		}
		if(obavestenje) {
			obav = " AND ak.obavestenje = :obavestenje";
		}
		String upit = "SELECT ak FROM AlarmiKorisnik ak where " + pretp +"ak.korisnik = :korisnik AND "
				+ "(:organizacija is null or ak.organizacija = :organizacija)" + aktivan + mail + obav;
		
		TypedQuery<AlarmiKorisnik> query = sessionFactory.getCurrentSession().createQuery(upit, AlarmiKorisnik.class);
		
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		}
		query.setParameter("korisnik", korisnik);
		query.setParameter("organizacija", korisnik.getOrganizacija());
		if(aktivno) {
			query.setParameter("akt", aktivno);
		}
		if(email) {
			query.setParameter("email", email);
		}
		if(obavestenje) {
			query.setParameter("obavestenje", obavestenje);
		}
		return query.getResultList();
		/*
		ArrayList<AlarmiKorisnik> lista = new ArrayList<AlarmiKorisnik>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AlarmiKorisnik.class);
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
		}
		if(!korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("korisnik", korisnik));
		}
		if(aktivno) {
			criteria.add(Restrictions.eq("aktivan", aktivno));
		}
		if(email) {
			criteria.add(Restrictions.eq("email", true));
		}
		if(obavestenje) {
			criteria.add(Restrictions.eq("obavestenje", true));
		}
		ArrayList<AlarmiKorisnik> lista2 = (ArrayList<AlarmiKorisnik>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
		*/
	}

	@Override
	public List<AlarmiKorisnik> nadjiSveAlarmeKorisnikePoObjektu(Objekti objekat) {
		String upit = "SELECT ak FROM AlarmiKorisnik ak where ak.objekti = :objekat AND ak.aktivan = :aktivan";
		TypedQuery<AlarmiKorisnik> query = sessionFactory.getCurrentSession().createQuery(upit, AlarmiKorisnik.class);
		query.setParameter("objekat", objekat);
		query.setParameter("aktivan", true);
		return query.getResultList();
		/*
		ArrayList<AlarmiKorisnik> lista = new ArrayList<AlarmiKorisnik>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AlarmiKorisnik.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.add(Restrictions.eq("aktivan", true));
		ArrayList<AlarmiKorisnik> lista2 = (ArrayList<AlarmiKorisnik>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
		*/
	}
	
}

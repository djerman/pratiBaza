package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	public void unesiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		alarmKorisnik.setVersion(0);
		alarmKorisnik.setIzmenjeno(new Timestamp((new Date()).getTime()));
		alarmKorisnik.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(alarmKorisnik);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<AlarmiKorisnik> vratiAlarmePoKorisniku(Korisnici korisnik, boolean aktivno, boolean email, boolean obavestenje) {
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
	}
	
	public void azurirajAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		alarmKorisnik.setVersion(alarmKorisnik.getVersion() + 1);
		sessionFactory.getCurrentSession().update(alarmKorisnik);
	}

	public void izbrisiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		sessionFactory.getCurrentSession().delete(alarmKorisnik);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public AlarmiKorisnik nadjiAlarmKorisnikPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AlarmiKorisnik.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			AlarmiKorisnik alarmKorisnik = (AlarmiKorisnik)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return alarmKorisnik;
		}else {
			return null;
		}
		
	}

	@Override
	public AlarmiKorisnik nadjiAlarmePoKorisnikObjekatAlarm(Korisnici korisnik, Objekti objekat, SistemAlarmi alarm) {
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
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AlarmiKorisnik> nadjiSveAlarmePoKorisniku(Korisnici korisnik, boolean aktivno, boolean email, boolean obavestenje) {
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
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AlarmiKorisnik> nadjiSveAlarmeKorisnikePoObjektu(Objekti objekat) {
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
	}
	
}

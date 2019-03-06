package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.AlarmiKorisnikDAO;
import pratiBaza.tabele.AlarmiKorisnik;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemAlarmi;

@Repository("alarmKorisnikDAO")
public class AlarmiKorisnikDAOImpl implements AlarmiKorisnikDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		sessionFactory.getCurrentSession().persist(alarmKorisnik);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SistemAlarmi> vratiAlarmePoKorisniku(Korisnici korisnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AlarmiKorisnik.class);
		criteria.add(Restrictions.eq("korisnik", korisnik));
		ArrayList<SistemAlarmi> alarmi = (ArrayList<SistemAlarmi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return alarmi;
	}
	
	public void azurirajAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
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
	
}

package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.KorisniciDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("korisnikDAO")
public class KorisniciDAOImpl implements KorisniciDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiKorisnika(Korisnici korisnik) {
		korisnik.setVersion(0);
		korisnik.setIzmenjeno(new Timestamp((new Date()).getTime()));
		korisnik.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(korisnik);
	}

	public void azurirajKorisnika(Korisnici korisnik) {
		korisnik.setVersion(korisnik.getVersion() + 1);
		korisnik.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(korisnik);
	}

	public void izbrisiKorisnika(Korisnici korisnik) {
		korisnik.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(korisnik);
	}

	public Korisnici nadjiKorisnikaPoKorisnickom(String email, String lozinka) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Korisnici.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("lozinka", lozinka));
		return (Korisnici) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Korisnici> nadjiKorisnikePoPretplatniku(SistemPretplatnici pretplatnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Korisnici.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		criteria.add(Restrictions.eq("aktivan", true));
		return (ArrayList<Korisnici>) criteria.list();
	}

	public Korisnici nadjiKorisnikaPoIButton(String iButton) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Korisnici.class);
		criteria.add(Restrictions.eq("ibutton", iButton));
		return (Korisnici) criteria.uniqueResult();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Korisnici> nadjiSveKorisnike(Korisnici korisnik, boolean aktivan) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Korisnici.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
			if(aktivan) {
				criteria.add(Restrictions.eq("aktivan", true));
			}
			if(korisnik.getOrganizacija() != null) {
				criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
			}
		}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Korisnici> lista = (ArrayList<Korisnici>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	public Korisnici nadjiKorisnikaPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Korisnici.class);
		criteria.add(Restrictions.eq("id", id));
		Korisnici korisnik = (Korisnici)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return korisnik;
	}

	public ArrayList<Korisnici> nadjiKorisnikeAktivneIzbrisane(boolean aktivan, boolean izbrisan) {
		// TODO Auto-generated method stub
		return null;
	}

}

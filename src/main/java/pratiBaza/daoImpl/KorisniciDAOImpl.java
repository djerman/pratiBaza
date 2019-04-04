package pratiBaza.daoImpl;

import java.util.ArrayList;
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
		sessionFactory.getCurrentSession().persist(korisnik);
	}

	public void azurirajKorisnika(Korisnici korisnik) {
		sessionFactory.getCurrentSession().update(korisnik);
	}

	public void izbrisiKorisnika(Korisnici korisnik) {
		sessionFactory.getCurrentSession().delete(korisnik);
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
	public ArrayList<Korisnici> nadjiSveKorisnike(Korisnici korisnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Korisnici.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			if(korisnik.getOrganizacija() != null) {
				criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
			}
		}
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Korisnici> lista = (ArrayList<Korisnici>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

}

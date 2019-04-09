package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.OrganizacijeDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;

@Repository("organizacijaDAO")
public class OrganizacijeDAOImpl implements OrganizacijeDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiOrganizacije(Organizacije organizacija) {
		sessionFactory.getCurrentSession().persist(organizacija);
	}

	public void azurirajOrganizacije(Organizacije organizacija) {
		sessionFactory.getCurrentSession().update(organizacija);
	}

	public void izbrisiOrganizacije(Organizacije organizacija) {
		organizacija.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(organizacija);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Organizacije> nadjiSveOrganizacije(Korisnici korisnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Organizacije.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
		}
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Organizacije> lista = (ArrayList<Organizacije>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}
	
}

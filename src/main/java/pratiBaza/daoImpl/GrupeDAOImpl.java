package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.GrupeDAO;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;

@Repository("grupaDAO")
public class GrupeDAOImpl implements GrupeDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGrupu(Grupe grupa) {
		sessionFactory.getCurrentSession().save(grupa);
	}

	public void azurirajGrupu(Grupe grupa) {
		sessionFactory.getCurrentSession().update(grupa);
	}

	public void izbrisiGrupu(Grupe grupa) {
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Grupe> vratiGrupe(Korisnici korisnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grupe.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			if(korisnik.getOrganizacija() != null) {
				criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
			}
		}
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Grupe> lista = (ArrayList<Grupe>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}
	
}

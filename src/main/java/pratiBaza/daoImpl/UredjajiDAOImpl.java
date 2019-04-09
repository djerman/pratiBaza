package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.UredjajiDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

@Repository("uredjajDAO")
public class UredjajiDAOImpl implements UredjajiDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiUredjaj(Uredjaji uredjaj) {
		sessionFactory.getCurrentSession().persist(uredjaj);
	}

	public void izmeniUredjaj(Uredjaji uredjaj) {
		sessionFactory.getCurrentSession().update(uredjaj);
	}

	public void izbrisiUredjaj(Uredjaji uredjaj) {
		uredjaj.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(uredjaj);
	}

	public ArrayList<Uredjaji> nadjiSveUredjaje(Korisnici korisnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uredjaji.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
		}
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivno"));
		criteria.addOrder(Order.asc("objekti"));
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Uredjaji> lista = (ArrayList<Uredjaji>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	public ArrayList<Uredjaji> nadjiSveUredjajePoPretplatniku(SistemPretplatnici pretplatnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uredjaji.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivno"));
		criteria.addOrder(Order.desc("id"));
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

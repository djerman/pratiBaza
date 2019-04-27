package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.ObjektiDAO;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("objekatDAO")
public class ObjektiDAOImpl implements ObjektiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiObjekte(Objekti objekat) {
		objekat.setVersion(0);
		sessionFactory.getCurrentSession().persist(objekat);
	}

	public void azurirajObjekte(Objekti objekat) {
		objekat.setVersion(objekat.getVersion() + 1);
		sessionFactory.getCurrentSession().update(objekat);
	}

	public void izbrisiObjekte(Objekti objekat) {
		objekat.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(objekat);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ArrayList<Objekti> vratiSveObjekte(Korisnici korisnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
			if(korisnik.getOrganizacija() != null) {
				criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
			}
		}
		criteria.addOrder(Order.desc("sistemPretplatnici"));
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Objekti> lista = (ArrayList<Objekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	public ArrayList<Objekti> vratiObjektePoPretplatniku(SistemPretplatnici pretplatnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		@SuppressWarnings("unchecked")
		ArrayList<Objekti> lista = (ArrayList<Objekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	public ArrayList<Objekti> vratiObjektePoKorisniku(Korisnici korisnik) {

		return null;
	}

	public ArrayList<Objekti> vratiObjektePoGrupi(Grupe grupa) {
		// TODO Auto-generated method stub
		return null;
	}

	public Objekti nadjiObjekatPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("id", id));
		Objekti objekat = (Objekti)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return objekat;
	}

	public ArrayList<Objekti> vratiSveObjekte(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.add(Restrictions.eq("aktivan", true));
		if(pretplatnik != null) {
			criteria.add(Restrictions.eq("sistemPretplatnici",pretplatnik));
		}
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
		}
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Objekti> lista = (ArrayList<Objekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}
	
}

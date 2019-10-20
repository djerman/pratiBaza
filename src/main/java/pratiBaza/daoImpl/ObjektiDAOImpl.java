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
import pratiBaza.dao.ObjektiDAO;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

@Repository("objekatDAO")
public class ObjektiDAOImpl implements ObjektiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiObjekte(Objekti objekat) {
		objekat.setVersion(0);
		objekat.setIzmenjeno(new Timestamp((new Date()).getTime()));
		objekat.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(objekat);
	}

	public void azurirajObjekte(Objekti objekat) {
		objekat.setVersion(objekat.getVersion() + 1);
		objekat.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(objekat);
	}

	public void izbrisiObjekte(Objekti objekat) {
		if(objekat.getUredjaji() != null) {
			objekat.getUredjaji().setObjekti(null);
		}
		objekat.setUredjaji(null);
		objekat.setAktivan(false);
		objekat.setIzbrisan(true);
		azurirajObjekte(objekat);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Objekti> vratiSveObjekte(Korisnici korisnik, boolean aktivan) {
		ArrayList<Objekti> lista = new ArrayList<Objekti>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
			}
		if(aktivan) {
			criteria.add(Restrictions.eq("aktivan", true));
			criteria.add(Restrictions.eq("izbrisan", false));
			}
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.desc("sistemPretplatnici"));
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Objekti> lista2 = (ArrayList<Objekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Objekti> vratiObjektePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean aktivan) {
		ArrayList<Objekti> lista = new ArrayList<Objekti>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		if(aktivan) {
			criteria.add(Restrictions.eq("aktivan", true));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacije", organizacija));
		}
		ArrayList<Objekti> lista2 = (ArrayList<Objekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Objekti> vratiSvaVozila(Korisnici korisnik) {
		ArrayList<Objekti> lista = new ArrayList<Objekti>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
			}
		criteria.add(Restrictions.eq("aktivan", true));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.add(Restrictions.eq("tip", true));
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.desc("sistemPretplatnici"));
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Objekti> lista2 = (ArrayList<Objekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	public ArrayList<Objekti> vratiObjektePoGrupi(Grupe grupa) {
		// TODO Auto-generated method stub
		return null;
	}

	public Objekti nadjiObjekatPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Objekti objekat = (Objekti)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return objekat;
		}else {
			return null;
		}
	}

	public ArrayList<Objekti> vratiSveObjekte(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		ArrayList<Objekti> lista = new ArrayList<Objekti>();
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
		ArrayList<Objekti> lista2 = (ArrayList<Objekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@Override
	public ArrayList<Objekti> vratiSveObjekteVozila(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		ArrayList<Objekti> lista = new ArrayList<Objekti>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.add(Restrictions.eq("aktivan", true));
		criteria.add(Restrictions.eq("tip", true));
		criteria.add(Restrictions.eq("sistemPretplatnici",pretplatnik));
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
		}
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Objekti> lista2 = (ArrayList<Objekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@Override
	public Objekti nadjiObjekatPoUredjaju(Uredjaji uredjaj) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("uredjaji", uredjaj));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Objekti objekat = (Objekti)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return objekat;
		}else {
			return null;
		}
	}
	
}

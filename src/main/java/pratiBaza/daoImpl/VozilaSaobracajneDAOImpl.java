package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Criteria;
//import javax.persistence.criteria.CriteriaQuery
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.VozilaSaobracajneDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaSaobracajne;

@Repository("saobracajnaDAO")
public class VozilaSaobracajneDAOImpl implements VozilaSaobracajneDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void unesiSaobracajnu(VozilaSaobracajne saobracajna) {
		saobracajna.setVersion(0);
		saobracajna.setIzmenjeno(new Timestamp((new Date()).getTime()));
		saobracajna.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(saobracajna);
	}

	@Override
	public void izmeniSaobracajnu(VozilaSaobracajne saobracajna) {
		saobracajna.setVersion(saobracajna.getVersion() + 1);
		saobracajna.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(saobracajna);
	}

	@Override
	public void izbrisiSaobracajnu(VozilaSaobracajne saobracajna) {
		if(saobracajna != null)
			sessionFactory.getCurrentSession().delete(saobracajna);
	}

	@Override
	public VozilaSaobracajne nadjiSaobracajnuPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaSaobracajne.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozilaSaobracajne)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@Override
	public VozilaSaobracajne nadjiSaobracajnuPoBroju(String broj) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaSaobracajne.class);
		criteria.add(Restrictions.eq("brojSaobracajne", broj));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozilaSaobracajne)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozilaSaobracajne> nadjiSveSaobracajne(Korisnici korisnik, boolean izbrisan) {
		ArrayList<VozilaSaobracajne> lista = new ArrayList<VozilaSaobracajne>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaSaobracajne.class);
		if(!korisnik.getSistemPretplatnici().isSistem() && !korisnik.isSistem()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.createAlias("vozilo", "v");
			criteria.createAlias("v.objekti", "o");
			criteria.add(Restrictions.eq("o.organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozilaSaobracajne> lista2 = (ArrayList<VozilaSaobracajne>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozilaSaobracajne> nadjiSlobodneSaobracajne(Korisnici korisnik, boolean izbrisan) {
		ArrayList<VozilaSaobracajne> lista = new ArrayList<VozilaSaobracajne>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaSaobracajne.class);
		if(!korisnik.getSistemPretplatnici().isSistem() && !korisnik.isSistem()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.createAlias("vozilo", "v");
			criteria.createAlias("v.objekti", "o");
			criteria.add(Restrictions.eq("o.organizacija", korisnik.getOrganizacija()));
			}
		if(izbrisan) {
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		criteria.add(Restrictions.isNull("saobracajna2"));
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozilaSaobracajne> lista2 = (ArrayList<VozilaSaobracajne>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozilaSaobracajne> nadjiSlobodneSaobracajnePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		ArrayList<VozilaSaobracajne> lista = new ArrayList<VozilaSaobracajne>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaSaobracajne.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		if(organizacija != null) {
			criteria.createAlias("vozilo", "v");
			criteria.createAlias("v.objekti", "o");
			criteria.add(Restrictions.eq("o.organizacija", organizacija));
			}
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.add(Restrictions.isNull("saobracajna2"));
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozilaSaobracajne> lista2 = (ArrayList<VozilaSaobracajne>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	@Override
	public VozilaSaobracajne nadjiSaobracajnuPoVozilu(Vozila vozilo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaSaobracajne.class);
		criteria.add(Restrictions.eq("vozilo", vozilo));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozilaSaobracajne)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

}

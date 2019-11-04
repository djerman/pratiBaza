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
import pratiBaza.dao.VozaciDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Vozaci;

@Repository("vozacDAO")
public class VozaciDAOImpl implements VozaciDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void unesiVozaca(Vozaci vozac) {
		vozac.setVersion(0);
		vozac.setKreirano(new Timestamp((new Date()).getTime()));
		vozac.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(vozac);
	}

	@Override
	public void izmeniVozaca(Vozaci vozac) {
		vozac.setVersion(vozac.getVersion() + 1);
		vozac.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(vozac);
	}

	@Override
	public void izbrisiVozaca(Vozaci vozac) {
		vozac.setIzbrisan(true);
		izmeniVozaca(vozac);
	}

	@Override
	public Vozaci nadjiVozacaPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozaci.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (Vozaci)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@Override
	public Vozaci nadjiVozacaPoKorisniku(Korisnici korisnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozaci.class);
		criteria.add(Restrictions.eq("korisnici", korisnik));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.desc("id")).setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (Vozaci)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Vozaci> nadjiSveVozacePoKorisniku(Korisnici korisnik) {
		ArrayList<Vozaci> lista = new ArrayList<Vozaci>();
		if(korisnik.isVozac()) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozaci.class);
			criteria.add(Restrictions.eq("korisnici", korisnik));
			criteria.add(Restrictions.eq("izbrisan", false));
			ArrayList<Vozaci> lista2 = (ArrayList<Vozaci>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(lista2 != null) {
				return lista2;
			}else {
				return lista;
			}
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Vozaci> nadjiSveVozace(Korisnici korisnik) {
		ArrayList<Vozaci> lista = new ArrayList<Vozaci>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozaci.class);
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			
		}else {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.createAlias("korisnici", "ko");
			criteria.add(Restrictions.eq("ko.organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		
		ArrayList<Vozaci> lista2 = (ArrayList<Vozaci>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Vozaci> nadjiSveVozacePoPretplatniku(SistemPretplatnici pretplatnik) {
		ArrayList<Vozaci> lista = new ArrayList<Vozaci>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozaci.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.asc("id"));
		ArrayList<Vozaci> lista2 = (ArrayList<Vozaci>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Vozaci> nadjiSveVozacePoOrganizaciji(Organizacije organizacija) {
		ArrayList<Vozaci> lista = new ArrayList<Vozaci>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozaci.class);
		criteria.createAlias("korisnici", "ko");
		criteria.add(Restrictions.eq("k.organizacija", organizacija));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.asc("id"));
		ArrayList<Vozaci> lista2 = (ArrayList<Vozaci>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

}

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

import pratiBaza.dao.VozilaDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaSaobracajne;

@Repository("voziloDAO")
public class VozilaDAOImpl implements VozilaDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiVozilo(Vozila vozilo) {
		vozilo.setVersion(0);
		vozilo.setIzmenjeno(new Timestamp((new Date()).getTime()));
		vozilo.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(vozilo);
	}

	public void azurirajVozilo(Vozila vozilo) {
		vozilo.setVersion(vozilo.getVersion() + 1);
		vozilo.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(vozilo);
	}

	public void izbrisiVozilo(Vozila vozilo) {
		sessionFactory.getCurrentSession().delete(vozilo);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Vozila nadjiVoziloPoObjektu(Objekti objekti) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.add(Restrictions.eq("objekti", objekti));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Vozila vozilo = (Vozila)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return vozilo;
		}else {
			return null;
		}
		
	}

	@Override
	public Vozila nadjiVoziloPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.add(Restrictions.eq("objekti", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Vozila vozilo = (Vozila)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return vozilo;
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Vozila> vratisvaVozila(Korisnici korisnik, boolean aktivan) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(aktivan) {
			criteria.add(Restrictions.eq("izbrisan", false));
			}
		if(korisnik.getOrganizacija() != null) {
			criteria.createAlias("objekti", "o");
			criteria.add(Restrictions.eq("o.organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.desc("sistemPretplatnici"));
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("objekti"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Vozila> lista2 = (ArrayList<Vozila>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	@Override
	public Vozila vratiVoziloPoSaobracajnoj(VozilaSaobracajne saobracajna) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.add(Restrictions.eq("saobracajna", saobracajna));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Vozila vozilo = (Vozila)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return vozilo;
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Vozila> nadjisvaVozilaPoPretplatniku(SistemPretplatnici pretplatnik) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.asc("id"));
		ArrayList<Vozila> lista2 = (ArrayList<Vozila>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Vozila> nadjisvaVozilaPoOrganizaciji(Organizacije organizacija) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.createAlias("objekti", "o");
		criteria.add(Restrictions.eq("o.organizacija", organizacija));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.asc("id"));
		ArrayList<Vozila> lista2 = (ArrayList<Vozila>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Vozila> nadjisvaVozilaPoObjektima(ArrayList<Objekti> objekti) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.add(Restrictions.in("objekti", objekti));
		ArrayList<Vozila> lista2 = (ArrayList<Vozila>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Vozila> nadjiSvaVozilaBezSaobracajne(Korisnici korisnik, boolean aktivan) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(aktivan) {
			criteria.add(Restrictions.eq("izbrisan", false));
			}
		criteria.add(Restrictions.isNull("saobracajna"));
		if(korisnik.getOrganizacija() != null) {
			criteria.createAlias("objekti", "o");
			criteria.add(Restrictions.eq("o.organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.desc("sistemPretplatnici"));
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("objekti"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Vozila> lista2 = (ArrayList<Vozila>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Vozila> nadjiSvaVozilaBezSaobracajnePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		criteria.add(Restrictions.eq("izbrisan", false));

		criteria.add(Restrictions.isNull("saobracajna"));
		if(organizacija != null) {
			criteria.createAlias("objekti", "o");
			criteria.add(Restrictions.eq("o.organizacija", organizacija));
			}
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("objekti"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Vozila> lista2 = (ArrayList<Vozila>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}
	
}

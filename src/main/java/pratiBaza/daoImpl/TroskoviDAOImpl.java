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

import pratiBaza.dao.TroskoviDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Racuni;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Troskovi;

@Repository("trosakDAO")
public class TroskoviDAOImpl implements TroskoviDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void unesiTrosak(Troskovi trosak) {
		trosak.setVersion(0);
		trosak.setIzmenjeno(new Timestamp((new Date()).getTime()));
		trosak.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(trosak);
	}

	@Override
	public void izmeniTrosak(Troskovi trosak) {
		trosak.setVersion(trosak.getVersion() + 1);
		trosak.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(trosak);
	}

	@Override
	public void izbrisiTrosak(Troskovi trosak) {
		trosak.setIzbrisan(true);
		izmeniTrosak(trosak);
	}

	@Override
	public Troskovi nadjiTrosakPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Troskovi.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Troskovi trosak = (Troskovi)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return trosak;
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Troskovi> nadjiSveTroskovePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Troskovi.class);
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		if(pretplatnik != null) {
			criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		}
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
		}
		if(izbrisan) {
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		criteria.addOrder(Order.desc("datumVreme"));
		ArrayList<Troskovi> lista2 = (ArrayList<Troskovi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Troskovi> nadjiSveTroskove(Korisnici korisnik) {
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Troskovi.class);
			criteria.addOrder(Order.desc("datumVreme"));
			ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
			ArrayList<Troskovi> lista2 = (ArrayList<Troskovi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(lista2 != null) {
				lista.addAll(lista2);
			}
			return lista;
		}else {
			return nadjiSveTroskovePoPretplatniku(korisnik.getSistemPretplatnici(), korisnik.getOrganizacija(), true);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Troskovi> nadjiSvaOdrzavanjaPoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan) {
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Troskovi.class);
		if(pretplatnik != null) {
			criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		}
		if(organizacija != null) {
			criteria.createAlias("objekti", "o");
			criteria.add(Restrictions.eq("o.organizacija", organizacija));
		}
		if(izbrisan) {
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		criteria.add(Restrictions.ne("tipServisa", 0));
		criteria.addOrder(Order.desc("datumVreme"));
		ArrayList<Troskovi> lista2 = (ArrayList<Troskovi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Troskovi> nadjiSvaOdrzavanja(Korisnici korisnik) {
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Troskovi.class);
			if(!korisnik.isSistem() && !korisnik.getSistemPretplatnici().isSistem()) {
				criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
				criteria.add(Restrictions.eq("izbrisan", false));
			}
			if(korisnik.getOrganizacija() != null) {
				criteria.createAlias("objekti", "o");
				criteria.add(Restrictions.eq("o.organizacija", korisnik.getOrganizacija()));
			}
			criteria.add(Restrictions.ne("tipServisa", 0));
			criteria.addOrder(Order.desc("datumVreme"));
			ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
			ArrayList<Troskovi> lista2 = (ArrayList<Troskovi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(lista2 != null) {
				lista.addAll(lista2);
			}
			return lista;
		}else {
			return nadjiSvaOdrzavanjaPoPretplatniku(korisnik.getSistemPretplatnici(), korisnik.getOrganizacija(), true);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Troskovi> nadjiSvuPotrosnjuPoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan) {
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Troskovi.class);
		if(pretplatnik != null) {
			criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		}
		if(organizacija != null) {
			criteria.createAlias("objekti", "o");
			criteria.add(Restrictions.eq("o.organizacija", organizacija));
		}
		if(izbrisan) {
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		criteria.add(Restrictions.eq("tipServisa", 0));
		criteria.addOrder(Order.desc("datumVreme"));
		ArrayList<Troskovi> lista2 = (ArrayList<Troskovi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Troskovi> nadjiSvuPotrosnju(Korisnici korisnik) {
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Troskovi.class);
			criteria.add(Restrictions.eq("tipServisa", 0));
			criteria.addOrder(Order.desc("datumVreme"));
			ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
			ArrayList<Troskovi> lista2 = (ArrayList<Troskovi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(lista2 != null) {
				lista.addAll(lista2);
			}
			return lista;
		}else {
			return nadjiSvuPotrosnjuPoPretplatniku(korisnik.getSistemPretplatnici(), korisnik.getOrganizacija(), true);
		}
	}

	@Override
	public Troskovi nadjiPoslednjiTrosakDo(Timestamp datumVreme, int tipTroska) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Troskovi.class);
		criteria.add(Restrictions.eq("tipServisa", tipTroska));
		criteria.add(Restrictions.lt("datumVreme", datumVreme));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.desc("datumVreme"));
		criteria.setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Troskovi trosak = (Troskovi)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return trosak;
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Troskovi> nadjiSveTroskoveOd(Timestamp datumVremeOd, SistemPretplatnici pretplatnik, Organizacije organizacija) {
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Troskovi.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		criteria.add(Restrictions.eq("izbrisan", false));
		if(organizacija != null) {
			criteria.createAlias("objekti", "o");
			criteria.add(Restrictions.eq("o.organizacija", organizacija));
		}
		criteria.add(Restrictions.ge("datumVreme", datumVremeOd));
		criteria.addOrder(Order.desc("datumVreme"));
		ArrayList<Troskovi> lista2 = (ArrayList<Troskovi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Troskovi> nadjiSveTroskoveUkupno(ArrayList<Objekti> vozila, Timestamp datumVremeOd, Timestamp datumVremeDo, Integer tipTroska){
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		if(vozila != null && !vozila.isEmpty()) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Troskovi.class);
			criteria.add(Restrictions.in("objekti", vozila));
			criteria.add(Restrictions.ge("datumVreme", datumVremeOd));
			criteria.add(Restrictions.lt("datumVreme", datumVremeDo));
			if(tipTroska != null) {
				criteria.add(Restrictions.eq("tipServisa", tipTroska));
				}
			criteria.add(Restrictions.eq("izbrisan", false));
			criteria.addOrder(Order.asc("datumVreme"));
			ArrayList<Troskovi> lista2 = (ArrayList<Troskovi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(lista2 != null) {
				lista.addAll(lista2);
				}
			}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Troskovi> nadjiSvuPotrosnjuPoRacunu(Racuni racun) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Troskovi.class);
		criteria.add(Restrictions.eq("racun", racun));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.desc("datumVreme"));
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		ArrayList<Troskovi> lista2 = (ArrayList<Troskovi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}
}

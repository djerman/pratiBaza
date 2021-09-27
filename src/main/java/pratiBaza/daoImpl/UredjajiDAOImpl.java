package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.UredjajiDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

@Repository("uredjajDAO")
public class UredjajiDAOImpl implements UredjajiDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiUredjaj(Uredjaji uredjaj) {
		uredjaj.setVersion(0);
		uredjaj.setIzmenjeno(new Timestamp((new Date()).getTime()));
		uredjaj.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(uredjaj);
	}

	public void izmeniUredjaj(Uredjaji uredjaj) {
		uredjaj.setVersion(uredjaj.getVersion() + 1);
		uredjaj.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(uredjaj);
	}

	public void izbrisiUredjaj(Uredjaji uredjaj) {
		if(uredjaj.getObjekti() != null) {
			uredjaj.getObjekti().setUredjaji(null);
		}
		if(uredjaj.getSim() != null) {
			uredjaj.getSim().setUredjaji(null);
		}
		if(uredjaj.getSim2() != null) {
			uredjaj.getSim2().setUredjaji(null);
		}
		uredjaj.setObjekti(null);
		uredjaj.setSim(null);
		uredjaj.setSim2(null);
		uredjaj.setAktivno(false);
		uredjaj.setIzbrisan(true);
		izmeniUredjaj(uredjaj);
	}

	public List<Uredjaji> nadjiSveUredjaje(Korisnici korisnik, boolean aktivan) {
		String pretp = "";
		String akt = "";
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			pretp = "u.sistemPretplatnici = :pretplatnik AND u.izbrisan = false AND ";
		}
		if(aktivan) {
			akt = " AND u.aktivan = :akt";
		}
		
		String upit = "Select u FROM Uredjaji u where " + pretp + "(:organizacija is null or u.organizacija = :organizacija) "
				+ akt + " ORDER BY u.sistemPretplatnici.naziv, u.id, u.izbrisan, u.aktivan desc";
		
		TypedQuery<Uredjaji> query = sessionFactory.getCurrentSession().createQuery(upit, Uredjaji.class);
		
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		}
		query.setParameter("organizacija", korisnik.getOrganizacija());
		if(aktivan) {
			query.setParameter("akt", aktivan);
		}
		return query.getResultList();
		/*ArrayList<Uredjaji> lista = new ArrayList<Uredjaji>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uredjaji.class);
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
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
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		criteria.addOrder(Order.asc("objekti"));
		@SuppressWarnings("unchecked")
		ArrayList<Uredjaji> lista2 = (ArrayList<Uredjaji>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
		*/
	}

	public List<Uredjaji> nadjiSveAktivneSlobodneUredjajePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		String upit = "SELECT u FROM Uredjaji u where u.objekti IS NULL AND (:pretplatnik IS NULL or u.sistemPretplatnici = :pretplatnik) AND "
				+ "(:organizacija IS NULL or u.organizacija = :organizacija) "
				+ "AND u.aktivan = true AND u.izbrisan = false ORDER BY u.id asc";
		
		TypedQuery<Uredjaji> query = sessionFactory.getCurrentSession().createQuery(upit, Uredjaji.class);
		
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);

		return query.getResultList();
		/*
		ArrayList<Uredjaji> lista = new ArrayList<Uredjaji>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uredjaji.class);
		criteria.add(Restrictions.isNull("objekti"));
		criteria.add(Restrictions.eq("aktivan", true));
		criteria.add(Restrictions.eq("izbrisan", false));
		if(pretplatnik != null ) {
			criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		}
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija",  organizacija));
		}
		criteria.addOrder(Order.asc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Uredjaji> lista2 = (ArrayList<Uredjaji>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
		*/
	}

	@Override
	public List<Uredjaji> nadjiSveAktivneSlobodneUredjaje(Korisnici korisnik, SistemPretplatnici pretplatnik, Organizacije organizacija) {
		if(korisnik.getSistemPretplatnici().isSistem()) {
			return nadjiSveAktivneSlobodneUredjajePoPretplatniku(null, null);
		}else {
			return nadjiSveAktivneSlobodneUredjajePoPretplatniku(pretplatnik, organizacija);
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Uredjaji> nadjiSveAktivneUredjaje(Korisnici korisnik, Uredjaji uredjaj) {
		String pretp = "";
		if(!korisnik.getSistemPretplatnici().isSistem() && !korisnik.isSistem()) {
			
		}else {
			pretp = "u.sistemPretplatnici = :pretplatnik AND u.izbrisan = false AND ";
		}
		
		String upit = "Select u FROM Uredjaji u where " + pretp + "(:organizacija is null or u.organizacija = :organizacija) AND u.objekti IS NULL"
				+ " AND u.aktivan = true AND u.izbrisan = false ORDER BY u.sistemPretplatnici.naziv, u.id, u.izbrisan, u.aktivan desc";
		
		TypedQuery<Uredjaji> query = sessionFactory.getCurrentSession().createQuery(upit, Uredjaji.class);
		
		if(!korisnik.getSistemPretplatnici().isSistem() && !korisnik.isSistem()) {
			query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		}else {
			
		}
		query.setParameter("organizacija", korisnik.getOrganizacija());
		return query.getResultList();
		/*
		ArrayList<Uredjaji> lista = new ArrayList<Uredjaji>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uredjaji.class);
		criteria.add(Restrictions.eq("aktivan", true));
		criteria.add(Restrictions.eq("izbrisan", false));
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			
		}else {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
		}
		criteria.add(Restrictions.isNull("objekti"));
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Uredjaji> lista2 = (ArrayList<Uredjaji>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
		*/
	}

	@Override
	public Uredjaji nadjiUredjajPoId(int id) {
		String upit = "SELECT u FROM Uredjaji u where u.id = :id";
		TypedQuery<Uredjaji> query = sessionFactory.getCurrentSession().createQuery(upit, Uredjaji.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uredjaji.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Uredjaji uredjaj = (Uredjaji)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return uredjaj;
		}else {
			return null;
		}
		*/
	}

	@Override
	public Uredjaji nadjiUredjajPoKodu(String kod) {
		String upit = "SELECT u FROM Uredjaji u where u.kod = :kod";
		TypedQuery<Uredjaji> query = sessionFactory.getCurrentSession().createQuery(upit, Uredjaji.class);
		query.setParameter("kod", kod);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uredjaji.class);
		criteria.add(Restrictions.eq("kod", kod));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Uredjaji uredjaj = (Uredjaji)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return uredjaj;
		}else {
			return null;
		}
		*/
	}
	
}

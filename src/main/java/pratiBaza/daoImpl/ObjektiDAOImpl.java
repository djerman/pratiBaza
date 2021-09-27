package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
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
		//sessionFactory.getCurrentSession().persist(objekat);
		sessionFactory.getCurrentSession().saveOrUpdate(objekat);
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

	public List<Objekti> vratiSveObjekte(Korisnici korisnik, boolean aktivan) {
		String pretp = "";
		String akt = "";
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			pretp = "obj.sistemPretplatnici = :pretplatnik AND obj.izbrisan = false AND ";
		}
		if(aktivan) {
			akt = " AND obj.aktivan = :akt";
		}
		
		String upit = "Select obj FROM Objekti obj where " + pretp + "(:organizacija is null or obj.organizacija = :organizacija) "
				+ akt + " ORDER BY obj.sistemPretplatnici, obj.izbrisan, obj.aktivan, obj.id desc";
		
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		}
		query.setParameter("organizacija", korisnik.getOrganizacija());
		if(aktivan) {
			query.setParameter("akt", aktivan);
		}
		return query.getResultList();
		/*
		ArrayList<Objekti> lista = new ArrayList<Objekti>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
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
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Objekti> lista2 = (ArrayList<Objekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
		*/
	}

	public List<Objekti> vratiObjektePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean aktivan) {
		String akt = "";
		if(aktivan) {
			akt = " AND obj.aktivan = :akt";
		}
		String upit = "Select obj FROM Objekti obj where obj.sistemPretplatnici = :pretplatnik AND obj.izbrisan = false AND "
				+ "(:organizacija is null or obj.organizacija = :organizacija) "
				+ akt + " ORDER BY obj.sistemPretplatnici, obj.izbrisan, obj.aktivan, obj.id desc";
		
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);
		if(aktivan) {
			query.setParameter("akt", aktivan);
		}
		return query.getResultList();
		/*
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
		*/
	}

	public List<Objekti> vratiSvaVozila(Korisnici korisnik, boolean aktivan) {
		String pretp = "";
		String akt = "";
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			pretp = "obj.sistemPretplatnici = :pretplatnik AND obj.izbrisan = false AND ";
		}
		if(aktivan) {
			akt = " AND obj.aktivan = :akt";
		}
		
		String upit = "Select obj FROM Objekti obj where " + pretp + "(:organizacija is null or obj.organizacija = :organizacija) "
				+ akt + " AND obj.tip = true ORDER BY obj.sistemPretplatnici, obj.izbrisan, obj.aktivan, obj.id desc";
		
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		}
		query.setParameter("organizacija", korisnik.getOrganizacija());
		if(aktivan) {
			query.setParameter("akt", aktivan);
		}
		return query.getResultList();
		/*
		ArrayList<Objekti> lista = new ArrayList<Objekti>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(aktivan) {
			criteria.add(Restrictions.eq("aktivan", true));
			criteria.add(Restrictions.eq("izbrisan", false));
			}
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
		*/
	}

	public List<Objekti> vratiObjektePoGrupi(Grupe grupa) {
		// TODO Auto-generated method stub
		return null;
	}

	public Objekti nadjiObjekatPoId(int id) {
		String upit = "SELECT obj FROM Objekti obj where obj.id = :id";
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Objekti objekat = (Objekti)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return objekat;
		}else {
			return null;
		}
		*/
	}

	public List<Objekti> vratiSveObjekte(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		String upit = "Select obj FROM Objekti obj where obj.sistemPretplatnici = :pretplatnik AND obj.izbrisan = false AND "
				+ "(:organizacija is null or obj.organizacija = :organizacija)"
				+ " AND obj.aktivan = true ORDER BY  obj.id, obj.aktivan desc, obj.izbrisan";
		
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);
		return query.getResultList();
		/*
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
		*/
	}

	@Override
	public List<Objekti> vratiSveObjekteVozila(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		String upit = "Select obj FROM Objekti obj where obj.sistemPretplatnici = :pretplatnik AND obj.izbrisan = false AND "
				+ "(:organizacija is null or obj.organizacija = :organizacija) AND obj.aktivan = true AND obj.tip = true ORDER BY obj.id desc";
		
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);
		return query.getResultList();
		/*
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
		*/
	}

	@Override
	public Objekti nadjiObjekatPoUredjaju(Uredjaji uredjaj) {
		String upit = "SELECT obj FROM Objekti obj where obj.uredjaj = :uredjaj";
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		query.setParameter("uredjaj", uredjaj);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("uredjaji", uredjaj));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Objekti objekat = (Objekti)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return objekat;
		}else {
			return null;
		}
		*/
	}

	@Override
	public List<Objekti> nadjiSveObjekteSavozilom(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		String upit = "Select obj FROM Objekti obj where obj.sistemPretplatnici = :pretplatnik AND obj.izbrisan = false AND "
				+ "(:organizacija is null or obj.organizacija = :organizacija) AND obj.vozilo IS NOT NULL"
				+ " AND obj.aktivan = true ORDER BY obj.id desc";
		
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);
		return query.getResultList();
		/*
		ArrayList<Objekti> lista = new ArrayList<Objekti>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("sistemPretplatnici",pretplatnik));
		criteria.add(Restrictions.isNotNull("vozilo"));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.add(Restrictions.eq("aktivan", true));
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
		}
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Objekti> lista2 = (ArrayList<Objekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
		*/
	}
	
	@Override
	public List<Objekti> nadjiSveObjekteBezVozila(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		String upit = "Select obj FROM Objekti obj where obj.sistemPretplatnici = :pretplatnik AND obj.izbrisan = false AND "
				+ "(:organizacija is null or obj.organizacija = :organizacija) AND obj.vozilo IS NULL"
				+ " AND obj.aktivan = true AND obj.tip = true ORDER BY obj.id desc";
		
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);
		return query.getResultList();
		/*
		ArrayList<Objekti> lista = new ArrayList<Objekti>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("sistemPretplatnici",pretplatnik));
		criteria.add(Restrictions.isNull("vozilo"));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.add(Restrictions.eq("aktivan", true));
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
		}
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		ArrayList<Objekti> lista2 = (ArrayList<Objekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
		*/
	}

	@Override
	public Objekti nadjiObjekatSadrzi(SistemPretplatnici pretplatnik, String oznaka) {
		String upit = "Select obj FROM Objekti obj where obj.sistemPretplatnici = :pretplatnik AND obj.izbrisan = false AND "
				+ "obj.oznaka like '@':oznaka'@' AND obj.aktivan = true ORDER BY obj.id desc";
		
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		
		query.setParameter("pretplatnik", pretplatnik);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Objekti.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		criteria.add(Restrictions.ilike("oznaka", oznaka, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("aktivan", true));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Objekti objekat = (Objekti)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return objekat;
		}else {
			return null;
		}
		*/
	}
	
}

package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
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
		String upit = "SELECT v FROM Vozila v WHERE v.objekti = :objekti";
		TypedQuery<Vozila> query = sessionFactory.getCurrentSession().createQuery(upit, Vozila.class);
		query.setParameter("objekti", objekti);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.add(Restrictions.eq("objekti", objekti));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Vozila vozilo = (Vozila)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return vozilo;
		}else {
			return null;
		}*/
	}

	@Override
	public Vozila nadjiVoziloPoId(int id) {
		String upit = "SELECT v FROM Vozila v WHERE v.id = :id";
		TypedQuery<Vozila> query = sessionFactory.getCurrentSession().createQuery(upit, Vozila.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.add(Restrictions.eq("objekti", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Vozila vozilo = (Vozila)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return vozilo;
		}else {
			return null;
		}*/
	}

	@Override
	public ArrayList<Vozila> vratisvaVozila(Korisnici korisnik, boolean aktivan) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		String upit = "SELECT v FROM Vozila v WHERE (v.sistemPretplatnici IS NULL OR v.sistemPretplatnici = :pretplatnik)"
				+ " AND (:organizacija IS NULL OR v.organizacija = :organizacija)"
				+ " ORDER BY v.id desc";
		TypedQuery<Vozila> query = sessionFactory.getCurrentSession().createQuery(upit, Vozila.class);
		query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		query.setParameter("organizacija", korisnik.getOrganizacija());
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
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
		}*/
		return lista;
	}

	@Override
	public Vozila vratiVoziloPoSaobracajnoj(VozilaSaobracajne saobracajna) {
		String upit = "SELECT v FROM Vozila v WHERE v.saobracajna = :saobracajna";
		TypedQuery<Vozila> query = sessionFactory.getCurrentSession().createQuery(upit, Vozila.class);
		query.setParameter("saobracajna", saobracajna);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.add(Restrictions.eq("saobracajna", saobracajna));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Vozila vozilo = (Vozila)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return vozilo;
		}else {
			return null;
		}*/
	}

	@Override
	public ArrayList<Vozila> nadjisvaVozilaPoPretplatniku(SistemPretplatnici pretplatnik) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		String upit = "SELECT v FROM Vozila v WHERE v.sistemPretplatnici = :pretplatnik"
				+ " AND v.izbrisan = false"
				+ " ORDER BY v.id desc";
		TypedQuery<Vozila> query = sessionFactory.getCurrentSession().createQuery(upit, Vozila.class);
		query.setParameter("pretplatnik", pretplatnik);
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.asc("id"));
		ArrayList<Vozila> lista2 = (ArrayList<Vozila>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}*/
	}

	@Override
	public ArrayList<Vozila> nadjisvaVozilaPoOrganizaciji(Organizacije organizacija) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		String upit = "SELECT v FROM Vozila v WHERE v.objekti.organizacija = :organizacija"
				+ " AND v.izbrisan = false"
				+ " ORDER BY v.id desc";
		TypedQuery<Vozila> query = sessionFactory.getCurrentSession().createQuery(upit, Vozila.class);
		query.setParameter("organizacija", organizacija);
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.createAlias("objekti", "o");
		criteria.add(Restrictions.eq("o.organizacija", organizacija));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.asc("id"));
		ArrayList<Vozila> lista2 = (ArrayList<Vozila>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}*/
	}

	@Override
	public ArrayList<Vozila> nadjisvaVozilaPoObjektima(ArrayList<Objekti> objekti) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		String upit = "SELECT v FROM Vozila v WHERE v.objekti IN :objekti"
				+ " AND v.izbrisan = false"
				+ " ORDER BY v.id desc";
		TypedQuery<Vozila> query = sessionFactory.getCurrentSession().createQuery(upit, Vozila.class);
		query.setParameter("objekti", objekti);
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.add(Restrictions.in("objekti", objekti));
		ArrayList<Vozila> lista2 = (ArrayList<Vozila>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}*/
	}

	@Override
	public ArrayList<Vozila> nadjiSvaVozilaBezSaobracajne(Korisnici korisnik, boolean aktivan) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		String upit = "SELECT v FROM Vozila v WHERE v.sistemPretplatnici = :pretplatnik"
				+ " AND (:organizacija IS NULL OR v.objekti.organizacija = :organizacija)"
				+ " AND v.saobracajna IS NULL"
				+ " AND v.izbrisan = false"
				+ " ORDER BY v.id desc";
		TypedQuery<Vozila> query = sessionFactory.getCurrentSession().createQuery(upit, Vozila.class);
		query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		query.setParameter("organizacija", korisnik.getOrganizacija());
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
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
		}*/
		
	}

	@Override
	public ArrayList<Vozila> nadjiSvaVozilaBezSaobracajnePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		ArrayList<Vozila> lista = new ArrayList<Vozila>();
		String upit = "SELECT v FROM Vozila v WHERE v.sistemPretplatnici = :pretplatnik"
				+ " AND (:organizacija IS NULL OR v.organizacija = :organizacija)"
				+ " AND v.saobracajna IS NULL"
				+ " AND v.izbrisan = false"
				+ " ORDER BY v.id desc";
		TypedQuery<Vozila> query = sessionFactory.getCurrentSession().createQuery(upit, Vozila.class);
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vozila.class);
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
		}*/
		return lista;
	}

	@Override
	public Vozila nadjiVoziloPoRegistraciji(SistemPretplatnici pretplatnik, String registracija) {
		String upit = "SELECT v FROM Vozila v WHERE v.sistemPretplatnici = :pretplatnik"
				+ " AND (lower(v.registracija) like lower(concat('%',:registracija,'%')))";
		
		TypedQuery<Vozila> query = sessionFactory.getCurrentSession().createQuery(upit, Vozila.class);
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("registracija", registracija);
		
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
	
}

package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.OrganizacijeDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("organizacijaDAO")
public class OrganizacijeDAOImpl implements OrganizacijeDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiOrganizacije(Organizacije organizacija) {
		organizacija.setVersion(0);
		organizacija.setIzmenjeno(new Timestamp((new Date()).getTime()));
		organizacija.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(organizacija);
	}

	public void azurirajOrganizacije(Organizacije organizacija) {
		organizacija.setVersion(organizacija.getVersion() + 1);
		organizacija.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(organizacija);
	}

	public void izbrisiOrganizacije(Organizacije organizacija) {
		organizacija.setAktivan(false);
		organizacija.setIzbrisan(true);
		azurirajOrganizacije(organizacija);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ArrayList<Organizacije> nadjiSveOrganizacije(Korisnici korisnik, boolean aktivan) {
		ArrayList<Organizacije> lista = new ArrayList<Organizacije>();
		String upit = "SELECT o FROM Organizacije o WHERE (:pretplatnik IS NULL OR o.sistemPretplatnici = :pretplatnik)"
				+ " AND (:id IS NULL OR o.id = :id)"
				+ " AND (:izbrisan IS NULL OR o.izbrisan = :izbrisan)"
				+ " AND (:aktivan IS NULL or o.aktivan = :aktivan)"
				+ " ORDER BY o.sistemPretplatnici.naziv asc, o.izbrisan asc, o.aktivan desc, o.naziv asc";
		TypedQuery<Organizacije> query = sessionFactory.getCurrentSession().createQuery(upit, Organizacije.class);
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
			query.setParameter("izbrisan", false);
		}else {
			query.setParameter("pretplatnik", null);
			query.setParameter("izbrisan", null);
		}
		if(korisnik.getOrganizacija() != null) {
			query.setParameter("id", korisnik.getOrganizacija().getId());
		}else {
			query.setParameter("id", null);
		}
		if(aktivan){
			query.setParameter("aktivan", aktivan);
			query.setParameter("izbrisan", false);
		}else {
			query.setParameter("aktivan", null);
			query.setParameter("izbrisan", null);
		}
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			// TODO: handle exception
		}
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Organizacije.class);
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("id", korisnik.getOrganizacija().getId()));
		}
		if(aktivan) {
			criteria.add(Restrictions.eq("aktivan", true));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("aktivan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Organizacije> lista2 = (ArrayList<Organizacije>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}*/
		return lista;
	}

	public Organizacije nadjiOrganizacijuPoId(int id) {
		String upit = "SELECT o FROM Organizacije o WHERE obj.id = :id";
		TypedQuery<Organizacije> query = sessionFactory.getCurrentSession().createQuery(upit, Organizacije.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Organizacije.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Organizacije organizacija = (Organizacije)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return organizacija;
		}else {
			return null;
		}*/
		
	}

	public ArrayList<Organizacije> nadjiSveOrganizacije(SistemPretplatnici pretplatnik, boolean aktivan) {
		ArrayList<Organizacije> lista = new ArrayList<Organizacije>();
		String akt = "";
		if(aktivan) {
			akt = " AND o.aktivan = :aktivan";
		}
		String upit = "SELECT o FROM Organizacije o WHERE o.sistemPretplatnici = :pretplatnik"
				+ " AND o.izbrisan = false"
				+ akt
				+ " ORDER BY o.naziv asc";
		TypedQuery<Organizacije> query = sessionFactory.getCurrentSession().createQuery(upit, Organizacije.class);
		query.setParameter("pretplatnik", pretplatnik);
		if(aktivan){
			query.setParameter("aktivan", aktivan);
		}else {
			query.setParameter("aktivan", null);
		}
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			// TODO: handle exception
		}
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Organizacije.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		criteria.add(Restrictions.eq("izbrisan", false));
		if(aktivan) {
			criteria.add(Restrictions.eq("aktivan", true));
		}
		criteria.addOrder(Order.desc("id"));
		ArrayList<Organizacije> lista2 = (ArrayList<Organizacije>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}*/
		return lista;
	}
	
}

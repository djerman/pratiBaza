package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.ProjektiDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Projekti;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("projektDAO")
public class ProjektiDAOImpl implements ProjektiDAO{

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
	public void unesiProjekat(Projekti projekat) {
		projekat.setVersion(0);
		projekat.setIzmenjeno(new Timestamp((new Date()).getTime()));
		projekat.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(projekat);
	}

	@Override
	public void izmeniProjekat(Projekti projekat) {
		projekat.setVersion(projekat.getVersion() + 1);
		projekat.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(projekat);
	}

	@Override
	public void izbrisiProjekat(Projekti projekat) {
		projekat.setIzbrisan(true);
		izmeniProjekat(projekat);
	}

	@Override
	public Projekti nadjiProjekatPoId(int id) {
		String upit = "SELECT p FROM Projekti p WHERE p.id = :id";
		TypedQuery<Projekti> query = sessionFactory.getCurrentSession().createQuery(upit, Projekti.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Projekti.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Projekti projekt = (Projekti)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return projekt;
		}else {
			return null;
		}*/
	}

	@Override
	public ArrayList<Projekti> nadjiSveProjekte(Korisnici korisnik) {
		ArrayList<Projekti> lista = new ArrayList<>();
		String upit = "SELECT p FROM Projekti p WHERE (:pretplatnik.isSistem OR p.sistemPretplatnici = :pretplatnik)"
				+ " ORDER BY p.id DESC";
		TypedQuery<Projekti> query = sessionFactory.getCurrentSession().createQuery(upit, Projekti.class);
		query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
					}
		return lista;
		/*if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Projekti.class);
			criteria.addOrder(Order.desc("id"));
			ArrayList<Projekti> lista = new ArrayList<>();
			ArrayList<Projekti> lista2 = (ArrayList<Projekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(lista2 != null) {
				lista.addAll(lista2);
			}
			return lista;
		}else {
			return nadjiSveProjektePoPretplatniku(korisnik.getSistemPretplatnici(), korisnik.getOrganizacija(), true);
		}*/
	}

	@Override
	public ArrayList<Projekti> nadjiSveProjektePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan) {
		ArrayList<Projekti> lista = new ArrayList<>();
		String upit = "SELECT p FROM Projekti p WHERE (:pretplatnik IS NULL OR p.sistemPretplatnici = :pretplatnik)"
				+ " AND (:organizacija IS NULL OR p.organizacija = :organizacija)"
				+ " AND (:izbrisan = false OR p.izbrisan = false)"
				+ " ORDER BY p.id DESC";
		TypedQuery<Projekti> query = sessionFactory.getCurrentSession().createQuery(upit, Projekti.class);
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);
		query.setParameter("izbrisan", izbrisan);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
					}

		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Projekti.class);
		if(pretplatnik != null) {
			criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		}
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
		}
		if(izbrisan) {
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		criteria.addOrder(Order.desc("id"));
		ArrayList<Projekti> lista2 = (ArrayList<Projekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}*/
		return lista;
	}
}

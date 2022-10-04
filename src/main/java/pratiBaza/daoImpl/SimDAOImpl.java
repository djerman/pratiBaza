package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SimDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Sim;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("simDAO")
public class SimDAOImpl implements SimDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiSim(Sim sim) {
		sim.setVersion(0);
		sim.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sim.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(sim);
	}

	public void azurirajSim(Sim sim) {
		sim.setVersion(sim.getVersion() + 1);
		sim.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(sim);
	}

	public void izbrisiSim(Sim sim) {
		if(sim.getUredjaji() != null) {
			if(sim.equals(sim.getUredjaji().getSim())) {
				sim.getUredjaji().setSim(null);
			}
			if(sim.equals(sim.getUredjaji().getSim2())) {
				sim.getUredjaji().setSim2(null);
			}
		}
		sim.setAktivno(false);
		sim.setIzbrisan(true);
		azurirajSim(sim);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ArrayList<Sim> vratiSveSimKartice(Korisnici korisnik, boolean aktivan) {
		ArrayList<Sim> lista = new ArrayList<Sim>();
		String pretp = "";
		String akt = "";
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			pretp = "sim.sistemPretplatnici = :pretplatnik AND sim.izbrisan = false AND ";
		}
		if(aktivan) {
			akt = " AND sim.aktivan = :akt";
		}
		
		String upit = "SELECT sim FROM Sim sim WHERE" + " " + pretp + ""
				+ "(:organizacija is null or sim.organizacija = :organizacija) "
				+ akt + " ORDER BY sim.sistemPretplatnici.naziv asc, sim.izbrisan asc, sim.aktivan desc, sim.id desc";
		
		TypedQuery<Sim> query = sessionFactory.getCurrentSession().createQuery(upit, Sim.class);
		
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		}
		query.setParameter("organizacija", korisnik.getOrganizacija());
		if(aktivan) {
			query.setParameter("akt", aktivan);
		}
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			// TODO: handle exception
		}
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sim.class);
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
		ArrayList<Sim> lista2 = (ArrayList<Sim>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
			}*/
		return lista;
	}

	@Override
	public Sim nadjiSimPoID(int id) {
		String upit = "SELECT sim FROM Sim obj WHERE sim.id = :id";
		TypedQuery<Sim> query = sessionFactory.getCurrentSession().createQuery(upit, Sim.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sim.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Sim sim = (Sim)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return sim;
		}else {
			return null;
		}*/
		
	}

	@Override
	public ArrayList<Sim> vratiSveAktivneSimKartice(SistemPretplatnici pretplatnici, Organizacije organizacija, Sim sim) {
		ArrayList<Sim> lista = new ArrayList<Sim>();
		if(sim != null) {
			lista.add(sim);
		}
		String pretp = "";

		if(pretplatnici != null) {
			pretp = "sim.sistemPretplatnici = :pretplatnik AND sim.izbrisan = false AND ";
		}

		String upit = "SELECT sim FROM Sim sim WHERE" + " " + pretp + ""
				+ "(:organizacija IS NULL OR sim.organizacija = :organizacija)"
				+ " AND sim.aktivan = true"
				+ " AND sim.izbrisan = false"
				+ " ORDER BY sim.sistemPretplatnici.naziv asc, sim.izbrisan asc, sim.aktivan desc, sim.id desc";
		
		TypedQuery<Sim> query = sessionFactory.getCurrentSession().createQuery(upit, Sim.class);
		
		if(pretplatnici != null) {
			query.setParameter("pretplatnik", pretplatnici != null);
		}
		query.setParameter("organizacija", organizacija);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			// TODO: handle exception
		}
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sim.class);
		criteria.add(Restrictions.isNull("uredjaji"));
		criteria.add(Restrictions.eq("aktivan", true));
		criteria.add(Restrictions.eq("izbrisan", false));
		if(pretplatnici != null) {
			criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnici));
		}
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
		}
		criteria.addOrder(Order.desc("id"));
		ArrayList<Sim> lista2 = (ArrayList<Sim>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}*/
		return lista;
	}

	@Override
	public ArrayList<Sim> vratiSveSlobodneSim(Korisnici korisnik, SistemPretplatnici pretplatnici, Organizacije organizacija, Sim sim) {
		ArrayList<Sim> lista = vratiSveAktivneSimKartice(pretplatnici, organizacija, sim);
		if(korisnik.getSistemPretplatnici().isSistem()) {
			ArrayList<Sim> lista2 = vratiSveAktivneSimKartice(korisnik.getSistemPretplatnici(), null, null);
			if(lista2 != null) {
				lista.addAll(lista2);
			}
		}
		return lista;
	}
	
	
}

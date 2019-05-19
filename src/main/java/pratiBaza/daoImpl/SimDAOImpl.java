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

	@SuppressWarnings("unchecked")
	public ArrayList<Sim> vratiSveSimKartice(Korisnici korisnik, boolean aktivan) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sim.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
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
		ArrayList<Sim> lista = (ArrayList<Sim>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}

	@Override
	public Sim nadjiSimPoID(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sim.class);
		criteria.add(Restrictions.eq("id", id));
		Sim sim = (Sim)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return sim;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Sim> vratiSveAktivneSimKartice(SistemPretplatnici pretplatnici, Organizacije organizacija, Sim sim) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sim.class);
		criteria.add(Restrictions.eq("aktivan", true));
		criteria.add(Restrictions.eq("izbrisan", false));
		if(pretplatnici != null) {
			criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnici));
		}else {
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
		}
		criteria.add(Restrictions.isNull("uredjaji"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Sim> lista = (ArrayList<Sim>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(sim != null) {
			lista.add(sim);
		}
		return lista;
	}
	
}

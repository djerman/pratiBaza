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
import pratiBaza.dao.VozilaNaloziDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaNalozi;

@Repository("voziloNalogDAO")
public class VozilaNaloziDAOImpl implements VozilaNaloziDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiVoziloNalog(VozilaNalozi nalog) {
		nalog.setVersion(0);
		nalog.setKreirano(new Timestamp((new Date()).getTime()));
		nalog.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(nalog);
	}

	@Override
	public void izmeniVoziloNalog(VozilaNalozi nalog) {
		nalog.setVersion(nalog.getVersion() + 1);
		nalog.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(nalog);;
	}

	@Override
	public void izbrisiVoziloNalog(VozilaNalozi nalog) {
		nalog.setIzbrisan(true);
		izmeniVoziloNalog(nalog);
	}

	@Override
	public VozilaNalozi nadjiVoziloNalog(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaNalozi.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozilaNalozi)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@Override
	public VozilaNalozi nadjiVoziloNalogPoVozilu(Vozila vozilo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaNalozi.class);
		criteria.add(Restrictions.eq("vozilo", vozilo));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.desc("id")).setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozilaNalozi)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozilaNalozi> nadjiSveVozilaNalogePoVozilu(Vozila vozilo) {
		ArrayList<VozilaNalozi> lista = new ArrayList<VozilaNalozi>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaNalozi.class);
		criteria.add(Restrictions.eq("vozilo", vozilo));
		criteria.add(Restrictions.eq("izbrisan", false));
		ArrayList<VozilaNalozi> lista2 = (ArrayList<VozilaNalozi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozilaNalozi> nadjiSveVozilaNaloge(Korisnici korisnik) {
		ArrayList<VozilaNalozi> lista = new ArrayList<VozilaNalozi>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaNalozi.class);
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.createAlias("vozilo", "v");
			criteria.createAlias("v.objekti", "o");
			criteria.add(Restrictions.eq("o.organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("ocekivaniPolazak"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozilaNalozi> lista2 = (ArrayList<VozilaNalozi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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
	public ArrayList<VozilaNalozi> nadjiNalogeZaGrupuUPeriodu(ArrayList<Objekti> objekti, Timestamp pocetak, Timestamp kraj) {
		ArrayList<VozilaNalozi> lista = new ArrayList<VozilaNalozi>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaNalozi.class);
		//criteria.createAlias("vozilo", "v");
		//criteria.add(Restrictions.in("v.objekti", objekti));
		criteria.add(Restrictions.in("vozilo", objekti));
		criteria.add(Restrictions.eq("izbrisan", false));
		ArrayList<VozilaNalozi> lista2 = (ArrayList<VozilaNalozi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}
	
}

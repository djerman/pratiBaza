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

import pratiBaza.dao.VozaciLicnaDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciLicna;

@Repository("vozacLicnaDAO")
public class VozaciLicnaDAOImpl implements VozaciLicnaDAO{
	
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiVozacLicna(VozaciLicna licna) {
		licna.setVersion(0);
		licna.setKreirano(new Timestamp((new Date()).getTime()));
		licna.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(licna);
	}

	@Override
	public void izmeniVozacLicna(VozaciLicna licna) {
		licna.setVersion(licna.getVersion() + 1);
		licna.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(licna);
	}

	@Override
	public void izbrisiVozacLicna(VozaciLicna licna) {
		licna.setIzbrisan(true);
		izmeniVozacLicna(licna);
	}

	@Override
	public VozaciLicna nadjiVozacLicnaPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLicna.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozaciLicna)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@Override
	public VozaciLicna nadjiVozacLicnaPoVozacu(Korisnici vozac) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLicna.class);
		criteria.add(Restrictions.eq("vozaci", vozac));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.desc("id")).setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozaciLicna)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozaciLicna> nadjiSveVozacLicnaPoVozacu(Korisnici vozac) {
		ArrayList<VozaciLicna> lista = new ArrayList<VozaciLicna>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLicna.class);
		criteria.add(Restrictions.eq("vozaci", vozac));
		criteria.add(Restrictions.eq("izbrisan", false));
		ArrayList<VozaciLicna> lista2 = (ArrayList<VozaciLicna>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozaciLicna> nadjiSveVozacLicna(Korisnici korisnik) {
		ArrayList<VozaciLicna> lista = new ArrayList<VozaciLicna>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLicna.class);
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			
		}else {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.createAlias("vozaci", "v");
			criteria.add(Restrictions.eq("v.organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.asc("vaziDo"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozaciLicna> lista2 = (ArrayList<VozaciLicna>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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
}

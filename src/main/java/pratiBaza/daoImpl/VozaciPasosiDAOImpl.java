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

import pratiBaza.dao.VozaciPasosiDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozaci;
import pratiBaza.tabele.VozaciPasosi;

@Repository("vozacPasosDAO")
public class VozaciPasosiDAOImpl implements VozaciPasosiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiVozacPasos(VozaciPasosi pasos) {
		pasos.setVersion(0);
		pasos.setKreirano(new Timestamp((new Date()).getTime()));
		pasos.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(pasos);
	}

	@Override
	public void izmeniVozacPasos(VozaciPasosi pasos) {
		pasos.setVersion(pasos.getVersion() + 1);
		pasos.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(pasos);
	}

	@Override
	public void izbrisiVozacPasos(VozaciPasosi pasos) {
		pasos.setIzbrisan(true);
		izmeniVozacPasos(pasos);
	}

	@Override
	public VozaciPasosi nadjiVozacPasosPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciPasosi.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozaciPasosi)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@Override
	public VozaciPasosi nadjiVozacPasosPoVozacu(Vozaci vozac) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciPasosi.class);
		criteria.add(Restrictions.eq("vozaci", vozac));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.desc("id")).setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozaciPasosi)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozaciPasosi> nadjiSveVozacPasosPoVozacu(Vozaci vozac) {
		ArrayList<VozaciPasosi> lista = new ArrayList<VozaciPasosi>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciPasosi.class);
		criteria.createAlias("vozaci", "v");
		criteria.add(Restrictions.eq("v.korisnici", vozac));
		criteria.add(Restrictions.eq("izbrisan", false));
		ArrayList<VozaciPasosi> lista2 = (ArrayList<VozaciPasosi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozaciPasosi> nadjiSveVozacPasos(Korisnici korisnik) {
		ArrayList<VozaciPasosi> lista = new ArrayList<VozaciPasosi>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciPasosi.class);
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			
		}else {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.createAlias("vozaci", "v");
			criteria.createAlias("v.korisnici", "k");
			criteria.add(Restrictions.eq("k.organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.asc("vaziDo"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozaciPasosi> lista2 = (ArrayList<VozaciPasosi>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

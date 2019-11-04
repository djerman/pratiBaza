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
import pratiBaza.dao.VozaciLicenceDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozaci;
import pratiBaza.tabele.VozaciLicence;


@Repository("vozacLicencaDAO")
public class VozaciLicenceDAOImpl implements VozaciLicenceDAO{
	
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void unesiVozacLicenca(VozaciLicence licenca) {
		licenca.setVersion(0);
		licenca.setKreirano(new Timestamp((new Date()).getTime()));
		licenca.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(licenca);
	}

	@Override
	public void izmeniVozacLicenca(VozaciLicence licenca) {
		licenca.setVersion(licenca.getVersion() + 1);
		licenca.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(licenca);
	}

	@Override
	public void izbrisiVozacLicenca(VozaciLicence licenca) {
		licenca.setIzbrisan(true);
		izmeniVozacLicenca(licenca);
	}

	@Override
	public VozaciLicence nadjiVozacLicencaPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLicence.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozaciLicence)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@Override
	public VozaciLicence nadjiVozacLicencaPoVozacu(Vozaci vozac) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLicence.class);
		criteria.add(Restrictions.eq("vozaci", vozac));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.desc("id")).setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozaciLicence)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozaciLicence> nadjiSveVozacLicencaPoVozacu(Vozaci vozac) {
		ArrayList<VozaciLicence> lista = new ArrayList<VozaciLicence>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLicence.class);
		criteria.add(Restrictions.eq("vozaci", vozac));
		criteria.add(Restrictions.eq("izbrisan", false));
		ArrayList<VozaciLicence> lista2 = (ArrayList<VozaciLicence>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozaciLicence> nadjiSveVozacLicenca(Korisnici korisnik) {
		ArrayList<VozaciLicence> lista = new ArrayList<VozaciLicence>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLicence.class);
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
		ArrayList<VozaciLicence> lista2 = (ArrayList<VozaciLicence>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

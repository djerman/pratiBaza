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

import pratiBaza.dao.VozaciLekarskoDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciLekarsko;

@Repository("vozacLekarskoDAO")
public class VozaciLekarskoDAOImpl implements VozaciLekarskoDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiVozacLekarsko(VozaciLekarsko lekarsko) {
		lekarsko.setVersion(0);
		lekarsko.setKreirano(new Timestamp((new Date()).getTime()));
		lekarsko.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(lekarsko);
	}

	@Override
	public void izmeniVozacLekarsko(VozaciLekarsko lekarsko) {
		lekarsko.setVersion(lekarsko.getVersion() + 1);
		lekarsko.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(lekarsko);
	}

	@Override
	public void izbrisiVozacLekarsko(VozaciLekarsko lekarsko) {
		lekarsko.setIzbrisan(true);
		izmeniVozacLekarsko(lekarsko);
	}

	@Override
	public VozaciLekarsko nadjiVozacLekarskoPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLekarsko.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozaciLekarsko)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@Override
	public VozaciLekarsko nadjiVozacLekarskoPoKorisniku(Korisnici korisnik) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLekarsko.class);
		criteria.add(Restrictions.eq("korisnici", korisnik));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.desc("id")).setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null){
			return (VozaciLekarsko)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozaciLekarsko> nadjiSveVozacLekarskePoKorisniku(Korisnici korisnik) {
		ArrayList<VozaciLekarsko> lista = new ArrayList<VozaciLekarsko>();
		if(korisnik.isVozac()) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLekarsko.class);
			criteria.add(Restrictions.eq("korisnici", korisnik));
			criteria.add(Restrictions.eq("izbrisan", false));
			criteria.addOrder(Order.asc("vaziDo"));
			ArrayList<VozaciLekarsko> lista2 = (ArrayList<VozaciLekarsko>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(lista2 != null) {
				return lista2;
			}else {
				return lista;
			}
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozaciLekarsko> nadjiSveVozacLekarske(Korisnici korisnik) {
		ArrayList<VozaciLekarsko> lista = new ArrayList<VozaciLekarsko>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciLekarsko.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
			}
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.asc("vaziDo"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozaciLekarsko> lista2 = (ArrayList<VozaciLekarsko>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

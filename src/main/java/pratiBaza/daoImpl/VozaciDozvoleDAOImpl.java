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
import pratiBaza.dao.VozaciDozvoleDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciDozvole;

@Repository("vozacDozvolaDAO")
public class VozaciDozvoleDAOImpl implements VozaciDozvoleDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiVozacDozvola(VozaciDozvole dozvola) {
		dozvola.setVersion(0);
		dozvola.setKreirano(new Timestamp((new Date()).getTime()));
		dozvola.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(dozvola);
	}

	@Override
	public void izmeniVozacDozvola(VozaciDozvole dozvola) {
		dozvola.setVersion(dozvola.getVersion() + 1);
		dozvola.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(dozvola);
	}

	@Override
	public void izbrisiVozacDozvola(VozaciDozvole dozvola) {
		dozvola.setIzbrisan(true);
		izmeniVozacDozvola(dozvola);
	}

	@Override
	public VozaciDozvole nadjiVozacDozvolaPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciDozvole.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozaciDozvole)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@Override
	public VozaciDozvole nadjiVozacDozvoluPoVozacu(Korisnici vozac) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciDozvole.class);
		criteria.add(Restrictions.eq("vozaci", vozac));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.desc("id")).setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozaciDozvole)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozaciDozvole> nadjiSveVozacDozvolePoVozacu(Korisnici vozac) {
		ArrayList<VozaciDozvole> lista = new ArrayList<VozaciDozvole>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciDozvole.class);
		criteria.add(Restrictions.eq("vozaci", vozac));
		criteria.add(Restrictions.eq("izbrisan", false));
		ArrayList<VozaciDozvole> lista2 = (ArrayList<VozaciDozvole>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozaciDozvole> nadjiSveVozacDozvole(Korisnici korisnik) {
		ArrayList<VozaciDozvole> lista = new ArrayList<VozaciDozvole>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozaciDozvole.class);
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.createAlias("vozaci", "v");
			criteria.add(Restrictions.eq("kv.organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.asc("vaziDo"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozaciDozvole> lista2 = (ArrayList<VozaciDozvole>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

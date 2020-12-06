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
import pratiBaza.dao.VozilaPrimoPredajeDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaPrimoPredaje;

@Repository("voziloPrimoPredajaDAO")
public class VozilaPrimoPredajeDAOImpl implements VozilaPrimoPredajeDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja) {
		primoPredaja.setVersion(0);
		primoPredaja.setKreirano(new Timestamp((new Date()).getTime()));
		primoPredaja.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(primoPredaja);
	}

	@Override
	public void izmeniVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja) {
		primoPredaja.setVersion(primoPredaja.getVersion() + 1);
		primoPredaja.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(primoPredaja);
	}

	@Override
	public void izbrisiVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja) {
		primoPredaja.setIzbrisan(true);
		izmeniVoziloPrimoPredaja(primoPredaja);
	}

	@Override
	public VozilaPrimoPredaje nadjiVoziloPrimoPredajaPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaPrimoPredaje.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozilaPrimoPredaje)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@Override
	public VozilaPrimoPredaje nadjiVoziloPrimoPredajuPoVozilu(Vozila vozilo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaPrimoPredaje.class);
		criteria.add(Restrictions.eq("vozilo", vozilo));
		criteria.add(Restrictions.eq("izbrisan", false));
		criteria.addOrder(Order.desc("id")).setMaxResults(1);
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozilaPrimoPredaje)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozilaPrimoPredaje> nadjiSveVozilaPrimoPredajePoVozilu(Vozila vozilo) {
		ArrayList<VozilaPrimoPredaje> lista = new ArrayList<VozilaPrimoPredaje>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaPrimoPredaje.class);
		criteria.add(Restrictions.eq("vozilo", vozilo));
		criteria.add(Restrictions.eq("izbrisan", false));
		ArrayList<VozilaPrimoPredaje> lista2 = (ArrayList<VozilaPrimoPredaje>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozilaPrimoPredaje> nadjiSveVozilaPrimoPredaje(Korisnici korisnik) {
		ArrayList<VozilaPrimoPredaje> lista = new ArrayList<VozilaPrimoPredaje>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaPrimoPredaje.class);
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
		criteria.addOrder(Order.desc("datum"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozilaPrimoPredaje> lista2 = (ArrayList<VozilaPrimoPredaje>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

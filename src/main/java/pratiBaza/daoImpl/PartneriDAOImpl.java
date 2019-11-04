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
import pratiBaza.dao.PartneriDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Partneri;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("partnerDAO")
public class PartneriDAOImpl implements PartneriDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void unesiPartnera(Partneri partner) {
		partner.setVersion(0);
		partner.setIzmenjeno(new Timestamp((new Date()).getTime()));
		partner.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(partner);
	}

	@Override
	public void izmeniPartnera(Partneri partner) {
		partner.setVersion(partner.getVersion() + 1);
		partner.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(partner);
	}

	@Override
	public void izbrisiPartnera(Partneri partner) {
		partner.setIzbrisan(true);
		izmeniPartnera(partner);
	}

	@Override
	public Partneri nadjiPartneraPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Partneri.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (Partneri)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Partneri> nadjiSvePartnere(Korisnici korisnik, boolean izbrisan) {
		ArrayList<Partneri> lista = new ArrayList<Partneri>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Partneri.class);
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			
		}else {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(izbrisan) {
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<Partneri> lista2 = (ArrayList<Partneri>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

	@Override
	public Partneri nadjiPartneraPoPibu(SistemPretplatnici pretplatnik, int pib) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Partneri.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		criteria.add(Restrictions.eq("pib", pib));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (Partneri)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}
}

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
import pratiBaza.dao.RacuniDAO;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Partneri;
import pratiBaza.tabele.Racuni;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("racunDAO")
public class RacuniDAOImpl implements RacuniDAO{
	
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void unesiRacun(Racuni racun) {
		racun.setVersion(0);
		racun.setIzmenjeno(new Timestamp((new Date()).getTime()));
		racun.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(racun);
	}

	@Override
	public void izmeniRacun(Racuni racun) {
		racun.setVersion(racun.getVersion() + 1);
		racun.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(racun);
	}

	@Override
	public void izbrisiRacun(Racuni racun) {
		racun.setIzbrisan(true);
		izmeniRacun(racun);
	}

	@Override
	public Racuni nadjiRacunPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Racuni.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			Racuni racun = (Racuni)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return racun;
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Racuni> nadjiRacunePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija,
			boolean izbrisan, Date datumOd, Date DatumDo, Partneri partner) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Racuni.class);
		ArrayList<Racuni> lista = new ArrayList<Racuni>();
		if(pretplatnik != null) {
			criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		}
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
		}
		if(izbrisan) {
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(datumOd != null) {
			criteria.add(Restrictions.ge("datum", datumOd));
		}
		if(DatumDo != null) {
			criteria.add(Restrictions.lt("datum", datumOd));
		}
		criteria.addOrder(Order.desc("datum"));
		ArrayList<Racuni> lista2 = (ArrayList<Racuni>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

}

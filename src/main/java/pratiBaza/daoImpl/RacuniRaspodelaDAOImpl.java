package pratiBaza.daoImpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.RacuniRaspodelaDAO;
import pratiBaza.tabele.Partneri;
import pratiBaza.tabele.Racuni;
import pratiBaza.tabele.RacuniRaspodela;

@Repository("racunRaspodelaDAO")
public class RacuniRaspodelaDAOImpl implements RacuniRaspodelaDAO{

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
	public void unesiRacunRaspodelu(RacuniRaspodela raspodela) {
		sessionFactory.getCurrentSession().persist(raspodela);
	}

	@Override
	public void izmeniRacunRaspodelu(RacuniRaspodela raspodela) {
		sessionFactory.getCurrentSession().update(raspodela);
	}

	@Override
	public void izbrisiRacunRaspodelu(RacuniRaspodela raspodela) {
		sessionFactory.getCurrentSession().delete(raspodela);
	}

	@Override
	public RacuniRaspodela nadjiRacunRaspodeluPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RacuniRaspodela.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			RacuniRaspodela raspodela = (RacuniRaspodela)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return raspodela;
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RacuniRaspodela> nadjiRacuneRaspodelePoRacunu(Racuni racun) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RacuniRaspodela.class);
		ArrayList<RacuniRaspodela> lista = new ArrayList<RacuniRaspodela>();
		criteria.add(Restrictions.eq("racun", racun));
		criteria.addOrder(Order.asc("id"));
		ArrayList<RacuniRaspodela> lista2 = (ArrayList<RacuniRaspodela>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RacuniRaspodela> nadjiRacuneRaspodelePoPartneru(Partneri partner) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RacuniRaspodela.class);
		ArrayList<RacuniRaspodela> lista = new ArrayList<RacuniRaspodela>();
		criteria.add(Restrictions.eq("partner", partner));
		criteria.addOrder(Order.asc("datum"));
		ArrayList<RacuniRaspodela> lista2 = (ArrayList<RacuniRaspodela>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}
		return lista;
	}

}

package pratiBaza.daoImpl;

import java.util.ArrayList;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.ObdPoslednjiDAO;
import pratiBaza.tabele.ObdPoslednji;
import pratiBaza.tabele.Objekti;

@Repository("obdPoslednjiDAO")
public class ObdPoslednjiDAOImpl implements ObdPoslednjiDAO{

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
	public void unesiObd(ObdPoslednji obdPoslednji) {
		sessionFactory.getCurrentSession().saveOrUpdate(obdPoslednji);
	}

	@Override
	public void azurirajObd(ObdPoslednji obdPoslednji) {
		sessionFactory.getCurrentSession().saveOrUpdate(obdPoslednji);
	}

	@Override
	public void izbrisiObd(ObdPoslednji obdPoslednje) {
		sessionFactory.getCurrentSession().persist(obdPoslednje);
	}

	@Override
	public ArrayList<ObdPoslednji> vratiListuObdPoslednjih(ArrayList<Objekti> objekti) {
		ArrayList<ObdPoslednji> lista = new ArrayList<ObdPoslednji>();
		if(objekti != null && !objekti.isEmpty()) {
			String upit = "SELECT o FROM ObdPoslednji o WHERE o.objekti IN :objekti ORDER BY o.datumVreme desc";
			TypedQuery<ObdPoslednji> query = sessionFactory.getCurrentSession().createQuery(upit, ObdPoslednji.class);
			query.setParameter("objekti", objekti);
			if(query.getResultList() != null) {
				lista.addAll(query.getResultList());
				}
			/*
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObdPoslednji.class);
			criteria.add(Restrictions.in("objekti", objekti));
			ArrayList<ObdPoslednji> lista2 = (ArrayList<ObdPoslednji>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(lista2 != null) {
				lista.addAll(lista2);
				//lista.sort(Comparator.comparing(ObdPoslednji::getDatumVreme).reversed());
				}
				*/
		}
		return lista;
	}

	@Override
	public ObdPoslednji nadjiObdPoslednjiPoObjektu(Objekti objekat) {
		String upit = "SELECT o FROM ObdPoslednji o WHERE o.objekat = :objekat ORDER BY o.datumVreme desc";
		TypedQuery<ObdPoslednji> query = sessionFactory.getCurrentSession().createQuery(upit, ObdPoslednji.class);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObdPoslednji.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (ObdPoslednji)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
		*/
	}

}

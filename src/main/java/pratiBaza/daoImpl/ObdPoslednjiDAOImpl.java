package pratiBaza.daoImpl;

import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
		sessionFactory.getCurrentSession().persist(obdPoslednji);
	}

	@Override
	public void azurirajObd(ObdPoslednji obdPoslednji) {
		sessionFactory.getCurrentSession().update(obdPoslednji);
	}

	@Override
	public void izbrisiObd(ObdPoslednji obdPoslednje) {
		sessionFactory.getCurrentSession().persist(obdPoslednje);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ObdPoslednji> vratiListuObdPoslednjih(ArrayList<Objekti> objekti) {
		ArrayList<ObdPoslednji> lista = new ArrayList<ObdPoslednji>();
		if(objekti != null && !objekti.isEmpty()) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObdPoslednji.class);
			criteria.add(Restrictions.in("objekti", objekti));
			ArrayList<ObdPoslednji> lista2 = (ArrayList<ObdPoslednji>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(lista2 != null) {
				lista.addAll(lista2);
				//lista.sort(Comparator.comparing(ObdPoslednji::getDatumVreme).reversed());
				}
		}
		return lista;
	}

	@Override
	public ObdPoslednji nadjiObdPoslednjiPoObjektu(Objekti objekat) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObdPoslednji.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (ObdPoslednji)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

}

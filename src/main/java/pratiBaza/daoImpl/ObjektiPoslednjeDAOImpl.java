package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.ObjektiPoslednjeDAO;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.ObjektiPoslednje;

@Repository("objekatPoslednjeDAO")
public class ObjektiPoslednjeDAOImpl implements ObjektiPoslednjeDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void unesiObjekatPoslednje(ObjektiPoslednje objekatPoslednje) {
		objekatPoslednje.setVersion(0);
		objekatPoslednje.setIzmenjeno(new Timestamp((new Date()).getTime()));
		objekatPoslednje.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(objekatPoslednje);
	}

	@Override
	public void azurirajObjektiPoslednje(ObjektiPoslednje objekatPoslednje) {
		objekatPoslednje.setVersion(objekatPoslednje.getVersion() + 1);
		objekatPoslednje.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(objekatPoslednje);
	}

	@Override
	public void izbrisiObjekatPoslednje(ObjektiPoslednje objekatPoslednje) {
		sessionFactory.getCurrentSession().delete(objekatPoslednje);
	}

	@Override
	public ObjektiPoslednje nadjiObjekatPoslednjePoObjektu(Objekti objekat) {
		String upit = "SELECT op ObjektiPoslednje FROM op WHERE op.objekti = :objekat";
		TypedQuery<ObjektiPoslednje> query = sessionFactory.getCurrentSession().createQuery(upit, ObjektiPoslednje.class);
		query.setParameter("objekat", objekat);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObjektiPoslednje.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			ObjektiPoslednje objekatPoslednje = (ObjektiPoslednje)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return objekatPoslednje;
		}else {
			return null;
		}
		*/
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ObjektiPoslednje nadjiObjektiPoslednjePoId(int id) {
		String upit = "SELECT op ObjektiPoslednje FROM op WHERE op.id = :id";
		TypedQuery<ObjektiPoslednje> query = sessionFactory.getCurrentSession().createQuery(upit, ObjektiPoslednje.class);
		query.setParameter("id", id);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObjektiPoslednje.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			ObjektiPoslednje objekatPoslednje = (ObjektiPoslednje)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return objekatPoslednje;
		}else {
			return null;
		}
		*/
	}

}

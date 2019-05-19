package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.ObjektiDetaljiDAO;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.ObjektiDetalji;

@Repository("objekatDetaljDAO")
public class ObjektiDetaljiDAOImpl implements ObjektiDetaljiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiObjektiDetalji(ObjektiDetalji objekatDetalj) {
		objekatDetalj.setVersion(0);
		objekatDetalj.setIzmenjeno(new Timestamp((new Date()).getTime()));
		objekatDetalj.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(objekatDetalj);
	}

	public void azurirajObjektiDetalji(ObjektiDetalji objekatDetalj) {
		objekatDetalj.setVersion(0);
		objekatDetalj.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(objekatDetalj);
	}

	public void izbrisiObjektiDetalji(ObjektiDetalji objekatDetalj) {
		sessionFactory.getCurrentSession().delete(objekatDetalj);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ObjektiDetalji nadjiObjekatDetaljePoObjektu(Objekti objekti) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObjektiDetalji.class);
		criteria.add(Restrictions.eq("objekti", objekti));
		ObjektiDetalji objekatDetalj = (ObjektiDetalji)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return objekatDetalj;
	}
	
}

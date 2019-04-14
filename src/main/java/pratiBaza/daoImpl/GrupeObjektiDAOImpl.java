package pratiBaza.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.GrupeObjektiDAO;
import pratiBaza.tabele.GrupeObjekti;

@Repository("grupaObjekatDAO")
public class GrupeObjektiDAOImpl implements GrupeObjektiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGrupaObjekat(GrupeObjekti grupaObjekat) {
		grupaObjekat.setVersion(0);
		sessionFactory.getCurrentSession().persist(grupaObjekat);
	}

	public void azurirajGrupaObjekat(GrupeObjekti grupaObjekat) {
		grupaObjekat.setVersion(grupaObjekat.getVersion() + 1);
		sessionFactory.getCurrentSession().update(grupaObjekat);
	}

	public void izbrisiGrupaObjekat(GrupeObjekti grupaObjekat) {
		sessionFactory.getCurrentSession().delete(grupaObjekat);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GrupeObjekti nadjiGrupaObjekatPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GrupeObjekti.class);
		criteria.add(Restrictions.eq("id", id));
		GrupeObjekti grupaObjekat = (GrupeObjekti)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return grupaObjekat;
	}
	
}

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
import pratiBaza.dao.GrupeObjektiDAO;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.GrupeObjekti;
import pratiBaza.tabele.Objekti;

@Repository("grupaObjekatDAO")
public class GrupeObjektiDAOImpl implements GrupeObjektiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGrupaObjekat(GrupeObjekti grupaObjekat) {
		grupaObjekat.setVersion(0);
		grupaObjekat.setIzmenjeno(new Timestamp((new Date()).getTime()));
		grupaObjekat.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(grupaObjekat);
	}

	public void azurirajGrupaObjekat(GrupeObjekti grupaObjekat) {
		grupaObjekat.setVersion(grupaObjekat.getVersion() + 1);
		grupaObjekat.setIzmenjeno(new Timestamp((new Date()).getTime()));
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
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			GrupeObjekti grupaObjekat = (GrupeObjekti)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return grupaObjekat;
		}else {
			return null;
		}
		
	}

	public void izbrisiSveGrupaObjekti(Grupe grupa) {
		ArrayList<GrupeObjekti> grupaObjekti = nadjiSveGrupaObjektePoGrupi(grupa);
		for(GrupeObjekti grupaObjekat : grupaObjekti) {
			izbrisiGrupaObjekat(grupaObjekat);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<GrupeObjekti> nadjiSveGrupaObjektePoGrupi(Grupe grupa) {
		ArrayList<GrupeObjekti> grupaObjekti = new ArrayList<GrupeObjekti>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GrupeObjekti.class);
		criteria.add(Restrictions.eq("grupe", grupa));
		criteria.addOrder(Order.desc("id"));
		ArrayList<GrupeObjekti> grupaObjekti2 = (ArrayList<GrupeObjekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(grupaObjekti2 != null) {
			return grupaObjekti2;
		}else {
			return grupaObjekti;
		}
	}

	@Override
	public void izbrisiSveGrupeObjekatPoObjektu(Objekti objekat) {
		ArrayList<GrupeObjekti> grupaObjekti = nadjiSveGrupaObjektePoObjektu(objekat);
		for(GrupeObjekti grupaObjekat : grupaObjekti) {
			izbrisiGrupaObjekat(grupaObjekat);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<GrupeObjekti> nadjiSveGrupaObjektePoObjektu(Objekti objekat) {
		ArrayList<GrupeObjekti> grupaObjekti = new ArrayList<GrupeObjekti>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GrupeObjekti.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		criteria.addOrder(Order.desc("id"));
		ArrayList<GrupeObjekti> grupaObjekti2 = (ArrayList<GrupeObjekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(grupaObjekti2 != null) {
			return grupaObjekti2;
		}else {
			return grupaObjekti;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<GrupeObjekti> nadjiSveGrupeObjektePoGrupama(ArrayList<Grupe> grupe) {
		ArrayList<GrupeObjekti> grupaObjekti = new ArrayList<GrupeObjekti>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GrupeObjekti.class);
		criteria.add(Restrictions.in("grupe", grupe));
		ArrayList<GrupeObjekti> grupaObjekti2 = (ArrayList<GrupeObjekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(grupaObjekti2 != null) {
			return grupaObjekti2;
		}else {
			return grupaObjekti;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Objekti> nadjiSveObjektePoGrupi(Grupe grupa) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GrupeObjekti.class);
		criteria.add(Restrictions.in("grupe", grupa));
		ArrayList<GrupeObjekti> grupaObjekti = (ArrayList<GrupeObjekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		ArrayList<Objekti> objekti = new ArrayList<Objekti>();
		if(grupaObjekti != null) {
			for(GrupeObjekti grupaObjekat : grupaObjekti) {
				if(!objekti.contains(grupaObjekat.getObjekti())) {
					objekti.add(grupaObjekat.getObjekti());			
					}
				}
		}
		return objekti;
	}

	@Override
	public ArrayList<Objekti> nadjiSveObjektePoGrupama(ArrayList<Grupe> grupe) {
		ArrayList<Objekti> objekti = new ArrayList<Objekti>();
		for(Grupe grupa : grupe) {
			ArrayList<Objekti> objektiNiz = nadjiSveObjektePoGrupi(grupa);
			if(objektiNiz != null) {
				for(Objekti objekat : objektiNiz) {
					if(objekat.isAktivan()) {
						if(!objekti.contains(objekat)) {
							objekti.add(objekat);
						}
					}
				}
			}
		}
		return objekti;
	}
	
}

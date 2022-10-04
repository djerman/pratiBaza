package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
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
		String upit = "SELECT go from GrupeObjekti go where go.id = :id";
		TypedQuery<GrupeObjekti> query = sessionFactory.getCurrentSession().createQuery(upit, GrupeObjekti.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	public void izbrisiSveGrupaObjekti(Grupe grupa) {
		List<GrupeObjekti> grupaObjekti = nadjiSveGrupaObjektePoGrupi(grupa);
		for(GrupeObjekti grupaObjekat : grupaObjekti) {
			izbrisiGrupaObjekat(grupaObjekat);
		}
	}

	public List<GrupeObjekti> nadjiSveGrupaObjektePoGrupi(Grupe grupa) {
		String upit = "SELECT go from GrupeObjekti go where go.grupe = :grupa";
		TypedQuery<GrupeObjekti> query = sessionFactory.getCurrentSession().createQuery(upit, GrupeObjekti.class);
		query.setParameter("grupa", grupa);
		return query.getResultList();
	}

	@Override
	public void izbrisiSveGrupeObjekatPoObjektu(Objekti objekat) {
		List<GrupeObjekti> grupaObjekti = nadjiSveGrupaObjektePoObjektu(objekat);
		for(GrupeObjekti grupaObjekat : grupaObjekti) {
			izbrisiGrupaObjekat(grupaObjekat);
		}
	}

	@Override
	public List<GrupeObjekti> nadjiSveGrupaObjektePoObjektu(Objekti objekat) {
		String upit = "SELECT go from GrupeObjekti go where go.objekti = :objekat";
		TypedQuery<GrupeObjekti> query = sessionFactory.getCurrentSession().createQuery(upit, GrupeObjekti.class);
		query.setParameter("objekat", objekat);
		return query.getResultList();
	}

	@Override
	public List<GrupeObjekti> nadjiSveGrupeObjektePoGrupama(ArrayList<Grupe> grupe) {
		String upit = "SELECT go from GrupeObjekti go where go.grupe IN :objekat";
		TypedQuery<GrupeObjekti> query = sessionFactory.getCurrentSession().createQuery(upit, GrupeObjekti.class);
		query.setParameter("objekti", grupe);
		return query.getResultList();
	}

	@Override
	public List<Objekti> nadjiSveObjektePoGrupi(Grupe grupa) {
		String upit = "SELECT go.objekti from GrupeObjekti go where go.grupe = :grupa";
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		query.setParameter("grupa", grupa);
		return query.getResultList();
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GrupeObjekti.class);
		criteria.add(Restrictions.in("grupe", grupa));
		ArrayList<GrupeObjekti> grupaObjekti = (ArrayList<GrupeObjekti>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		ArrayList<Objekti> objekti = new ArrayList<Objekti>();
		if(grupaObjekti != null && grupaObjekti.size() > 0 && !grupaObjekti.isEmpty()) {
			for(GrupeObjekti grupaObjekat : grupaObjekti) {
				if(!objekti.contains(grupaObjekat.getObjekti())) {
					objekti.add(grupaObjekat.getObjekti());			
					}
				}
		}
		return objekti;
		*/
	}
	
	@Override
	public List<Objekti> nadjiSveObjektePoGrupiSaVozilom(Grupe grupa) {
		String upit = "SELECT go.objekti from GrupeObjekti go where go.grupe = :grupa AND go.objekti.vozilo IS NOT NULL";
		TypedQuery<Objekti> query = sessionFactory.getCurrentSession().createQuery(upit, Objekti.class);
		query.setParameter("grupa", grupa);
		return query.getResultList();
	}

	@Override
	public ArrayList<Objekti> nadjiSveObjektePoGrupama(ArrayList<Grupe> grupe) {
		ArrayList<Objekti> objekti = new ArrayList<Objekti>();
		for(Grupe grupa : grupe) {
			List<Objekti> objektiNiz = nadjiSveObjektePoGrupi(grupa);
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

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

import pratiBaza.dao.ObjektiDetaljiDAO;
import pratiBaza.tabele.Korisnici;
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
		objekatDetalj.setVersion(objekatDetalj.getVersion() + 1);
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

	@Override
	public ObjektiDetalji nadjiObjektiDetaljiPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObjektiDetalji.class);
		criteria.add(Restrictions.eq("objekti", id));
		ObjektiDetalji objekatDetalj = (ObjektiDetalji)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return objekatDetalj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ObjektiDetalji> vratisveObjekatDetalje(Korisnici korisnik, boolean aktivan) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ObjektiDetalji.class);
		if(korisnik.getSistemPretplatnici() != null && korisnik.isAdmin()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
			}
		if(aktivan) {
			criteria.add(Restrictions.eq("izbrisan", false));
			}
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.desc("sistemPretplatnici"));
		criteria.addOrder(Order.desc("izbrisan"));
		criteria.addOrder(Order.desc("objekti"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<ObjektiDetalji> lista = (ArrayList<ObjektiDetalji>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return lista;
	}
	
}

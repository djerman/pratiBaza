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
import pratiBaza.dao.VozilaSaobracajne2DAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozilaSaobracajne;
import pratiBaza.tabele.VozilaSaobracajne2;

@Repository("saobracajna2DAO")
public class VozilaSaobracajne2DAOImpl implements VozilaSaobracajne2DAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiSaobracajnu2(VozilaSaobracajne2 saobracajna2) {
		saobracajna2.setVersion(0);
		saobracajna2.setIzmenjeno(new Timestamp((new Date()).getTime()));
		saobracajna2.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(saobracajna2);
	}

	@Override
	public void izmeniSaobracajnu2(VozilaSaobracajne2 saobracajna2) {
		saobracajna2.setVersion(saobracajna2.getVersion() + 1);
		saobracajna2.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(saobracajna2);
	}

	@Override
	public void izbrisiSaobracajnu2(VozilaSaobracajne2 saobracajna2) {
		if(saobracajna2 != null)
			sessionFactory.getCurrentSession().delete(saobracajna2);
	}

	@Override
	public VozilaSaobracajne2 nadjiSaobracajnu2PoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaSaobracajne2.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozilaSaobracajne2)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@Override
	public VozilaSaobracajne2 nadjiSaobracajnu2PoBroju(VozilaSaobracajne saobracajna) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaSaobracajne2.class);
		criteria.add(Restrictions.eq("saobracajna", saobracajna));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozilaSaobracajne2)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozilaSaobracajne2> nadjiSveSaobracajne2(Korisnici korisnik, boolean izbrisan) {
		ArrayList<VozilaSaobracajne2> lista = new ArrayList<VozilaSaobracajne2>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaSaobracajne2.class);
		if(!korisnik.getSistemPretplatnici().isSistem() && !korisnik.isSistem()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		if(korisnik.getOrganizacija() != null) {
			criteria.createAlias("saobracajna", "s");
			criteria.createAlias("s.vozilo", "v");
			criteria.createAlias("v.objekti", "o");
			criteria.add(Restrictions.eq("o.organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozilaSaobracajne2> lista2 = (ArrayList<VozilaSaobracajne2>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

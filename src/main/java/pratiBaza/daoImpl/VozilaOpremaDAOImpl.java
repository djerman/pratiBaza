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
import pratiBaza.dao.VozilaOpremaDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozilaOprema;

@Repository("voziloOpremaDAO")
public class VozilaOpremaDAOImpl implements VozilaOpremaDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiVoziloOpremu(VozilaOprema oprema) {
		oprema.setVersion(0);
		oprema.setKreirano(new Timestamp((new Date()).getTime()));
		oprema.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(oprema);
	}

	@Override
	public void izmeniVoziloOpremu(VozilaOprema oprema) {
		oprema.setVersion(oprema.getVersion() + 1);
		oprema.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(oprema);
	}

	@Override
	public void izbrisiVoziloOpremu(VozilaOprema oprema) {
		oprema.setIzbrisan(true);
		izmeniVoziloOpremu(oprema);
	}

	@Override
	public VozilaOprema nadjiVoziloOpremuPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaOprema.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozilaOprema)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozilaOprema> nadjiSveVozilaOprema(Korisnici korisnik) {
		ArrayList<VozilaOprema> lista = new ArrayList<VozilaOprema>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaOprema.class);
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			
		}else {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
		}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("naziv"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozilaOprema> lista2 = (ArrayList<VozilaOprema>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

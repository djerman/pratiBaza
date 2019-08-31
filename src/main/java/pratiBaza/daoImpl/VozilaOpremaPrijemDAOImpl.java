package pratiBaza.daoImpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.VozilaOpremaPrijemDAO;
import pratiBaza.tabele.VozilaOpremaPrijem;
import pratiBaza.tabele.VozilaPrimoPredaje;

@Repository("voziloOpremaPrijemDAO")
public class VozilaOpremaPrijemDAOImpl implements VozilaOpremaPrijemDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void unesiVoziloOpremaPredaja(VozilaOpremaPrijem predaja) {
		sessionFactory.getCurrentSession().persist(predaja);
	}

	@Override
	public void izmeniVoziloOpremaPredaja(VozilaOpremaPrijem predaja) {
		sessionFactory.getCurrentSession().update(predaja);
	}

	@Override
	public void izbrisiVoziloOpremaPredaja(VozilaOpremaPrijem predaja) {
		sessionFactory.getCurrentSession().delete(predaja);
	}

	@Override
	public VozilaOpremaPrijem nadjiVoziloOpremaPradajaPoId(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaOpremaPrijem.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (VozilaOpremaPrijem)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<VozilaOpremaPrijem> nadjiVozilaOpremaPredajaPoPP(VozilaPrimoPredaje primoPredaja) {
		ArrayList<VozilaOpremaPrijem> lista = new ArrayList<VozilaOpremaPrijem>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VozilaOpremaPrijem.class);
		criteria.add(Restrictions.eq("primoPredaja", primoPredaja));
		criteria.addOrder(Order.desc("id"));
		ArrayList<VozilaOpremaPrijem> lista2 = (ArrayList<VozilaOpremaPrijem>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

	@Override
	public void izbrisiSvaVoziloOpremaPredaja(VozilaPrimoPredaje primoPredaja) {
		ArrayList<VozilaOpremaPrijem> lista = nadjiVozilaOpremaPredajaPoPP(primoPredaja);
		for(VozilaOpremaPrijem prijem : lista) {
			izbrisiVoziloOpremaPredaja(prijem);
		}
	}

	@Override
	public void unesiSvaVoziloOpremaPredaja(List<VozilaOpremaPrijem> opremaStavke) {
		for(VozilaOpremaPrijem prijem : opremaStavke) {
			unesiVoziloOpremaPredaja(prijem);
		}
	}

}

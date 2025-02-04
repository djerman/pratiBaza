package pratiBaza.daoImpl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.JavljanjaPoslednjaDAO;
import pratiBaza.tabele.JavljanjaPoslednja;
import pratiBaza.tabele.Objekti;

@Repository("javljanjePoslednjeDAO")
public class JavljanjaPoslednjaDAOImpl implements JavljanjaPoslednjaDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje) {
		sessionFactory.getCurrentSession().saveOrUpdate(javljanjePoslednje);
	}

	public void azurirajJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje) {
		sessionFactory.getCurrentSession().saveOrUpdate(javljanjePoslednje);
	}

	public void izbrisiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje) {
		sessionFactory.getCurrentSession().delete(javljanjePoslednje);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<JavljanjaPoslednja> vratiListuJavljanjaPoslednjih(ArrayList<Objekti> objekti) {
		String upit = "SELECT jp FROM JavljanjaPoslednja jp where jp.objekti IN :objekti ORDER BY jp.datumVreme desc";
		TypedQuery<JavljanjaPoslednja> query = sessionFactory.getCurrentSession().createQuery(upit, JavljanjaPoslednja.class);
		query.setParameter("objekti", objekti);
		return query.getResultList();
		/*ArrayList<JavljanjaPoslednja> lista = new ArrayList<JavljanjaPoslednja>();
		if(objekti != null && !objekti.isEmpty()) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(JavljanjaPoslednja.class);
			criteria.add(Restrictions.in("objekti", objekti));
			ArrayList<JavljanjaPoslednja> lista2 = (ArrayList<JavljanjaPoslednja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			if(lista2 != null) {
				lista.addAll(lista2);
				lista.sort(Comparator.comparing(JavljanjaPoslednja::getDatumVreme).reversed());
				}
			}
		return lista;
		*/
	}

	@Override
	public JavljanjaPoslednja nadjiJavljanjaPoslednjaPoObjektu(Objekti objekat) {
		String upit = "SELECT jp FROM JavljanjaPoslednja jp where jp.objekti = :objekat ORDER BY jp.datumVreme desc";
		TypedQuery<JavljanjaPoslednja> query = sessionFactory.getCurrentSession().createQuery(upit, JavljanjaPoslednja.class);
		query.setParameter("objekat", objekat);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(JavljanjaPoslednja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (JavljanjaPoslednja)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
		*/
	}
}

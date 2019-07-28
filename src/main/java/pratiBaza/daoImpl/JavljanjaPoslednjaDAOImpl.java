package pratiBaza.daoImpl;

import java.util.ArrayList;
import java.util.Comparator;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
		sessionFactory.getCurrentSession().persist(javljanjePoslednje);
	}

	public void azurirajJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje) {
		sessionFactory.getCurrentSession().update(javljanjePoslednje);
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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<JavljanjaPoslednja> vratiListuJavljanjaPoslednjih(ArrayList<Objekti> objekti) {
		ArrayList<JavljanjaPoslednja> lista = new ArrayList<JavljanjaPoslednja>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(JavljanjaPoslednja.class);
		criteria.add(Restrictions.in("objekti", objekti));
		ArrayList<JavljanjaPoslednja> lista2 = (ArrayList<JavljanjaPoslednja>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista2.sort(Comparator.comparing(JavljanjaPoslednja::getDatumVreme).reversed());
			return lista2;
		}else {
			lista.sort(Comparator.comparing(JavljanjaPoslednja::getDatumVreme).reversed());
			return lista;
		}
	}

	@Override
	public JavljanjaPoslednja nadjiJavljanjaPoslednjaPoObjektu(Objekti objekat) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(JavljanjaPoslednja.class);
		criteria.add(Restrictions.eq("objekti", objekat));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			return (JavljanjaPoslednja)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		}else {
			return null;
		}
		
	}
}

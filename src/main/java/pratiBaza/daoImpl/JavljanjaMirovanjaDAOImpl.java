package pratiBaza.daoImpl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pratiBaza.dao.JavljanjaMirovanjaDAO;
import pratiBaza.tabele.JavljanjaMirovanja;
import pratiBaza.tabele.Objekti;

@Repository("javljanjeMirovanjeDAO")
public class JavljanjaMirovanjaDAOImpl implements JavljanjaMirovanjaDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void unesiJavljanjaMirovanja(JavljanjaMirovanja javljanje) {
		sessionFactory.getCurrentSession().saveOrUpdate(javljanje);
	}
	
	@Override
	public void azurirajJavljanajMirovanja(JavljanjaMirovanja javljanje) {
		sessionFactory.getCurrentSession().saveOrUpdate(javljanje);
	}
	
	@Override
	public void izbrisijavljanjaMirovanja(JavljanjaMirovanja javljanje) {
		sessionFactory.getCurrentSession().delete(javljanje);
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<JavljanjaMirovanja> vratiListuJavljanjaMirovanja(ArrayList<Objekti> objekti){
		String upit = "SELECT jp FROM JavljanjaMirovanja jp where jp.objekti IN :objekti ORDER BY jp.datumVreme desc";
		TypedQuery<JavljanjaMirovanja> query = sessionFactory.getCurrentSession().createQuery(upit, JavljanjaMirovanja.class);
		query.setParameter("objekti", objekti);
		return query.getResultList();
	}
	
	@Override
	public JavljanjaMirovanja nadjiJavljanjaMirovanjaPoObjektu(Objekti objekat) {
		String upit = "SELECT jp FROM JavljanjaMirovanja jp where jp.objekti = :objekat ORDER BY jp.datumVreme desc";
		TypedQuery<JavljanjaMirovanja> query = sessionFactory.getCurrentSession().createQuery(upit, JavljanjaMirovanja.class);
		query.setParameter("objekat", objekat);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
			}catch (Exception e) {
				return null;
				}
		}
}

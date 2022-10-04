package pratiBaza.daoImpl;

import java.util.ArrayList;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemOperateriDAO;
import pratiBaza.tabele.SistemOperateri;

@Repository("sistemOperaterDAO")
public class SistemOperateriDAOImpl implements SistemOperateriDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiOperatera(SistemOperateri operater) {
		operater.setVersion(0);
		sessionFactory.getCurrentSession().persist(operater);
	}

	public void azurirajOperatera(SistemOperateri operater) {
		operater.setVersion(operater.getVersion() + 1);
		sessionFactory.getCurrentSession().update(operater);
	}

	public void izbrisiOperatera(SistemOperateri operater) {
		operater.setIzbrisan(true);
		operater.setVersion(operater.getVersion() + 1);
		sessionFactory.getCurrentSession().update(operater);
	}

	public ArrayList<SistemOperateri> nadjiSveOperatere() {
		ArrayList<SistemOperateri> lista = new ArrayList<SistemOperateri>();
		String upit = "SELECT o FROM SistemOperateri o ORDER BY o.izbrisan ASC, o.id DESC";
		TypedQuery<SistemOperateri> query = sessionFactory.getCurrentSession().createQuery(upit, SistemOperateri.class);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SistemOperateri nadjiOperateraPoId(int id) {
		String upit = "SELECT o FROM SistemOperateri o WHERE o.id = :id";
		TypedQuery<SistemOperateri> query = sessionFactory.getCurrentSession().createQuery(upit, SistemOperateri.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
	
}

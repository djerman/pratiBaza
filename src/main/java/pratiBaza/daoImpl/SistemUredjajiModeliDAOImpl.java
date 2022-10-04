package pratiBaza.daoImpl;

import java.util.ArrayList;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemUredjajiModeliDAO;
import pratiBaza.tabele.SistemUredjajiModeli;

@Repository("sistemUredjajModelDAO")
public class SistemUredjajiModeliDAOImpl implements SistemUredjajiModeliDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiUredjajModel(SistemUredjajiModeli model) {
		model.setVersion(0);
		sessionFactory.getCurrentSession().persist(model);
	}

	public void izmeniUredjajModel(SistemUredjajiModeli model) {
		model.setVersion(model.getVersion() + 1);
		sessionFactory.getCurrentSession().update(model);
	}

	public void izbrisiUredjajModel(SistemUredjajiModeli model) {
		model.setIzbrisan(true);
		model.setVersion(model.getVersion() + 1);
		sessionFactory.getCurrentSession().update(model);
	}

	public ArrayList<SistemUredjajiModeli> nadjiSveUredjajModele() {
		ArrayList<SistemUredjajiModeli> lista = new ArrayList<SistemUredjajiModeli>();
		String upit = "SELECT um FROM SistemUredjajiModeli um"
				+ " ORDER BY um.izbrisan ASC, um.id DESC";
		TypedQuery<SistemUredjajiModeli> query = sessionFactory.getCurrentSession().createQuery(upit, SistemUredjajiModeli.class);
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

	public SistemUredjajiModeli nadjiModelPoId(int id) {
		String upit = "SELECT up FROM Partneri up WHERE up.id = :id";
		TypedQuery<SistemUredjajiModeli> query = sessionFactory.getCurrentSession().createQuery(upit, SistemUredjajiModeli.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
	
}

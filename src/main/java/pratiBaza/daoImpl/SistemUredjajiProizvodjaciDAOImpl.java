package pratiBaza.daoImpl;

import java.util.ArrayList;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemUredjajiProizvodjaciDAO;
import pratiBaza.tabele.SistemUredjajiProizvodjac;

@Repository("sistemUredjajProizvodjacDAO")
public class SistemUredjajiProizvodjaciDAOImpl implements SistemUredjajiProizvodjaciDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		proizvodjac.setVersion(0);
		sessionFactory.getCurrentSession().persist(proizvodjac);
	}

	public void izmeniSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		proizvodjac.setVersion(proizvodjac.getVersion() + 1);
		sessionFactory.getCurrentSession().update(proizvodjac);
	}

	public void izbrisiSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		proizvodjac.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(proizvodjac);
	}

	public ArrayList<SistemUredjajiProizvodjac> nadjiSveSistemUredjajeProizvodjace() {
		ArrayList<SistemUredjajiProizvodjac> lista = new ArrayList<SistemUredjajiProizvodjac>();
		String upit = "SELECT up FROM SistemUredjajiProizvodjac up"
				+ " ORDER BY up.izbrisan ASC, up.id DESC";
		TypedQuery<SistemUredjajiProizvodjac> query = sessionFactory.getCurrentSession().createQuery(upit, SistemUredjajiProizvodjac.class);
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

	public SistemUredjajiProizvodjac nadjiProizvodjacaPoId(int id) {
		String upit = "SELECT up FROM Partneri up WHERE up.id = :id";
		TypedQuery<SistemUredjajiProizvodjac> query = sessionFactory.getCurrentSession().createQuery(upit, SistemUredjajiProizvodjac.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
	
}

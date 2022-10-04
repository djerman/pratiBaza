package pratiBaza.daoImpl;

import java.util.ArrayList;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemGorivaDAO;
import pratiBaza.tabele.SistemGoriva;

@Repository("sistemGorivoDAO")
public class SistemGorivoDAOImpl implements SistemGorivaDAO{
	
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGorivo(SistemGoriva gorivo) {
		gorivo.setVersion(0);
		sessionFactory.getCurrentSession().persist(gorivo);
	}

	public void azurirajGorivo(SistemGoriva gorivo) {
		gorivo.setVersion(gorivo.getVersion() + 1);
		sessionFactory.getCurrentSession().update(gorivo);
	}

	public void izbrisiGorivo(SistemGoriva gorivo) {
		gorivo.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(gorivo);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ArrayList<SistemGoriva> vratiSvaGoriva(boolean izbrisan) {
		ArrayList<SistemGoriva> lista = new ArrayList<SistemGoriva>();
		String upit = "SELECT g FROM SistemGoriva g WHERE g.izbrisan = false"
				+ " ORDER BY g.id desc";
		TypedQuery<SistemGoriva> query = sessionFactory.getCurrentSession().createQuery(upit, SistemGoriva.class);
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
	}

	public SistemGoriva nadjiGorivoPoId(int id) {
		String upit = "SELECT g FROM SistemGoriva g WHERE g.id = :id";
		TypedQuery<SistemGoriva> query = sessionFactory.getCurrentSession().createQuery(upit, SistemGoriva.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public SistemGoriva nadjiGorivoPoNazivu(String gorivo) {
		String upit = "SELECT g FROM SistemGoriva g WHERE g.izbrisan = false"
				+ " AND (lower(g.naziv) = lower(:gorivo))";
		TypedQuery<SistemGoriva> query = sessionFactory.getCurrentSession().createQuery(upit, SistemGoriva.class);
		query.setParameter("gorivo", gorivo);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			try {
				return query.getResultList().get(0);
			}catch (Exception ee) {
				return null;
			}
		}
	}
	
	@Override
	public SistemGoriva pretraziGorivoPoNazivu(String gorivo) {
		String upit = "SELECT g FROM SistemGoriva g WHERE g.izbrisan = false"
				+ " AND (lower(g.naziv) like lower(concat('%',:gorivo,'%')))";
		TypedQuery<SistemGoriva> query = sessionFactory.getCurrentSession().createQuery(upit, SistemGoriva.class);
		query.setParameter("gorivo", gorivo);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			try {
				return query.getResultList().get(0);
			}catch (Exception ee) {
				return null;
			}
		}
	}
	
}

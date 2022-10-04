package pratiBaza.daoImpl;

import java.util.ArrayList;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemAlarmiDAO;
import pratiBaza.tabele.SistemAlarmi;

@Repository("sistemAlarmiDAO")
public class SistemAlarmiDAOImpl implements SistemAlarmiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiAlarme(SistemAlarmi alarm) {
		alarm.setVersion(0);
		sessionFactory.getCurrentSession().persist(alarm);
	}

	public void azurirajAlarme(SistemAlarmi alarm) {
		alarm.setVersion(alarm.getVersion() + 1);
		sessionFactory.getCurrentSession().update(alarm);
	}

	public void izbrisiAlarme(SistemAlarmi alarm) {
		alarm.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(alarm);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ArrayList<SistemAlarmi> vratiSveAlarme() {
		ArrayList<SistemAlarmi> lista = new ArrayList<SistemAlarmi>();
		String upit = "SELECT sa FROM SistemAlarmi sa"
				+ " ORDER BY sa.izbrisan ASC, sa.id DESC";
		TypedQuery<SistemAlarmi> query = sessionFactory.getCurrentSession().createQuery(upit, SistemAlarmi.class);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			
		}
		return lista;
	}

	public SistemAlarmi nadjiAlaramPoId(int id) {
		String upit = "SELECT sa FROM SistemAlarmi sa WHERE sa.id = :id";
		TypedQuery<SistemAlarmi> query = sessionFactory.getCurrentSession().createQuery(upit, SistemAlarmi.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public SistemAlarmi nadjiAlarmPoSifri(String sifra) {
		String upit = "SELECT sa FROM SistemAlarmi sa WHERE sa.sifra = :sifra";
		TypedQuery<SistemAlarmi> query = sessionFactory.getCurrentSession().createQuery(upit, SistemAlarmi.class);
		query.setParameter("sifra", sifra);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<SistemAlarmi> vratiAlarmePoZahtevu(boolean aktivan, boolean email, boolean pregled) {
		ArrayList<SistemAlarmi> lista = new ArrayList<SistemAlarmi>();
		String upit = "SELECT sa FROM SistemAlarmi sa WHERE (:aktivan = false OR sa.aktivan = :aktivan)"
				+ " AND (:email = false OR sa.email = :email)"
				+ " AND (:pregled = false OR sa.pregled = :pregled)"
				+ " ORDER BY sa.izbrisan ASC, sa.id DESC";
		TypedQuery<SistemAlarmi> query = sessionFactory.getCurrentSession().createQuery(upit, SistemAlarmi.class);
		query.setParameter("aktivan", aktivan);
		query.setParameter("email", email);
		query.setParameter("pregled", pregled);
		try {
			lista.add(query.getSingleResult());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<SistemAlarmi> vratiAlarmeZaPregled(boolean aktivan, boolean pregled) {
		ArrayList<SistemAlarmi> lista = new ArrayList<SistemAlarmi>();
		String upit = "SELECT sa FROM SistemAlarmi sa WHERE (:aktivan = false OR sa.aktivan = :aktivan)"
				+ " AND (:pregled = false OR sa.pregled = :pregled)"
				+ " ORDER BY sa.izbrisan ASC, sa.id DESC";
		TypedQuery<SistemAlarmi> query = sessionFactory.getCurrentSession().createQuery(upit, SistemAlarmi.class);
		query.setParameter("aktivan", aktivan);
		query.setParameter("pregled", pregled);
		try {
			lista.add(query.getSingleResult());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}

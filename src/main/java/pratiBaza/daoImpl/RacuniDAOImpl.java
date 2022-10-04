package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.RacuniDAO;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Partneri;
import pratiBaza.tabele.Racuni;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("racunDAO")
public class RacuniDAOImpl implements RacuniDAO{
	
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void unesiRacun(Racuni racun) {
		racun.setVersion(0);
		racun.setIzmenjeno(new Timestamp((new Date()).getTime()));
		racun.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(racun);
	}

	@Override
	public void izmeniRacun(Racuni racun) {
		racun.setVersion(racun.getVersion() + 1);
		racun.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(racun);
	}

	@Override
	public void izbrisiRacun(Racuni racun) {
		racun.setIzbrisan(true);
		izmeniRacun(racun);
	}

	@Override
	public Racuni nadjiRacunPoId(int id) {
		String upit = "SELECT r FROM Racuni r WHERE r.id = :id";
		TypedQuery<Racuni> query = sessionFactory.getCurrentSession().createQuery(upit, Racuni.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Racuni> nadjiRacunePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija,
			boolean izbrisan, Date datumOd, Date datumDo, Partneri partner) {
		ArrayList<Racuni> lista = new ArrayList<Racuni>();
		String upit = "SELECT r FROM Racuni r WHERE (:pretplatnik IS NULL OR r.sistemPretplatnici = :pretplatnik)"
				+ " AND (:organizacija IS NULL OR r.organizacija = :organizacija)"
				+ " AND (:partner IS NULL OR r.partner = :partner)"
				+ " AND (:izbrisan = false OR r.izbrisan = false)"
				+ " AND (:datumOd IS NULL OR r.datum >= :datumOd)"
				+ " AND (:datumDo IS NULL OR r.datum <= :datumOd)"
				+ " ORDER BY r.datum DESC";
		TypedQuery<Racuni> query = sessionFactory.getCurrentSession().createQuery(upit, Racuni.class);
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);
		query.setParameter("partner", partner);
		query.setParameter("izbrisan", izbrisan);
		query.setParameter("datumOd", datumOd);
		query.setParameter("datumDo", datumDo);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			System.out.println("računi listanje ne valja: ");
			e.printStackTrace();
		}
		return lista;
	}

}

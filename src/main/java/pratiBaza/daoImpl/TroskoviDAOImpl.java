package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.TroskoviDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Racuni;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Troskovi;

@Repository("trosakDAO")
public class TroskoviDAOImpl implements TroskoviDAO{

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
	public void unesiTrosak(Troskovi trosak) {
		trosak.setVersion(0);
		trosak.setIzmenjeno(new Timestamp((new Date()).getTime()));
		trosak.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(trosak);
	}

	@Override
	public void izmeniTrosak(Troskovi trosak) {
		trosak.setVersion(trosak.getVersion() + 1);
		trosak.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(trosak);
	}

	@Override
	public void izbrisiTrosak(Troskovi trosak) {
		try {
			sessionFactory.getCurrentSession().delete(trosak);
		}catch (Exception e) {
			trosak.setIzbrisan(true);
			izmeniTrosak(trosak);
		}
	}

	@Override
	public Troskovi nadjiTrosakPoId(int id) {
		String upit = "SELECT t FROM Troskovi t WHERE t.id = :id";
		TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class).setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Troskovi> nadjiSveTroskovePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan) {
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		String upit = "SELECT t FROM Troskovi t WHERE (t.sistemPretplatnici IS NULL OR t.sistemPretplatnici = :pretplatnik)"
				+ " AND (t.organizacija IS NULL OR t.organizacija =: organizacija)"
				+ " ORDER BY t.datumVreme DESC";
		TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class)
				.setParameter(1, pretplatnik)
				.setParameter(2, organizacija);
		
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
	}

	@Override
	public ArrayList<Troskovi> nadjiSveTroskove(Korisnici korisnik) {
		if(korisnik.getSistemPretplatnici().isSistem() && korisnik.isSistem()) {
			ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
			String upit = "SELECT t FROM Troskovi t ORDER BY t.datumVreme DESC";
			TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class);
			
			if(query.getResultList() != null)
				lista.addAll(query.getResultList());
			return lista;
		}else {
			return nadjiSveTroskovePoPretplatniku(korisnik.getSistemPretplatnici(), korisnik.getOrganizacija(), true);
		}
	}

	@Override
	public ArrayList<Troskovi> nadjiSvaOdrzavanjaPoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan) {
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		String upit = "SELECT t FROM Troskovi t WHERE (:pretplatnik IS NULL OR t.sistemPretplatnici = :pretplatnik)"
				+ " AND (:organizacija IS NULL OR t.organizacija = :organizacija)"
				+ " AND t.tipServisa <> 0"
				+ " ORDER BY t.datumVreme DESC";
		TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class)
				.setParameter("pretplatnik", pretplatnik)
				.setParameter("organizacija", organizacija);
		
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
	}

	@Override
	public ArrayList<Troskovi> nadjiSvaOdrzavanja(Korisnici korisnik) {
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		String upit = "SELECT t FROM Troskovi t WHERE (:pretplatnik IS NULL OR t.sistemPretplatnici = :pretplatnik)"
				+ " AND (:organizacija IS NULL OR t.organizacija = :organizacija)"
				+ " AND t.tipServisa <> 0"
				+ " AND t.izbrisan = false"
				+ " ORDER BY t.datumVreme DESC";
		TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class)
				.setParameter("pretplatnik", korisnik.getSistemPretplatnici())
				.setParameter("organizacija", korisnik.getOrganizacija());
		
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
	}

	@Override
	public ArrayList<Troskovi> nadjiSvuPotrosnjuPoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan) {
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		String upit = "SELECT t FROM Troskovi t WHERE (:pretplatnik IS NULL OR t.sistemPretplatnici = :pretplatnik)"
				+ " AND (:organizacija IS NULL OR t.organizacija = :organizacija)"
				+ " AND t.tipServisa = 0"
				+ " AND t.izbrisan = false"
				+ " ORDER BY t.datumVreme DESC";
		TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class)
				.setParameter("pretplatnik", pretplatnik)
				.setParameter("organizacija", organizacija);
		
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
	}

	@Override
	public ArrayList<Troskovi> nadjiSvuPotrosnju(Korisnici korisnik) {
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		String upit = "SELECT t FROM Troskovi t WHERE (:pretplatnik IS NULL OR t.sistemPretplatnici = :pretplatnik)"
				+ " AND (:organizacija IS NULL OR t.organizacija = :organizacija)"
				+ " AND t.tipServisa = 0"
				+ " AND t.izbrisan = false"
				+ " ORDER BY t.datumVreme DESC";
		TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class)
				.setParameter("pretplatnik", korisnik.getSistemPretplatnici())
				.setParameter("organizacija", korisnik.getOrganizacija());
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
	}

	@Override
	public Troskovi nadjiPoslednjiTrosakDo(Timestamp datumVreme, int tipTroska) {
		String upit = "SELECT t FROM Troskovi t WHERE t.tipServisa = tipServisa"
				+ " AND t.datumVreme <= :datumVreme"
				+ " AND t.izbrisan = false"
				+ " ORDER BY t.datumVreme DESC";
		TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class)
				.setParameter("tipServisa", tipTroska)
				.setParameter("datumVreme", datumVreme)
				.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Troskovi> nadjiSveTroskoveOd(Timestamp datumVremeOd, SistemPretplatnici pretplatnik, Organizacije organizacija) {
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		String upit = "SELECT t FROM Troskovi t WHERE (:pretplatnik IS NULL OR t.sistemPretplatnici = :pretplatnik)"
				+ " AND (:organizacija IS NULL OR t.organizacija = :organizacija)"
				+ " AND t.datumVreme >= :datumVremeOd"
				+ " AND t.tipServisa = 0"
				+ " AND t.izbrisan = false"
				+ " ORDER BY t.datumVreme DESC";
		
		TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class)
				.setParameter("pretplatnik", pretplatnik)
				.setParameter("organizacija", organizacija)
				.setParameter("datumVremeOd", datumVremeOd);
		
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
	}

	@Override
	public ArrayList<Troskovi> nadjiSveTroskoveUkupno(ArrayList<Objekti> vozila, Timestamp datumVremeOd, Timestamp datumVremeDo, Integer tipTroska){
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		String upit = "SELECT t FROM Troskovi t WHERE t.objekti IN :vozila"
				+ " AND t.datumVreme BETWEEN :datumVremeOd AND :datumVremeDo"
				+ " AND (:tipTroska IS NULL OR t.tipServisa = :tipTroska)"
				+ " AND t.izbrisan = false"
				+ " ORDER BY t.datumVreme ASC";
		
		TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class)
				.setParameter("vozila", vozila)
				.setParameter("datumVremeOd", datumVremeOd)
				.setParameter("datumVremeDo", datumVremeDo)
				.setParameter("tipTroska", tipTroska);
		
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
	}

	@Override
	public ArrayList<Troskovi> nadjiSvuPotrosnjuPoRacunu(Racuni racun) {
		ArrayList<Troskovi> lista = new ArrayList<Troskovi>();
		String upit = "SELECT t FROM Troskovi t WHERE t.racun = :racun"
				+ " AND t.izbrisan = false"
				+ " ORDER BY t.datumVreme DESC";
		
		TypedQuery<Troskovi> query = sessionFactory.getCurrentSession().createQuery(upit, Troskovi.class)
				.setParameter("racun", racun);
		
		if(query.getResultList() != null)
			lista.addAll(query.getResultList());
		return lista;
	}
}

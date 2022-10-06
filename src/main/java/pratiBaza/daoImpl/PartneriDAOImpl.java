package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.PartneriDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Partneri;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("partnerDAO")
public class PartneriDAOImpl implements PartneriDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void unesiPartnera(Partneri partner) {
		partner.setVersion(0);
		partner.setIzmenjeno(new Timestamp((new Date()).getTime()));
		partner.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(partner);
	}

	@Override
	public void izmeniPartnera(Partneri partner) {
		partner.setVersion(partner.getVersion() + 1);
		partner.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(partner);
	}

	@Override
	public void izbrisiPartnera(Partneri partner) {
		partner.setIzbrisan(true);
		izmeniPartnera(partner);
	}

	@Override
	public Partneri nadjiPartneraPoId(int id) {
		String upit = "SELECT p FROM Partneri p WHERE p.id = :id";
		TypedQuery<Partneri> query = sessionFactory.getCurrentSession().createQuery(upit, Partneri.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Partneri nadjiPartneraPoPibu(SistemPretplatnici pretplatnik, int pib) {
		String upit = "SELECT p FROM Partneri p WHERE p.sistemPretplatnici = :pretplatnik AND p.pib = :pib";
		TypedQuery<Partneri> query = sessionFactory.getCurrentSession().createQuery(upit, Partneri.class);
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("pib", pib);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public ArrayList<Partneri> nadjiSvePartnere(Korisnici korisnik, boolean izbrisan) {
		ArrayList<Partneri> lista = new ArrayList<Partneri>();
		String upit = "SELECT p FROM Partneri p WHERE (:sistem = true OR p.sistemPretplatnici = :pretplatnik)"
				+ " AND (:izbrisan = true OR p.izbrisan = izbrisan)"
				+ " ORDER BY p.id DESC";
		TypedQuery<Partneri> query = sessionFactory.getCurrentSession().createQuery(upit, Partneri.class);
		query.setParameter("sistem", korisnik.isSistem());
		query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		query.setParameter("izbrisan", izbrisan);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			
		}
		return lista;
	}

	@Override
	public ArrayList<Partneri> nadjiSvePartnerePoPretplatniku(SistemPretplatnici pretplatnik, boolean izbrisan) {
		ArrayList<Partneri> lista = new ArrayList<Partneri>();
		String upit = "SELECT p FROM Partneri p WHERE p.sistemPretplatnici = :pretplatnik"
				+ " AND (:izbrisan = true OR p.izbrisan = :izbrisan)"
				+ " ORDER BY p.id DESC";
		TypedQuery<Partneri> query = sessionFactory.getCurrentSession().createQuery(upit, Partneri.class);
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("izbrisan", izbrisan);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			
		}
		return lista;
	}
	
}

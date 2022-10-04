package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.GrupeDAO;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("grupaDAO")
public class GrupeDAOImpl implements GrupeDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiGrupu(Grupe grupa) {
		grupa.setVersion(0);
		grupa.setIzmenjeno(new Timestamp((new Date()).getTime()));
		grupa.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(grupa);
	}

	@Override
	public void azurirajGrupu(Grupe grupa) {
		grupa.setVersion(grupa.getVersion() + 1);
		grupa.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(grupa);
	}

	@Override
	public void izbrisiGrupu(Grupe grupa) {
		grupa.setAktivan(false);
		grupa.setIzbrisan(true);
		azurirajGrupu(grupa);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Grupe> vratiGrupe(Korisnici korisnik) {
		String pretp = "";
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			pretp = "g.sistemPretplatnici = :pretplatnik AND g.izbrisan = false AND ";
		}
		String upit = "Select g FROM Grupe g where " + pretp + "(:organizacija is null or g.organizacija = :organizacija) "
				+ " ORDER BY g.sistemPretplatnici.naziv, g.id, g.izbrisan, g.aktivan desc";
		TypedQuery<Grupe> query = sessionFactory.getCurrentSession().createQuery(upit, Grupe.class);
		
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		}
		query.setParameter("organizacija", korisnik.getOrganizacija());

		return query.getResultList();
	}

	@Override
	public Grupe nadjiGrupuPoId(int id) {
		String upit = "SELECT g FROM Grupe g where g.id = :id";
		TypedQuery<Grupe> query = sessionFactory.getCurrentSession().createQuery(upit, Grupe.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Grupe> vratiGrupeAktivne(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		String upit = "Select g FROM Grupe g where g.sistemPretplatnici = :pretplatnik AND g.izbrisan = false AND (:organizacija is null or g.organizacija = :organizacija)"
				+ " AND g.aktivan = true ORDER BY g.naziv asc";
		TypedQuery<Grupe> query = sessionFactory.getCurrentSession().createQuery(upit, Grupe.class);
		
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);

		return query.getResultList();
	}
	
}

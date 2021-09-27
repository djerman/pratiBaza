package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.GrupeKorisniciDAO;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.GrupeKorisnici;
import pratiBaza.tabele.Korisnici;

@Repository("grupaKorisnikDAO")
public class GrupeKorisniciDAOImpl implements GrupeKorisniciDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGrupaZaposleni(GrupeKorisnici grupaKorisnik) {
		grupaKorisnik.setIzmenjeno(new Timestamp((new Date()).getTime()));
		grupaKorisnik.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(grupaKorisnik);
	}

	public void azurirajGrupaZaposleni(GrupeKorisnici grupaKorisnik) {
		grupaKorisnik.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(grupaKorisnik);
	}

	public void izbrisiGrupaZaposleni(GrupeKorisnici grupaKorisnik) {
		sessionFactory.getCurrentSession().delete(grupaKorisnik);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GrupeKorisnici nadjiGrupaKorisnikPoId(int id) {
		String upit = "SELECT gk FROM GrupeKorisnici gk where gk.id = :id";
		TypedQuery<GrupeKorisnici> query = sessionFactory.getCurrentSession().createQuery(upit, GrupeKorisnici.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GrupeKorisnici.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			GrupeKorisnici grupaKorisnik = (GrupeKorisnici)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return grupaKorisnik;
		}else {
			return null;
		}
		*/
	}

	public List<GrupeKorisnici> vratiSveGrupeKorisnikPoKorisniku(Korisnici korisnik) {
		String upit = "SELECT gk FROM GrupeKorisnici gk WHERE gk.korisnici = :korisnik";
		TypedQuery<GrupeKorisnici> query = sessionFactory.getCurrentSession().createQuery(upit, GrupeKorisnici.class);
		query.setParameter("korisnik", korisnik);
		return query.getResultList();
		/*
		ArrayList<GrupeKorisnici> grupeKorisnik = new ArrayList<GrupeKorisnici>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GrupeKorisnici.class);
		criteria.add(Restrictions.eq("korisnici", korisnik));
		ArrayList<GrupeKorisnici> grupeKorisnik2 = (ArrayList<GrupeKorisnici>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(grupeKorisnik2 != null) {
			return grupeKorisnik2;
		}else {
			return grupeKorisnik;
		}
		*/
	}

	@Override
	public List<Grupe> vratiSveGrupePoKorisniku(Korisnici korisnik) {
		String upit = "SELECT gk.grupe FROM GrupeKorisnici gk WHERE gk.korisnici = :korisnik";
		TypedQuery<Grupe> query = sessionFactory.getCurrentSession().createQuery(upit, Grupe.class);
		query.setParameter("korisnik", korisnik);
		return query.getResultList();
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GrupeKorisnici.class);
		criteria.add(Restrictions.eq("korisnici", korisnik));
		ArrayList<GrupeKorisnici> grupeKorisnik2 = (ArrayList<GrupeKorisnici>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		ArrayList<Grupe> grupe = new ArrayList<Grupe>();
		if(grupeKorisnik2 != null) {
			for(GrupeKorisnici grupaKorisnik: grupeKorisnik2) {
				if(!grupe.contains(grupaKorisnik.getGrupe())) {
					grupe.add(grupaKorisnik.getGrupe());
				}
			}
		}
		return grupe;
		*/
	}
}

package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GrupeKorisnici.class);
		criteria.add(Restrictions.eq("id", id));
		if(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult() != null) {
			GrupeKorisnici grupaKorisnik = (GrupeKorisnici)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return grupaKorisnik;
		}else {
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<GrupeKorisnici> vratiSveGrupeKorisnikPoKorisniku(Korisnici korisnik) {
		ArrayList<GrupeKorisnici> grupeKorisnik = new ArrayList<GrupeKorisnici>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GrupeKorisnici.class);
		criteria.add(Restrictions.eq("korisnici", korisnik));
		ArrayList<GrupeKorisnici> grupeKorisnik2 = (ArrayList<GrupeKorisnici>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(grupeKorisnik2 != null) {
			return grupeKorisnik2;
		}else {
			return grupeKorisnik;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Grupe> vratiSveGrupePoKorisniku(Korisnici korisnik) {
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
	}
	
	
}

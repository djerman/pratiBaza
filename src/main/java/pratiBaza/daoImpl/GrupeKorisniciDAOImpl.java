package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.GrupeKorisniciDAO;
import pratiBaza.tabele.GrupeKorisnici;

@Repository("grupaKorisnikDAO")
public class GrupeKorisniciDAOImpl implements GrupeKorisniciDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGrupaZaposleni(GrupeKorisnici grupaKorisnik) {
		sessionFactory.getCurrentSession().persist(grupaKorisnik);
	}

	public void azurirajGrupaZaposleni(GrupeKorisnici grupaKorisnik) {
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
	
	
}

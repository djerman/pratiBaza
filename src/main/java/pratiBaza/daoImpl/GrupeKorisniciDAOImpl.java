package pratiBaza.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.GrupeKorisniciDAO;
import pratiBaza.tabele.GrupeKorisnici;

@Repository("grupaKorisnik")
public class GrupeKorisniciDAOImpl implements GrupeKorisniciDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiGrupaZaposleni(GrupeKorisnici grupaKorisnik) {
		// TODO Auto-generated method stub
		
	}

	public void azurirajGrupaZaposleni(GrupeKorisnici grupaKorisnik) {
		// TODO Auto-generated method stub
		
	}

	public void izbrisiGrupaZaposleni(GrupeKorisnici grupaKorisnik) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}

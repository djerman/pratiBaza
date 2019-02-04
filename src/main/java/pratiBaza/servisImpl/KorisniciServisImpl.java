package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.KorisniciDAO;
import pratiBaza.servis.KorisniciServis;
import pratiBaza.tabele.Korisnici;

@Service("korisnikServis")
public class KorisniciServisImpl implements KorisniciServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	KorisniciDAO korisnikDAO;

	@Transactional
	public void unesiKorisnika(Korisnici korisnik) {
		korisnikDAO.unesiKorisnika(korisnik);
	}

	@Transactional
	public void azurirajKorisnika(Korisnici korisnik) {
		korisnikDAO.azurirajKorisnika(korisnik);
	}

	@Transactional
	public void izbrisiKorisnika(Korisnici korisnik) {
		korisnikDAO.izbrisiKorisnika(korisnik);
	}

	@Transactional
	public KorisniciDAO getKorisnikDAO() {
		return korisnikDAO;
	}

	@Transactional
	public void setKorisnikDAO(KorisniciDAO korisnikDAO) {
		this.korisnikDAO = korisnikDAO;
	}
	
	
}

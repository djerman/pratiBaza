package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.KorisniciDAO;
import pratiBaza.servis.KorisniciServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemPretplatnici;

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
	public Korisnici nadjiKorisnikaPoKorisnickom(String email, String lozinka) {
		return korisnikDAO.nadjiKorisnikaPoKorisnickom(email, lozinka);
	}

	@Transactional
	public ArrayList<Korisnici> nadjiKorisnikePoPretplatniku(SistemPretplatnici pretplatnik) {
		return korisnikDAO.nadjiKorisnikePoPretplatniku(pretplatnik);
	}

	@Transactional
	public Korisnici nadjiKorisnikaPoIButton(String iButton) {
		return korisnikDAO.nadjiKorisnikaPoIButton(iButton);
	}
	
	@Transactional
	public KorisniciDAO getKorisnikDAO() {
		return korisnikDAO;
	}

	@Transactional
	public void setKorisnikDAO(KorisniciDAO korisnikDAO) {
		this.korisnikDAO = korisnikDAO;
	}

	@Transactional
	public ArrayList<Korisnici> nadjiSveKorisnike(Korisnici korisnik) {
		return korisnikDAO.nadjiSveKorisnike(korisnik);
	}
	
}

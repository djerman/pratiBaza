package pratiBaza.dao;

import pratiBaza.tabele.Korisnici;

public interface KorisniciDAO {

	void unesiKorisnika(Korisnici korisnik);
	
	void azurirajKorisnika(Korisnici korisnik);
	
	void izbrisiKorisnika(Korisnici korisnik);
}

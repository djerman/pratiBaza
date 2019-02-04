package pratiBaza.servis;

import pratiBaza.tabele.Korisnici;

public interface KorisniciServis {

	void unesiKorisnika(Korisnici korisnik);
	
	void azurirajKorisnika(Korisnici korisnik);
	
	void izbrisiKorisnika(Korisnici korisnik);
}

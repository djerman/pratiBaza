package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemPretplatnici;

public interface KorisniciServis {

	void unesiKorisnika(Korisnici korisnik);
	
	void azurirajKorisnika(Korisnici korisnik);
	
	void izbrisiKorisnika(Korisnici korisnik);
	
	Korisnici nadjiKorisnikaPoKorisnickom(String email, String lozinka);
	
	ArrayList<Korisnici> nadjiKorisnikePoPretplatniku(SistemPretplatnici pretplatnik);
	
	Korisnici nadjiKorisnikaPoIButton(String iButton);
	
	ArrayList<Korisnici> nadjiSveKorisnike(Korisnici korisnik);
	
	Korisnici nadjiKorisnikaPoId(int id);
}

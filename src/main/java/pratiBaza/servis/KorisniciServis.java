package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemPretplatnici;

public interface KorisniciServis {

	void unesiKorisnika(Korisnici korisnik);
	
	void azurirajKorisnika(Korisnici korisnik);
	
	void izbrisiKorisnika(Korisnici korisnik);
	
	public Korisnici nadjiKorisnikaPoKorisnickom(String email, String lozinka);
	
	public ArrayList<Korisnici> nadjiKorisnikePoPretplatniku(SistemPretplatnici pretplatnik);
	
	public Korisnici nadjiKorisnikaPoIButton(String iButton);
}

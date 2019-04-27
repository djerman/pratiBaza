package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemPretplatnici;

public interface KorisniciDAO {

	void unesiKorisnika(Korisnici korisnik);
	
	void azurirajKorisnika(Korisnici korisnik);
	
	void izbrisiKorisnika(Korisnici korisnik);
	
	Korisnici nadjiKorisnikaPoKorisnickom(String email, String lozinka);
	
	ArrayList<Korisnici> nadjiKorisnikePoPretplatniku(SistemPretplatnici pretplatnik);
	
	Korisnici nadjiKorisnikaPoIButton(String iButton);
	
	ArrayList<Korisnici> nadjiSveKorisnike(Korisnici korisnik, boolean aktivan);
	
	Korisnici nadjiKorisnikaPoId(int id);
	
	ArrayList<Korisnici> nadjiKorisnikeAktivneIzbrisane(boolean aktivan, boolean izbrisan);
}

package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemPretplatnici;

public interface KorisniciDAO {

	void unesiKorisnika(Korisnici korisnik);
	
	void azurirajKorisnika(Korisnici korisnik);
	
	void izbrisiKorisnika(Korisnici korisnik);
	
	public Korisnici nadjiKorisnikaPoKorisnickom(String email, String lozinka);
	
	public ArrayList<Korisnici> nadjiKorisnikePoPretplatniku(SistemPretplatnici pretplatnik);
	
	public Korisnici nadjiKorisnikaPoIButton(String iButton);
	
	public ArrayList<Korisnici> nadjiSveKorisnike(Korisnici korisnik);
}

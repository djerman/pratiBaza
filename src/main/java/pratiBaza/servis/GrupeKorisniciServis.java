package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.GrupeKorisnici;
import pratiBaza.tabele.Korisnici;

public interface GrupeKorisniciServis {

	void unesiGrupaZaposleni(GrupeKorisnici grupaKorisnik);
	
	void azurirajGrupaZaposleni(GrupeKorisnici grupaKorisnik);
	
	void izbrisiGrupaZaposleni(GrupeKorisnici grupaKorisnik);
	
	GrupeKorisnici nadjiGrupaKorisnikPoId(int id);
	
	ArrayList<GrupeKorisnici> vratiSveGrupeKorisnikPoKorisniku(Korisnici korisnik);
	
	ArrayList<Grupe> vratiSveGrupePoKorisniku(Korisnici korisnik);
}

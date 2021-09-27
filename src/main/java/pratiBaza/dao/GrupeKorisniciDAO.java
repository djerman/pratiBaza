package pratiBaza.dao;

import java.util.List;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.GrupeKorisnici;
import pratiBaza.tabele.Korisnici;

public interface GrupeKorisniciDAO {
	
	void unesiGrupaZaposleni(GrupeKorisnici grupaKorisnik);
	
	void azurirajGrupaZaposleni(GrupeKorisnici grupaKorisnik);
	
	void izbrisiGrupaZaposleni(GrupeKorisnici grupaKorisnik);

	GrupeKorisnici nadjiGrupaKorisnikPoId(int id);
	
	List<GrupeKorisnici> vratiSveGrupeKorisnikPoKorisniku(Korisnici korisnik);
	
	List<Grupe> vratiSveGrupePoKorisniku(Korisnici korisnik);
}

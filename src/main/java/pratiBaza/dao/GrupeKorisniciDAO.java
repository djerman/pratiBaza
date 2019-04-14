package pratiBaza.dao;

import pratiBaza.tabele.GrupeKorisnici;

public interface GrupeKorisniciDAO {
	
	void unesiGrupaZaposleni(GrupeKorisnici grupaKorisnik);
	
	void azurirajGrupaZaposleni(GrupeKorisnici grupaKorisnik);
	
	void izbrisiGrupaZaposleni(GrupeKorisnici grupaKorisnik);

	GrupeKorisnici nadjiGrupaKorisnikPoId(int id);
}

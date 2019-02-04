package pratiBaza.servis;

import pratiBaza.tabele.GrupeKorisnici;

public interface GrupeKorisniciServis {

	void unesiGrupaZaposleni(GrupeKorisnici grupaKorisnik);
	
	void azurirajGrupaZaposleni(GrupeKorisnici grupaKorisnik);
	
	void izbrisiGrupaZaposleni(GrupeKorisnici grupaKorisnik);
}

package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

public interface GrupeServis {
	
	void unesiGrupu(Grupe grupa);
	
	void azurirajGrupu(Grupe grupa);
	
	void izbrisiGrupu(Grupe grupa);
	
	ArrayList<Grupe> vratiGrupe(Korisnici korisnik);
	
	Grupe nadjiGrupuPoId(int id);

	ArrayList<Grupe> vratiGrupeAktivne(SistemPretplatnici pretplatnik, Organizacije organizacija);
}

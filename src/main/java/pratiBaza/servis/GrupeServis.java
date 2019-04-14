package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;

public interface GrupeServis {
	
	void unesiGrupu(Grupe grupa);
	
	void azurirajGrupu(Grupe grupa);
	
	void izbrisiGrupu(Grupe grupa);
	
	ArrayList<Grupe> vratiGrupe(Korisnici korisnik);
	
	Grupe nadjiGrupuPoId(int id);

}

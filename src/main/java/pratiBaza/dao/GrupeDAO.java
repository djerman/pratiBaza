package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;

public interface GrupeDAO {
	
	void unesiGrupu(Grupe grupa);
	
	void azurirajGrupu(Grupe grupa);
	
	void izbrisiGrupu(Grupe grupa);
	
	ArrayList<Grupe> vratiGrupe(Korisnici korisnik);
	
	Grupe nadjiGrupuPoId(int id);

}

package pratiBaza.dao;

import java.util.List;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

public interface GrupeDAO {
	
	void unesiGrupu(Grupe grupa);
	
	void azurirajGrupu(Grupe grupa);
	
	void izbrisiGrupu(Grupe grupa);
	
	List<Grupe> vratiGrupe(Korisnici korisnik);
	
	Grupe nadjiGrupuPoId(int id);

	List<Grupe> vratiGrupeAktivne(SistemPretplatnici pretplatnik, Organizacije organizacija);
}

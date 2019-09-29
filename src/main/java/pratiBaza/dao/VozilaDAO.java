package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaSaobracajne;

public interface VozilaDAO {

	void unesiVozilo(Vozila vozilo);
	
	void azurirajVozilo(Vozila vozilo);
	
	void izbrisiVozilo(Vozila vozilo);
	
	Vozila nadjiVoziloPoObjektu(Objekti objekti);
	
	Vozila nadjiVoziloPoId(int id);
	
	ArrayList<Vozila> vratisvaVozila(Korisnici korisnik, boolean aktivan);
	
	Vozila vratiVoziloPoSaobracajnoj(VozilaSaobracajne saobracajna);
}

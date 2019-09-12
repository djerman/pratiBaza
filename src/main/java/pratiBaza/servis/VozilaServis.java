package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Vozila;

public interface VozilaServis {

	void unesiVozilo(Vozila vozilo);
	
	void azurirajVozilo(Vozila objekatDetalj);
	
	void izbrisiVozilo(Vozila objekatDetalj);

	Vozila nadjiVoziloPoObjektu(Objekti objekti);
	
	Vozila nadjiVoziloPoId(int id);
	
	ArrayList<Vozila> vratisvaVozila(Korisnici korisnik, boolean aktivan);
}

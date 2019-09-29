package pratiBaza.servis;

import java.sql.Timestamp;
import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaNalozi;

public interface VozilaNaloziServis {

	void unesiVoziloNalog(VozilaNalozi nalog);
	
	void izmeniVoziloNalog(VozilaNalozi nalog);
	
	void izbrisiVoziloNalog(VozilaNalozi nalog);
	
	VozilaNalozi nadjiVoziloNalog(int id);
	
	VozilaNalozi nadjiVoziloNalogPoVozilu(Vozila vozilo);
	
	ArrayList<VozilaNalozi> nadjiSveVozilaNalogePoVozilu(Vozila vozilo);
	
	ArrayList<VozilaNalozi> nadjiSveVozilaNaloge(Korisnici korisnik);
	
	ArrayList<VozilaNalozi> nadjiNalogeZaGrupuUPeriodu(ArrayList<Objekti> objekti, Timestamp pocetak, Timestamp kraj);
}

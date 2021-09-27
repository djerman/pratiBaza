package pratiBaza.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaNalozi;

public interface VozilaNaloziDAO {

	void unesiVoziloNalog(VozilaNalozi nalog);
	
	void izmeniVoziloNalog(VozilaNalozi nalog);
	
	void izbrisiVoziloNalog(VozilaNalozi nalog);
	
	VozilaNalozi nadjiVoziloNalog(int id);
	
	VozilaNalozi nadjiVoziloNalogPoVozilu(Vozila vozilo);
	
	ArrayList<VozilaNalozi> nadjiSveVozilaNalogePoVozilu(Vozila vozilo);
	
	ArrayList<VozilaNalozi> nadjiSveVozilaNaloge(Korisnici korisnik);
	
	ArrayList<VozilaNalozi> nadjiNalogeZaGrupuUPeriodu(List<Objekti> objekti, Timestamp pocetak, Timestamp kraj);
}

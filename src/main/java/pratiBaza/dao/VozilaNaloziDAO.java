package pratiBaza.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.VozilaNalozi;

public interface VozilaNaloziDAO {

	void unesiVoziloNalog(VozilaNalozi nalog);
	
	void izmeniVoziloNalog(VozilaNalozi nalog);
	
	void izbrisiVoziloNalog(VozilaNalozi nalog);
	
	VozilaNalozi nadjiVoziloNalog(int id);
	
	VozilaNalozi nadjiVoziloNalogPoVozilu(Objekti objekat);
	
	ArrayList<VozilaNalozi> nadjiSveVozilaNalogePoObjektu(Objekti objekat);
	
	ArrayList<VozilaNalozi> nadjiSveVozilaNaloge(Korisnici korisnik);
	
	ArrayList<VozilaNalozi> nadjiNalogeZaGrupuUPeriodu(ArrayList<Objekti> objekti, Timestamp pocetak, Timestamp kraj);
}

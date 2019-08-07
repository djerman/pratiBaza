package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.VozilaNalozi;

public interface VozilaNaloziServis {

	void unesiVoziloNalog(VozilaNalozi nalog);
	
	void izmeniVoziloNalog(VozilaNalozi nalog);
	
	void izbrisiVoziloNalog(VozilaNalozi nalog);
	
	VozilaNalozi nadjiVoziloNalog(int id);
	
	VozilaNalozi nadjiVoziloNalogPoVozilu(Objekti objekat);
	
	ArrayList<VozilaNalozi> nadjiSveVozilaNalogePoObjektu(Objekti objekat);
	
	ArrayList<VozilaNalozi> nadjiSveVozilaNaloge(Korisnici korisnik);
}

package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaPrimoPredaje;

public interface VozilaPrimoPredajeServis {

	void unesiVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja);
	
	void izmeniVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja);
	
	void izbrisiVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja);
	
	VozilaPrimoPredaje nadjiVoziloPrimoPredajaPoId(int id);
	
	VozilaPrimoPredaje nadjiVoziloPrimoPredajuPoVozilu(Vozila vozilo);
	
	ArrayList<VozilaPrimoPredaje> nadjiSveVozilaPrimoPredajePoObjektu(Vozila vozilo);
	
	ArrayList<VozilaPrimoPredaje> nadjiSveVozilaPrimoPredaje(Korisnici korisnik);
}

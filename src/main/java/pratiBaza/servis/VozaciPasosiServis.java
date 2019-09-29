package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozaci;
import pratiBaza.tabele.VozaciPasosi;

public interface VozaciPasosiServis {

	void unesiVozacPasos(VozaciPasosi pasos);
	
	void izmeniVozacPasos(VozaciPasosi pasos);
	
	void izbrisiVozacPasos(VozaciPasosi pasos);
	
	VozaciPasosi nadjiVozacPasosPoId(int id);
	
	VozaciPasosi nadjiVozacPasosPoVozacu(Vozaci vozac);
	
	ArrayList<VozaciPasosi> nadjiSveVozacPasosPoVozacu(Vozaci vozac);
	
	ArrayList<VozaciPasosi> nadjiSveVozacPasos(Korisnici korisnik);
}

package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciPasosi;

public interface VozaciPasosiServis {

	void unesiVozacPasos(VozaciPasosi pasos);
	
	void izmeniVozacPasos(VozaciPasosi pasos);
	
	void izbrisiVozacPasos(VozaciPasosi pasos);
	
	VozaciPasosi nadjiVozacPasosPoId(int id);
	
	VozaciPasosi nadjiVozacPasosPoVozacu(Korisnici vozac);
	
	ArrayList<VozaciPasosi> nadjiSveVozacPasosPoVozacu(Korisnici vozac);
	
	ArrayList<VozaciPasosi> nadjiSveVozacPasos(Korisnici korisnik);
}

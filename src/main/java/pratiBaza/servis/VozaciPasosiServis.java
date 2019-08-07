package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciPasosi;

public interface VozaciPasosiServis {

	void unesiVozacPasos(VozaciPasosi pasos);
	
	void izmeniVozacPasos(VozaciPasosi pasos);
	
	void izbrisiVozacPasos(VozaciPasosi pasos);
	
	VozaciPasosi nadjiVozacPasosPoId(int id);
	
	VozaciPasosi nadjiVozacPasosPoKorisniku(Korisnici korisnik);
	
	ArrayList<VozaciPasosi> nadjiSveVozacPasosPoKorisniku(Korisnici korisnik);
	
	ArrayList<VozaciPasosi> nadjiSveVozacPasos(Korisnici korisnik);
}

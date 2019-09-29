package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozaci;
import pratiBaza.tabele.VozaciDozvole;

public interface VozaciDozvoleServis {

	void unesiVozacDozvola(VozaciDozvole dozvola);
	
	void izmeniVozacDozvola(VozaciDozvole dozvola);
	
	void izbrisiVozacDozvola(VozaciDozvole dozvola);
	
	VozaciDozvole nadjiVozacDozvolaPoId(int id);
	
	VozaciDozvole nadjiVozacDozvoluPoVozacu(Vozaci vozac);
	
	ArrayList<VozaciDozvole> nadjiSveVozacDozvolePoVozacu(Vozaci vozac);
	
	ArrayList<VozaciDozvole> nadjiSveVozacDozvole(Korisnici korisnik);
}

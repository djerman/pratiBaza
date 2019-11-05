package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciDozvole;

public interface VozaciDozvoleDAO {

	void unesiVozacDozvola(VozaciDozvole dozvola);
	
	void izmeniVozacDozvola(VozaciDozvole dozvola);
	
	void izbrisiVozacDozvola(VozaciDozvole dozvola);
	
	VozaciDozvole nadjiVozacDozvolaPoId(int id);
	
	VozaciDozvole nadjiVozacDozvoluPoVozacu(Korisnici vozac);
	
	ArrayList<VozaciDozvole> nadjiSveVozacDozvolePoVozacu(Korisnici vozac);
	
	ArrayList<VozaciDozvole> nadjiSveVozacDozvole(Korisnici korisnik);
}

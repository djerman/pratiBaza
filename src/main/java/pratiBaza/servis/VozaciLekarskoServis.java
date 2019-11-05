package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciLekarsko;

public interface VozaciLekarskoServis {

	void unesiVozacLekarsko(VozaciLekarsko lekarsko);
	
	void izmeniVozacLekarsko(VozaciLekarsko lekarsko);
	
	void izbrisiVozacLekarsko(VozaciLekarsko lekarsko);
	
	VozaciLekarsko nadjiVozacLekarskoPoId(int id);
	
	VozaciLekarsko nadjiVozacLekarskoPoVozacu(Korisnici vozac);
	
	ArrayList<VozaciLekarsko> nadjiSveVozacLekarskePoVozacu(Korisnici vozac);
	
	ArrayList<VozaciLekarsko> nadjiSveVozacLekarske(Korisnici korisnik);
}

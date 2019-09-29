package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozaci;
import pratiBaza.tabele.VozaciLekarsko;

public interface VozaciLekarskoDAO {

	void unesiVozacLekarsko(VozaciLekarsko lekarsko);
	
	void izmeniVozacLekarsko(VozaciLekarsko lekarsko);
	
	void izbrisiVozacLekarsko(VozaciLekarsko lekarsko);
	
	VozaciLekarsko nadjiVozacLekarskoPoId(int id);
	
	VozaciLekarsko nadjiVozacLekarskoPoVozacu(Vozaci vozac);
	
	ArrayList<VozaciLekarsko> nadjiSveVozacLekarskePoVozacu(Vozaci vozac);
	
	ArrayList<VozaciLekarsko> nadjiSveVozacLekarske(Korisnici korisnik);
}

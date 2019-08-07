package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciLekarsko;

public interface VozaciLekarskoServis {

	void unesiVozacLekarsko(VozaciLekarsko lekarsko);
	
	void izmeniVozacLekarsko(VozaciLekarsko lekarsko);
	
	void izbrisiVozacLekarsko(VozaciLekarsko lekarsko);
	
	VozaciLekarsko nadjiVozacLekarskoPoId(int id);
	
	VozaciLekarsko nadjiVozacLekarskoPoKorisniku(Korisnici korisnik);
	
	ArrayList<VozaciLekarsko> nadjiSveVozacLekarskePoKorisniku(Korisnici korisnik);
	
	ArrayList<VozaciLekarsko> nadjiSveVozacLekarske(Korisnici korisnik);
}

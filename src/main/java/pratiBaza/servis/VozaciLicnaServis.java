package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciLicna;

public interface VozaciLicnaServis {

	void unesiVozacLicna(VozaciLicna licna);
	
	void izmeniVozacLicna(VozaciLicna licna);
	
	void izbrisiVozacLicna(VozaciLicna licna);
	
	VozaciLicna nadjiVozacLicnaPoId(int id);
	
	VozaciLicna nadjiVozacLicnaPoVozacu(Korisnici vozac);
	
	ArrayList<VozaciLicna> nadjiSveVozacLicnaPoVozacu(Korisnici vozac);
	
	ArrayList<VozaciLicna> nadjiSveVozacLicna(Korisnici korisnik);
}

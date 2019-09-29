package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozaci;
import pratiBaza.tabele.VozaciLicence;

public interface VozaciLicenceDAO {

	void unesiVozacLicenca(VozaciLicence licenca);
	
	void izmeniVozacLicenca(VozaciLicence licenca);
	
	void izbrisiVozacLicenca(VozaciLicence licenca);
	
	VozaciLicence nadjiVozacLicencaPoId(int id);
	
	VozaciLicence nadjiVozacLicencaPoVozacu(Vozaci vozac);
	
	ArrayList<VozaciLicence> nadjiSveVozacLicencaPoVozacu(Vozaci vozac);
	
	ArrayList<VozaciLicence> nadjiSveVozacLicenca(Korisnici korisnik);
}

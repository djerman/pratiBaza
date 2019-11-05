package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciLicence;

public interface VozaciLicenceDAO {

	void unesiVozacLicenca(VozaciLicence licenca);
	
	void izmeniVozacLicenca(VozaciLicence licenca);
	
	void izbrisiVozacLicenca(VozaciLicence licenca);
	
	VozaciLicence nadjiVozacLicencaPoId(int id);
	
	VozaciLicence nadjiVozacLicencaPoVozacu(Korisnici vozac);
	
	ArrayList<VozaciLicence> nadjiSveVozacLicencaPoVozacu(Korisnici vozac);
	
	ArrayList<VozaciLicence> nadjiSveVozacLicenca(Korisnici korisnik);
}

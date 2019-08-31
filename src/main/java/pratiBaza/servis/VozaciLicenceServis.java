package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciLicence;

public interface VozaciLicenceServis {

	void unesiVozacLicenca(VozaciLicence licenca);
	
	void izmeniVozacLicenca(VozaciLicence licenca);
	
	void izbrisiVozacLicenca(VozaciLicence licenca);
	
	VozaciLicence nadjiVozacLicencaPoId(int id);
	
	VozaciLicence nadjiVozacLicencaPoKorisniku(Korisnici korisnik);
	
	ArrayList<VozaciLicence> nadjiSVeVozacLicencaPoKorisniku(Korisnici korisnik);
	
	ArrayList<VozaciLicence> nadjiSveVozacLicenca(Korisnici korisnik);
}

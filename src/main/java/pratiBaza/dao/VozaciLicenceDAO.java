package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciLicence;

public interface VozaciLicenceDAO {

	void unesiVozacLicenca(VozaciLicence licenca);
	
	void izmeniVozacLicenca(VozaciLicence licenca);
	
	void izbrisiVozacLicenca(VozaciLicence licenca);
	
	VozaciLicence nadjiVozacLicencaPoId(int id);
	
	VozaciLicence nadjiVozacLicencaPoKorisniku(Korisnici korisnik);
	
	ArrayList<VozaciLicence> nadjiSVeVozacLicencaPoKorisniku(Korisnici korisnik);
	
	ArrayList<VozaciLicence> nadjiSveVozacLicenca(Korisnici korisnik);
}

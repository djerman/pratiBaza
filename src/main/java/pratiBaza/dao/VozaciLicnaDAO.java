package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciLicna;

public interface VozaciLicnaDAO {

	void unesiVozacLicna(VozaciLicna licna);
	
	void izmeniVozacLicna(VozaciLicna licna);
	
	void izbrisiVozacLicna(VozaciLicna licna);
	
	VozaciLicna nadjiVozacLicnaPoId(int id);
	
	VozaciLicna nadjiVozacLicnaPoKorisniku(Korisnici korisnik);
	
	ArrayList<VozaciLicna> nadjiSveVozacLicnaPoKorisniku(Korisnici korisnik);
	
	ArrayList<VozaciLicna> nadjiSveVozacLicna(Korisnici korisnik);
}

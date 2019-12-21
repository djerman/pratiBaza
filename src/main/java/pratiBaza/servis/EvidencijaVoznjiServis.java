package pratiBaza.servis;

import java.sql.Timestamp;
import java.util.ArrayList;
import pratiBaza.tabele.EvidencijaVoznji;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

public interface EvidencijaVoznjiServis {

	void unesiEvidenciju(EvidencijaVoznji evidencija);
	
	void izmeniEvidenciju(EvidencijaVoznji evidencija);
	
	void izbrisiEvidenciju(EvidencijaVoznji evidencija);
	
	EvidencijaVoznji nadjiEvidencijuPoId(int id);
	
	ArrayList<EvidencijaVoznji> vratiEvidencije(SistemPretplatnici pretplatnik, Organizacije organizacija, String nalog,
			String registracija, String vozac, Timestamp datumVremeOd, Timestamp datumVremeDo);
}

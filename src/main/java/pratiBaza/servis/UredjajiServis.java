package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

public interface UredjajiServis {

	void unesiUredjaj(Uredjaji uredjaj);
	
	void izmeniUredjaj(Uredjaji uredjaj);
	
	void izbrisiUredjaj(Uredjaji uredjaj);
	
	ArrayList<Uredjaji> nadjiSveUredjaje();
	
	ArrayList<Uredjaji> nadjiSveUredjajePoPretplatniku(SistemPretplatnici pretplatnik);
}

package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.SistemPretplatnici;

public interface SistemPretplatniciServis {
	
	void unesiPretplatnika(SistemPretplatnici pretplatnik);
	
	void izmeniPretplatnika(SistemPretplatnici pretplatnik);
	
	void izbrisiPretplatnika(SistemPretplatnici pretplatnik);
	
	ArrayList<SistemPretplatnici> nadjiSvePretplatnike();
	
	SistemPretplatnici nadjiPretplatnikaPoId(int id);
}

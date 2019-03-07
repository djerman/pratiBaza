package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.SistemOperateri;

public interface SistemOperateriServis {

	void unesiOperatera(SistemOperateri operater);
	
	void azurirajOperatera(SistemOperateri operater);
	
	void izbrisiOperatera(SistemOperateri operater);
	
	ArrayList<SistemOperateri> nadjiSveOperatere();
}

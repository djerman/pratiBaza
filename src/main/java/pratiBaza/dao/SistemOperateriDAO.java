package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.SistemOperateri;

public interface SistemOperateriDAO {

	void unesiOperatera(SistemOperateri operater);
	
	void azurirajOperatera(SistemOperateri operater);
	
	void izbrisiOperatera(SistemOperateri operater);
	
	ArrayList<SistemOperateri> nadjiSveOperatere();
	
}

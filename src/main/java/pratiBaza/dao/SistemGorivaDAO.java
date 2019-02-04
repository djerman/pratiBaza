package pratiBaza.dao;

import pratiBaza.tabele.SistemGoriva;

public interface SistemGorivaDAO {

	void unesiGorivo(SistemGoriva gorivo);
	
	void azurirajGorivo(SistemGoriva gorivo);
	
	void izbrisiGorivo(SistemGoriva gorivo);
}

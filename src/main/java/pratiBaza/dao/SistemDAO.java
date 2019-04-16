package pratiBaza.dao;

import pratiBaza.tabele.Sistem;

public interface SistemDAO {

	void unesiSistem(Sistem sistem);
	
	void azurirajSistem(Sistem sistem);
	
	void izbrisiSistem(Sistem sistem);
	
	Sistem vratiSistem();
	
	Sistem nadjiSistemPoId(int id);
}

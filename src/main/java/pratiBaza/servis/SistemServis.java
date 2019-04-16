package pratiBaza.servis;

import pratiBaza.tabele.Sistem;

public interface SistemServis {

	void unesiSistem(Sistem sistem);
	
	void azurirajSistem(Sistem sistem);
	
	void izbrisiSistem(Sistem sistem);
	
	Sistem vratiSistem();
	
	Sistem nadjiSistemPoId(int id);
}

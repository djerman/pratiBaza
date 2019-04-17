package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.SistemGoriva;

public interface SistemGorivaDAO {

	void unesiGorivo(SistemGoriva gorivo);
	
	void azurirajGorivo(SistemGoriva gorivo);
	
	void izbrisiGorivo(SistemGoriva gorivo);
	
	ArrayList<SistemGoriva> vratiSvaGoriva();
	
	SistemGoriva nadjiGorivoPoId(int id);
}

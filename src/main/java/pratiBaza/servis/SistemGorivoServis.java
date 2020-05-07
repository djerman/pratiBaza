package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.SistemGoriva;

public interface SistemGorivoServis {

	void unesiGorivo(SistemGoriva gorivo);
	
	void azurirajGorivo(SistemGoriva gorivo);
	
	void izbrisiGorivo(SistemGoriva gorivo);
	
	ArrayList<SistemGoriva> vratiSvaGoriva(boolean izbrisan);
	
	SistemGoriva nadjiGorivoPoId(int id);
	
	SistemGoriva nadjiGorivoPoNazivu(String gorivo);
}

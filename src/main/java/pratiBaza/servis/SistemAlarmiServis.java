package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.SistemAlarmi;

public interface SistemAlarmiServis {

	void unesiAlarme(SistemAlarmi alarm);
	
	void azurirajAlarme(SistemAlarmi alarm);
	
	void izbrisiAlarme(SistemAlarmi alarm);
	
	ArrayList<SistemAlarmi> vratiSveAlarme();
	
	SistemAlarmi nadjiAlaramPoId(int id);
}

package pratiBaza.servis;

import pratiBaza.tabele.SistemAlarmi;

public interface SistemAlarmiServis {

	void unesiAlarme(SistemAlarmi alarm);
	
	void azurirajAlarme(SistemAlarmi alarm);
	
	void izbrisiAlarme(SistemAlarmi alarm);
}

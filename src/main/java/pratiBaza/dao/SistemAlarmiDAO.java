package pratiBaza.dao;

import pratiBaza.tabele.SistemAlarmi;

public interface SistemAlarmiDAO {

	void unesiAlarme(SistemAlarmi alarm);
	
	void azurirajAlarme(SistemAlarmi alarm);
	
	void izbrisiAlarme(SistemAlarmi alarm);
}

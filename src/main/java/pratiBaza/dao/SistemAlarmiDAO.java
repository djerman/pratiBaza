package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.SistemAlarmi;

public interface SistemAlarmiDAO {

	void unesiAlarme(SistemAlarmi alarm);
	
	void azurirajAlarme(SistemAlarmi alarm);
	
	void izbrisiAlarme(SistemAlarmi alarm);
	
	ArrayList<SistemAlarmi> vratiSveAlarme();
	
	SistemAlarmi nadjiAlaramPoId(int id);
	
	SistemAlarmi nadjiAlarmPoSifri(String sifra);
	
	ArrayList<SistemAlarmi> vratiAlarmePoZahtevu(boolean aktivan, boolean email, boolean pregled);
	
	ArrayList<SistemAlarmi> vratiAlarmeZaPregled(boolean aktivan, boolean pregled);
}

package pratiBaza.dao;

import pratiBaza.tabele.AlarmiKorisnik;

public interface AlarmiKorisnikDAO {
	
	void unesiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik);
	
	void azurirajAlarmiKorisnik(AlarmiKorisnik alarmKorisnik);
	
	void izbrisiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik);

}

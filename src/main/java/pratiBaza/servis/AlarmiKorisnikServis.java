package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.AlarmiKorisnik;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemAlarmi;

public interface AlarmiKorisnikServis {
	
	void unesiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik);
	
	ArrayList<SistemAlarmi> vratiAlarmePoKorisniku(Korisnici korisnik);
	
	void azurirajAlarmiKorisnik(AlarmiKorisnik alarmKorisnik);
	
	void izbrisiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik);
	
	AlarmiKorisnik nadjiAlarmKorisnikPoId(int id);
	
	AlarmiKorisnik nadjiAlarmePoKorisnikObjekatAlarm(Korisnici korisnik, Objekti objekat, SistemAlarmi alarm);
	
	ArrayList<AlarmiKorisnik> nadjiSveAlarmePoKorisniku(Korisnici korisnik, boolean aktivno);
	
	ArrayList<AlarmiKorisnik> nadjiSveAlarmeKorisnikePoObjektu(Objekti objekat);
}

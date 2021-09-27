package pratiBaza.servis;

import java.util.List;
import pratiBaza.tabele.AlarmiKorisnik;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemAlarmi;

public interface AlarmiKorisnikServis {
	
	void unesiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik);
	
	List<AlarmiKorisnik> vratiAlarmePoKorisniku(Korisnici korisnik, boolean aktivno, boolean email, boolean obavestenje);
	
	void azurirajAlarmiKorisnik(AlarmiKorisnik alarmKorisnik);
	
	void izbrisiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik);
	
	AlarmiKorisnik nadjiAlarmKorisnikPoId(int id);
	
	AlarmiKorisnik nadjiAlarmePoKorisnikObjekatAlarm(Korisnici korisnik, Objekti objekat, SistemAlarmi alarm);
	
	List<AlarmiKorisnik> nadjiSveAlarmePoKorisniku(Korisnici korisnik, boolean aktivno, boolean email, boolean obavestenje);
	
	List<AlarmiKorisnik> nadjiSveAlarmeKorisnikePoObjektu(Objekti objekat);
}

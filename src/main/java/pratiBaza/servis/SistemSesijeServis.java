package pratiBaza.servis;

import java.sql.Timestamp;
import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.SistemSesije;

public interface SistemSesijeServis {

	void unesiSesiju(SistemSesije sesija);
	
	void izmeniSesiju(SistemSesije sesija);
	
	void izbrisiSesiju(SistemSesije sesija);
	
	ArrayList<SistemSesije> nadjiSveSesije(Korisnici korisnik);
	
	ArrayList<SistemSesije> nadjiSveSesijeKorisnika(Korisnici korisnik);
	
	ArrayList<SistemSesije> nadjiSveSesijeKorisnikaPoVremenu(Korisnici korisnik, Timestamp datumVremeOd, Timestamp datumVremeDo);
	
	ArrayList<SistemSesije> nadjiSveSesijePretplatnika(SistemPretplatnici pretplatnik);
	
	ArrayList<SistemSesije> nadjiSveSesijePretplatnikaPoVremenu(SistemPretplatnici pratplatnika, Timestamp datumVremeOd, Timestamp datumVremDo);
}

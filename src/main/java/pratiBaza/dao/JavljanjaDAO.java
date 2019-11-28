package pratiBaza.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import pratiBaza.pomocne.PredjeniPut;
import pratiBaza.pomocne.PredjeniPutGPS;
import pratiBaza.pomocne.StajanjeMirovanje;
import pratiBaza.tabele.Javljanja;
import pratiBaza.tabele.Obd;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemAlarmi;
import pratiBaza.tabele.Vozila;

public interface JavljanjaDAO {

	void unesiJavljanja(Javljanja javljanje);
	
	void azurirajJavljanja(Javljanja javljanje);
	
	void izbrisiJavljanja(Javljanja javljanje);
	
	Javljanja nadjiPoslednjeJavljanjePoObjektu(Objekti objekat);
	
	ArrayList<Javljanja> vratiJavljanjaObjektaOdDo(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
	
	ArrayList<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmima(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
	
	ArrayList<Javljanja> vratiJavljanjaObjektaOdDoPrvoPoslednje(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
	
	ArrayList<Obd> nadjiObdPoObjektuOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo);
	
	ArrayList<PredjeniPut> nadjiPredjeniPut(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo);
	
	ArrayList<PredjeniPutGPS> nadjiPredjeniPutGPS(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo);
	
	ArrayList<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmimaZona(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo, ArrayList<SistemAlarmi> alarmi);
	
	ArrayList<Javljanja> vratiJavljanjaObjekataOdDoSaAlarmima(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, boolean pregled);
	
	ArrayList<Javljanja> vratiJavljanjaObjekataOdDoSaBrzinama(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo);
	
	Javljanja vratiJavljanjeObjektaDoIliOd(Objekti objekat, Timestamp datumVreme, boolean vremeDo);
	
	Obd vratiObdObjektaDoIliOd(Objekti objekat, Timestamp datumVreme, boolean vremeDo);
	
	ArrayList<Javljanja> vratiJavljanjaZaStajanja(Objekti objekat);
	
	Javljanja vratiJavljanjeZaStajanje(Objekti objekat);
	
	ArrayList<StajanjeMirovanje> vratiStajanjaMirovanja(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, int duzina);
	
	ArrayList<PredjeniPut> vratiPredjeniPut(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo);
	
	ArrayList<Vozila> vratiVozilaZaServise(ArrayList<Objekti> objekti, int tipServisa, int doServisa);
}

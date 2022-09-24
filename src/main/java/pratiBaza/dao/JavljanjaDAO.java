package pratiBaza.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pratiBaza.pomocne.KontrolaGoriva;
import pratiBaza.pomocne.PredjeniPut;
import pratiBaza.pomocne.PredjeniPutGPS;
import pratiBaza.pomocne.StajanjeMirovanje;
import pratiBaza.tabele.Javljanja;
import pratiBaza.tabele.Obd;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemAlarmi;
import pratiBaza.tabele.Vozila;

public interface JavljanjaDAO {

	public void unesiJavljanja(Javljanja javljanje);
	
	public void azurirajJavljanja(Javljanja javljanje);
	
	public void izbrisiJavljanja(Javljanja javljanje);
	
	public Javljanja nadjiPoslednjeJavljanjePoObjektu(Objekti objekat);
	
	public List<Javljanja> vratiJavljanjaObjektaOdDo(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
	
	public List<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmima(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
	
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoPrvoPoslednje(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
	
	public ArrayList<Obd> nadjiObdPoObjektuOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo);
	
	public ArrayList<PredjeniPut> nadjiPredjeniPut(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo);
	
	public ArrayList<PredjeniPutGPS> nadjiPredjeniPutGPS(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo);
	
	public List<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmimaZona(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo, ArrayList<SistemAlarmi> alarmi);
	
	public List<Javljanja> vratiJavljanjaObjekataOdDoSaAlarmima(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, boolean pregled);
	
	public List<Javljanja> vratiJavljanjaObjekataOdDoSaBrzinama(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo);
	
	public Javljanja vratiJavljanjeObjektaDoIliOd(Objekti objekat, Timestamp datumVreme, boolean vremeDo);
	
	public Obd vratiObdObjektaDoIliOd(Objekti objekat, Timestamp datumVreme, boolean vremeDo);
	
	public List<Javljanja> vratiJavljanjaZaStajanja(Objekti objekat);
	
	public Javljanja vratiJavljanjeZaStajanje(Objekti objekat);
	
	public ArrayList<StajanjeMirovanje> vratiStajanjaMirovanja(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, int duzina);
	
	public ArrayList<PredjeniPut> vratiPredjeniPut(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo);
	
	public ArrayList<KontrolaGoriva> vratiKontroluGoriva(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo);
	
	public ArrayList<Vozila> vratiVozilaZaServise(ArrayList<Objekti> objekti, int tipServisa, int doServisa);
	
	public float nadjiSumuPotroseneKolicine(Objekti objakat, Timestamp datumVremeOd, Timestamp datumVremeDo);
}

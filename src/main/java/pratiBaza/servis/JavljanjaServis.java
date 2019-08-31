package pratiBaza.servis;

import java.sql.Timestamp;
import java.util.ArrayList;

import pratiBaza.pomocne.StajanjeMirovanje;
import pratiBaza.tabele.Javljanja;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemAlarmi;

public interface JavljanjaServis {

	void unesiJavljanja(Javljanja javljanje);
	
	void azurirajJavljanja(Javljanja javljanje);
	
	void izbrisiJavljanja(Javljanja javljanje);

	Javljanja nadjiPoslednjeJavljanjePoObjektu(Objekti objekat);
	
	ArrayList<Javljanja> vratiJavljanjaObjektaOdDo(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
	
	ArrayList<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmima(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
	
	ArrayList<Javljanja> vratiJavljanjaObjektaOdDoPrvoPoslednje(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
	
	ArrayList<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmimaZona(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo, ArrayList<SistemAlarmi> alarmi);
	
	ArrayList<Javljanja> vratiJavljanjaObjekataOdDoSaAlarmima(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, boolean pregled);
	
	ArrayList<Javljanja> vratiJavljanjaObjekataOdDoSaBrzinama(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo);
	
	Javljanja vratiJavljanjePoslednjeObjektaDo(Objekti objekat, Timestamp vremeDo);
	
	ArrayList<Javljanja> vratiJavljanjaZaStajanja(Objekti objekat);
	
	Javljanja vratiJavljanjeZaStajanje(Objekti objekat);
	
	ArrayList<StajanjeMirovanje> vratiStajanjaMirovanja(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, int duzina);
}

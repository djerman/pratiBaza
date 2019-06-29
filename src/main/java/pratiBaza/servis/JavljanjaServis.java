package pratiBaza.servis;

import java.sql.Timestamp;
import java.util.ArrayList;
import pratiBaza.tabele.Javljanja;
import pratiBaza.tabele.Objekti;

public interface JavljanjaServis {

	void unesiJavljanja(Javljanja javljanje);
	
	void azurirajJavljanja(Javljanja javljanje);
	
	void izbrisiJavljanja(Javljanja javljanje);

	Javljanja nadjiPoslednjeJavljanjePoObjektu(Objekti objekat);
	
	ArrayList<Javljanja> vratiJavljanjaObjektaOdDo(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
	
	ArrayList<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmima(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
	
	ArrayList<Javljanja> vratiJavljanjaObjektaOdDoPrvoPoslednje(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo);
}

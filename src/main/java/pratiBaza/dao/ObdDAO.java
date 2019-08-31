package pratiBaza.dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import pratiBaza.tabele.Obd;
import pratiBaza.tabele.Objekti;

public interface ObdDAO {
	
	void unesiObd(Obd obd);

	void azurirajObd(Obd obd);
	
	void izbrisiObd(Obd obd);
	
	Obd nadjiObdPoslednji(Objekti objekat);
	
	ArrayList<Obd> nadjiObdPoObjektuOdDo(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo);
	
	ArrayList<Obd> nadjiObdPoObjektuOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo);
	
	ArrayList<Obd> nadjiObdPoslednjaStajanja(Objekti objekat, Timestamp datumVremeOd);
}

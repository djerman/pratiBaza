package pratiBaza.servis;

import pratiBaza.tabele.Obd;
import pratiBaza.tabele.Objekti;

public interface ObdServis {
	
	void unesiObd(Obd obd);

	void azurirajObd(Obd obd);
	
	void izbrisiObd(Obd obd);
	
	Obd nadjiObdPoslednji(Objekti objekat);
}

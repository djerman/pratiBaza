package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.ObdPoslednji;
import pratiBaza.tabele.Objekti;

public interface ObdPoslednjiServis {

	void unesiObd(ObdPoslednji obdPoslednji);

	void azurirajObd(ObdPoslednji obdPoslednji);
	
	void izbrisiObd(ObdPoslednji obdPoslednje);
	
	ArrayList<ObdPoslednji> vratiListuObdPoslednjih(ArrayList<Objekti> objekti);
	
	ObdPoslednji nadjiObdPoslednjiPoObjektu(Objekti objekat);
}

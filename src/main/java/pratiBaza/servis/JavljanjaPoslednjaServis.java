package pratiBaza.servis;

import java.util.ArrayList;
import java.util.List;
import pratiBaza.tabele.JavljanjaPoslednja;
import pratiBaza.tabele.Objekti;

public interface JavljanjaPoslednjaServis {

	void unesiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje);
	
	void azurirajJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje);
	
	void izbrisiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje);
	
	List<JavljanjaPoslednja> vratiListuJavljanjaPoslednjih(ArrayList<Objekti> objekti);
	
	JavljanjaPoslednja nadjiJavljanjaPoslednjaPoObjektu(Objekti objekat);
}

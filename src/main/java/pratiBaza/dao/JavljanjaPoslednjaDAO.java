package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.JavljanjaPoslednja;
import pratiBaza.tabele.Objekti;

public interface JavljanjaPoslednjaDAO {

	void unesiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje);
	
	void azurirajJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje);
	
	void izbrisiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje);
	
	ArrayList<JavljanjaPoslednja> vratiListuJavljanjaPoslednjih(ArrayList<Objekti> objekti);
	
	JavljanjaPoslednja nadjiJavljanjaPoslednjaPoObjektu(Objekti objekat);
}

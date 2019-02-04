package pratiBaza.dao;

import pratiBaza.tabele.JavljanjaPoslednja;

public interface JavljanjaPoslednjaDAO {

	void unesiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje);
	
	void azurirajJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje);
	
	void izbrisiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje);
}

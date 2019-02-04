package pratiBaza.dao;

import pratiBaza.tabele.Javljanja;

public interface JavljanjaDAO {

	void unesiJavljanja(Javljanja javljanje);
	
	void azurirajJavljanja(Javljanja javljanje);
	
	void izbrisiJavljanja(Javljanja javljanje);
}

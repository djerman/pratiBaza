package pratiBaza.dao;

import pratiBaza.tabele.Objekti;

public interface ObjektiDAO {
	
	void unesiObjekte(Objekti objekat);

	void azurirajObjekte(Objekti objekat);
	
	void izbrisiObjekte(Objekti objekat);
}

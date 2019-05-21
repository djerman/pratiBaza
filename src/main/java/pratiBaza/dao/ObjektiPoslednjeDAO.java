package pratiBaza.dao;

import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.ObjektiPoslednje;

public interface ObjektiPoslednjeDAO {

	void unesiObjekatPoslednje(ObjektiPoslednje objekatPoslednje);
	
	void azurirajObjektiPoslednje(ObjektiPoslednje objekatPoslednje);
	
	void izbrisiObjekatPoslednje(ObjektiPoslednje objekatPoslednje);
	
	ObjektiPoslednje nadjiObjekatPoslednjePoObjektu(Objekti objekat);
	
	ObjektiPoslednje nadjiObjektiPoslednjePoId(int id);
}

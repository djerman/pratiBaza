package pratiBaza.servis;

import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.ObjektiPoslednje;

public interface ObjektiPoslednjeServis {


	void unesiObjekatPoslednje(ObjektiPoslednje objekatPoslednje);
	
	void azurirajObjektiPoslednje(ObjektiPoslednje objekatPoslednje);
	
	void izbrisiObjekatPoslednje(ObjektiPoslednje objekatPoslednje);
	
	ObjektiPoslednje nadjiObjekatPoslednjePoObjektu(Objekti objekat);
	
	ObjektiPoslednje nadjiObjektiPoslednjePoId(int id);
}

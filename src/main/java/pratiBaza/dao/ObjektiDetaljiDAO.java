package pratiBaza.dao;

import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.ObjektiDetalji;

public interface ObjektiDetaljiDAO {

	void unesiObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	void azurirajObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	void izbrisiObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	ObjektiDetalji nadjiObjekatDetaljePoObjektu(Objekti objekti);
}

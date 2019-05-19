package pratiBaza.servis;

import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.ObjektiDetalji;

public interface ObjektiDetaljiServis {

	void unesiObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	void azurirajObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	void izbrisiObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	ObjektiDetalji nadjiObjekatDetaljePoObjektu(Objekti objekti);
}

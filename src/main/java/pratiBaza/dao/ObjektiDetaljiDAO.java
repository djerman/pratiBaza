package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.ObjektiDetalji;

public interface ObjektiDetaljiDAO {

	void unesiObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	void azurirajObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	void izbrisiObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	ObjektiDetalji nadjiObjekatDetaljePoObjektu(Objekti objekti);
	
	ObjektiDetalji nadjiObjektiDetaljiPoId(int id);
	
	ArrayList<ObjektiDetalji> vratisveObjekatDetalje(Korisnici korisnik, boolean aktivan);
}

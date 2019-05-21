package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.ObjektiDetalji;

public interface ObjektiDetaljiServis {

	void unesiObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	void azurirajObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	void izbrisiObjektiDetalji(ObjektiDetalji objekatDetalj);
	
	ObjektiDetalji nadjiObjekatDetaljePoObjektu(Objekti objekti);
	
	ObjektiDetalji nadjiObjektiDetaljiPoId(int id);
	
	ArrayList<ObjektiDetalji> vratisveObjekatDetalje(Korisnici korisnik, boolean aktivan);
}

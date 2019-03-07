package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.SistemUredjajiModeli;

public interface SistemUredjajiModeliServis {

	void unesiUredjajModel(SistemUredjajiModeli model);
	
	void izmeniUredjajModel(SistemUredjajiModeli model);
	
	void izbrisiUredjajModel(SistemUredjajiModeli model);
	
	ArrayList<SistemUredjajiModeli> nadjiSveUredjajModele();
}

package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.SistemUredjajiModeli;

public interface SistemUredjajiModeliDAO {

	void unesiUredjajModel(SistemUredjajiModeli model);
	
	void izmeniUredjajModel(SistemUredjajiModeli model);
	
	void izbrisiUredjajModel(SistemUredjajiModeli model);
	
	ArrayList<SistemUredjajiModeli> nadjiSveUredjajModele();
}

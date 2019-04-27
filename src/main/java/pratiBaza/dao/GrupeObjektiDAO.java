package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.GrupeObjekti;

public interface GrupeObjektiDAO {
	
	void unesiGrupaObjekat(GrupeObjekti grupaObjekat);
	
	void azurirajGrupaObjekat(GrupeObjekti grupaObjekat);
	
	void izbrisiGrupaObjekat(GrupeObjekti grupaObjekat);

	GrupeObjekti nadjiGrupaObjekatPoId(int id);
	
	void izbrisiSveGrupaObjekti(Grupe grupa);
	
	ArrayList<GrupeObjekti> nadjiSveGrupaObjektePoGrupi(Grupe grupa);
}

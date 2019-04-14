package pratiBaza.dao;

import pratiBaza.tabele.GrupeObjekti;

public interface GrupeObjektiDAO {
	
	void unesiGrupaObjekat(GrupeObjekti grupaObjekat);
	
	void azurirajGrupaObjekat(GrupeObjekti grupaObjekat);
	
	void izbrisiGrupaObjekat(GrupeObjekti grupaObjekat);

	GrupeObjekti nadjiGrupaObjekatPoId(int id);
}

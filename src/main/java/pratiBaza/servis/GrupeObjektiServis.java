package pratiBaza.servis;

import pratiBaza.tabele.GrupeObjekti;

public interface GrupeObjektiServis {
	
	void unesiGrupaObjekat(GrupeObjekti grupaObjekat);
	
	void azurirajGrupaObjekat(GrupeObjekti grupaObjekat);
	
	void izbrisiGrupaObjekat(GrupeObjekti grupaObjekat);

	GrupeObjekti nadjiGrupaObjekatPoId(int id);
}

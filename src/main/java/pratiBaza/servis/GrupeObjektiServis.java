package pratiBaza.servis;

import java.util.ArrayList;
import java.util.List;

import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.GrupeObjekti;
import pratiBaza.tabele.Objekti;

public interface GrupeObjektiServis {
	
	void unesiGrupaObjekat(GrupeObjekti grupaObjekat);
	
	void azurirajGrupaObjekat(GrupeObjekti grupaObjekat);
	
	void izbrisiGrupaObjekat(GrupeObjekti grupaObjekat);

	GrupeObjekti nadjiGrupaObjekatPoId(int id);
	
	void izbrisiSveGrupaObjekti(Grupe grupa);
	
	List<GrupeObjekti> nadjiSveGrupaObjektePoGrupi(Grupe grupa);
	
	void izbrisiSveGrupeObjekatPoObjektu(Objekti objekat);
	
	List<GrupeObjekti> nadjiSveGrupaObjektePoObjektu(Objekti objekat);
	
	List<GrupeObjekti> nadjiSveGraupeObjektePoGrupama(ArrayList<Grupe> grupe);
	
	List<Objekti> nadjiSveObjektePoGrupi(Grupe grupa);
	
	List<Objekti> nadjiSveObjektePoGrupiSaVozilom(Grupe grupa);
	
	ArrayList<Objekti> nadjiSveObjektePoGrupama(ArrayList<Grupe> grupe);
}

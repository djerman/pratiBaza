package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.GrupeObjekti;
import pratiBaza.tabele.Objekti;

public interface GrupeObjektiServis {
	
	void unesiGrupaObjekat(GrupeObjekti grupaObjekat);
	
	void azurirajGrupaObjekat(GrupeObjekti grupaObjekat);
	
	void izbrisiGrupaObjekat(GrupeObjekti grupaObjekat);

	GrupeObjekti nadjiGrupaObjekatPoId(int id);
	
	void izbrisiSveGrupaObjekti(Grupe grupa);
	
	ArrayList<GrupeObjekti> nadjiSveGrupaObjektePoGrupi(Grupe grupa);
	
	void izbrisiSveGrupeObjekatPoObjektu(Objekti objekat);
	
	ArrayList<GrupeObjekti> nadjiSveGrupaObjektePoObjektu(Objekti objekat);
	
	ArrayList<GrupeObjekti> nadjiSveGraupeObjektePoGrupama(ArrayList<Grupe> grupe);
	
	ArrayList<Objekti> nadjiSveObjektePoGrupi(Grupe grupa);
	
	ArrayList<Objekti> nadjiSveObjektePoGrupama(ArrayList<Grupe> grupe);
}

package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.GrupeObjekti;
import pratiBaza.tabele.Objekti;

public interface GrupeObjektiDAO {
	
	public void unesiGrupaObjekat(GrupeObjekti grupaObjekat);
	
	public void azurirajGrupaObjekat(GrupeObjekti grupaObjekat);
	
	public void izbrisiGrupaObjekat(GrupeObjekti grupaObjekat);

	public GrupeObjekti nadjiGrupaObjekatPoId(int id);
	
	public void izbrisiSveGrupaObjekti(Grupe grupa);
	
	public ArrayList<GrupeObjekti> nadjiSveGrupaObjektePoGrupi(Grupe grupa);
	
	public void izbrisiSveGrupeObjekatPoObjektu(Objekti objekat);
	
	public ArrayList<GrupeObjekti> nadjiSveGrupaObjektePoObjektu(Objekti objekat);
	
	public ArrayList<GrupeObjekti> nadjiSveGrupeObjektePoGrupama(ArrayList<Grupe> grupe);
	
	public ArrayList<Objekti> nadjiSveObjektePoGrupi(Grupe grupa);
	
	public ArrayList<Objekti> nadjiSveObjektePoGrupiSaVozilom(Grupe grupa);
	
	public ArrayList<Objekti> nadjiSveObjektePoGrupama(ArrayList<Grupe> grupe);
}

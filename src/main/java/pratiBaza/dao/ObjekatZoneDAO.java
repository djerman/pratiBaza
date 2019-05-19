package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Zone;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.ObjekatZone;

public interface ObjekatZoneDAO {
	
	void unesiZonaObjekat(ObjekatZone zonaObjekat);
	
	void izmeniZonaObjekat(ObjekatZone zonaObjekat);
	
	void izbrisiZonaObjekat(ObjekatZone zonaObjekat);
	
	ArrayList<ObjekatZone> nadjiZoneObjektePoObjektu(Objekti objekat);
	
	ArrayList<ObjekatZone> nadjiZoneObjektePoZoni(Zone zona);
	
	void izbrisiZoneObjektiPoObjektu(Objekti objekat);
	
	void izbrisiZoneObjektiPOZoni(Zone zona);
	
	ObjekatZone nadjiObjekatZoniPoId(int id);
	
	ArrayList<ObjekatZone> vratiSveObjekatZone(Korisnici korisnik, boolean aktivan);

}

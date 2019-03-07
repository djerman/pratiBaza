package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Zone;
import pratiBaza.tabele.ZoneObjekti;

public interface ZoneObjektiServis {
	
	void unesiZonaObjekat(ZoneObjekti zonaObjekat);
	
	void izmeniZonaObjekat(ZoneObjekti zonaObjekat);
	
	void izbrisiZonaObjekat(ZoneObjekti zonaObjekat);
	
	ArrayList<ZoneObjekti> nadjiZoneObjektePoObjektu(Objekti objekat);
	
	ArrayList<ZoneObjekti> nadjiZoneObjektePoZoni(Zone zona);
}

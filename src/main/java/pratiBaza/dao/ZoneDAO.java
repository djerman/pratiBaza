package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Zone;

public interface ZoneDAO {
	
	void unesiZonu(Zone zona);
	
	void izmeniZonu(Zone zona);
	
	void izbrisiZonu(Zone zona);
	
	ArrayList<Zone> nadjiSveZone(Korisnici korisnik, boolean aktivan);
	
	ArrayList<Zone> nadjiSveZonePoPretplatniku(SistemPretplatnici pretplatnik);
	
	ArrayList<Zone> nadjiSveZonePoOrganizaciji(Organizacije organizacija);
	
	Zone nadjiZonuPoId(int id);
}

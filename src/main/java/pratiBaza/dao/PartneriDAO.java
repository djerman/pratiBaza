package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Partneri;
import pratiBaza.tabele.SistemPretplatnici;

public interface PartneriDAO {

	void unesiPartnera(Partneri partner);
	
	void izmeniPartnera(Partneri partner);
	
	void izbrisiPartnera(Partneri partner);
	
	Partneri nadjiPartneraPoId(int id);
	
	Partneri nadjiPartneraPoPibu(SistemPretplatnici pretplatnik, int pib);
	
	ArrayList<Partneri> nadjiSvePartnere(Korisnici korisnik, boolean izbrisan);
	
	ArrayList<Partneri> nadjiSvePartnerePoPretplatniku(SistemPretplatnici pretplatnik, boolean izbrisan);
}

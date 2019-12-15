package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaSaobracajne;

public interface VozilaSaobracajneDAO {

	void unesiSaobracajnu(VozilaSaobracajne saobracajna);
	
	void izmeniSaobracajnu(VozilaSaobracajne saobracajna);
	
	void izbrisiSaobracajnu(VozilaSaobracajne saobracajna);
	
	VozilaSaobracajne nadjiSaobracajnuPoId(int id);
	
	VozilaSaobracajne nadjiSaobracajnuPoBroju(String broj);
	
	ArrayList<VozilaSaobracajne> nadjiSveSaobracajne(Korisnici korisnik, boolean izbrisan);
	
	ArrayList<VozilaSaobracajne> nadjiSlobodneSaobracajne(Korisnici korisnik, boolean izbrisan);
	
	ArrayList<VozilaSaobracajne> nadjiSlobodneSaobracajnePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	VozilaSaobracajne nadjiSaobracajnuPoVozilu(Vozila vozilo);
}

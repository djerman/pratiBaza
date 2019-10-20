package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Vozaci;

public interface VozaciDAO {

	void unesiVozaca(Vozaci vozac);
	
	void izmeniVozaca(Vozaci vozac);
	
	void izbrisiVozaca(Vozaci vozac);
	
	Vozaci nadjiVozacaPoId(int id);
	
	Vozaci nadjiVozacaPoKorisniku(Korisnici korisnik);// poslednji unos za vozaca kome je vreme zaposlenDo null ili mladje
	
	ArrayList<Vozaci> nadjiSveVozacePoKorisniku(Korisnici korisnik);//svaki unos vozaca za određenog korisnika
	
	ArrayList<Vozaci> nadjiSveVozace(Korisnici korisnik);
	
	ArrayList<Vozaci> nadjiSveVozacePoPretplatniku(SistemPretplatnici pretplatnik);
	
	ArrayList<Vozaci> nadjiSveVozacePoOrganizaciji(Organizacije organizacija);
}

package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Sim;
import pratiBaza.tabele.SistemPretplatnici;

public interface SimServis {

	void unesiSim(Sim sim);
	
	void azurirajSim(Sim sim);
	
	void izbrisiSim(Sim sim);
	
	ArrayList<Sim> vratiSveSimKartice(Korisnici korisnik, boolean aktivan);
	
	Sim nadjiSimPoID(int id);
	
	ArrayList<Sim> vratiSveAktivneSimKartice(SistemPretplatnici pretplatnici, Organizacije organizacija, Sim sim);
	
	ArrayList<Sim> vratiSveSlobodneSim(Korisnici korisnik, SistemPretplatnici pretplatnici, Organizacije organizacija, Sim sim);
}

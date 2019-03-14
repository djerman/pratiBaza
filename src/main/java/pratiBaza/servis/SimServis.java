package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.Sim;

public interface SimServis {

	void unesiSim(Sim sim);
	
	void azurirajSim(Sim sim);
	
	void izbrisiSim(Sim sim);
	
	ArrayList<Sim> vratiSveSimKartice();
}

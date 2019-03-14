package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.Sim;

public interface SimDAO {

	void unesiSim(Sim sim);
	
	void azurirajSim(Sim sim);
	
	void izbrisiSim(Sim sim);
	
	ArrayList<Sim> vratiSveSimKartice();
}

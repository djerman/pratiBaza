package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.SistemUredjajiProizvodjac;

public interface SistemUredjajiProizvodjaciServis {

	void unesiSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac);
	
	void izmeniSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac);
	
	void izbrisiSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac);
	
	ArrayList<SistemUredjajiProizvodjac> nadjiSveSistemUredjajeProizvodjace();
	
	SistemUredjajiProizvodjac nadjiProizvodjacaPoId(int id);
}

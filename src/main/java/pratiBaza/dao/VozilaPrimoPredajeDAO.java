package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.VozilaPrimoPredaje;

public interface VozilaPrimoPredajeDAO {

	void unesiVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja);
	
	void izmeniVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja);
	
	void izbrisiVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja);
	
	VozilaPrimoPredaje nadjiVoziloPrimoPredajaPoId(int id);
	
	VozilaPrimoPredaje nadjiVoziloPripmoPredajuPoVozilu(Objekti objekat);
	
	ArrayList<VozilaPrimoPredaje> nadjiSveVozilaPrimoPredajePoObjektu(Objekti objekat);
	
	ArrayList<VozilaPrimoPredaje> nadjiSveVozilaPrimoPredaje(Korisnici korisnik);
}

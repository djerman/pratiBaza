package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaPrimoPredaje;

public interface VozilaPrimoPredajeDAO {

	void unesiVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja);
	
	void izmeniVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja);
	
	void izbrisiVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja);
	
	VozilaPrimoPredaje nadjiVoziloPrimoPredajaPoId(int id);
	
	VozilaPrimoPredaje nadjiVoziloPrimoPredajuPoVozilu(Vozila vozilo);
	
	ArrayList<VozilaPrimoPredaje> nadjiSveVozilaPrimoPredajePoVozilu(Vozila vozilo);
	
	ArrayList<VozilaPrimoPredaje> nadjiSveVozilaPrimoPredaje(Korisnici korisnik);
}

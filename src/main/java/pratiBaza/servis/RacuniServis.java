package pratiBaza.servis;

import java.util.ArrayList;
import java.util.Date;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Partneri;
import pratiBaza.tabele.Racuni;
import pratiBaza.tabele.SistemPretplatnici;

public interface RacuniServis {

	public void unesiRacun(Racuni racun);
	
	public void izmeniRacun(Racuni racun);
	
	public void izbrisiRacun(Racuni racun);
	
	public Racuni nadjiRacunPoId(int id);
	
	public ArrayList<Racuni> nadjiRacunePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan, Date datumOd, Date DatumDo, Partneri partner);
	
}

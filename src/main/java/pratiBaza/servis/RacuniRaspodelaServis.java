package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Partneri;
import pratiBaza.tabele.Racuni;
import pratiBaza.tabele.RacuniRaspodela;

public interface RacuniRaspodelaServis {
	
	public void unesiRacunRaspodelu(RacuniRaspodela raspodela);
	
	public void izmeniRacunRaspodelu(RacuniRaspodela raspodela);
	
	public void izbrisiRacunRaspodelu(RacuniRaspodela raspodela);
	
	public RacuniRaspodela nadjiRacunRaspodeluPoId(int id);
	
	public ArrayList<RacuniRaspodela> nadjiRacuneRaspodelePoRacunu(Racuni racun);
	
	public ArrayList<RacuniRaspodela> nadjiRacuneRaspodelePoPartneru(Partneri partner);

}

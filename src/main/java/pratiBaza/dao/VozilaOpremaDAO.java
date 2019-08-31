package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozilaOprema;

public interface VozilaOpremaDAO {

	void unesiVoziloOpremu(VozilaOprema oprema);
	
	void izmeniVoziloOpremu(VozilaOprema oprema);
	
	void izbrisiVoziloOpremu(VozilaOprema oprema);
	
	VozilaOprema nadjiVoziloOpremuPoId(int id);
	
	ArrayList<VozilaOprema> nadjiSveVozilaOprema(Korisnici korisnik);
}

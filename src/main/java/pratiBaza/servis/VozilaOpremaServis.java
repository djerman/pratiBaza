package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozilaOprema;

public interface VozilaOpremaServis {

	void unesiVoziloOpremu(VozilaOprema oprema);
	
	void izmeniVoziloOpremu(VozilaOprema oprema);
	
	void izbrisiVoziloOpremu(VozilaOprema oprema);
	
	VozilaOprema nadjiVoziloOpremuPoId(int id);
	
	ArrayList<VozilaOprema> nadjiSveVozilaOprema(Korisnici korisnik);
}

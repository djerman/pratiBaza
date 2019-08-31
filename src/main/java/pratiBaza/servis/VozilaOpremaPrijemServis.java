package pratiBaza.servis;

import java.util.ArrayList;
import java.util.List;
import pratiBaza.tabele.VozilaOpremaPrijem;
import pratiBaza.tabele.VozilaPrimoPredaje;

public interface VozilaOpremaPrijemServis {

	void unesiVoziloOpremaPredaja(VozilaOpremaPrijem predaja);
	
	void izmeniVoziloOpremaPredaja(VozilaOpremaPrijem predaja);
	
	void izbrisiVoziloOpremaPredaja(VozilaOpremaPrijem predaja);
	
	void izbrisiSvaVoziloOpremaPredaja(VozilaPrimoPredaje primoPredaja);
	
	void unesiSvaVoziloOpremaPredaja(List<VozilaOpremaPrijem> opremaStavke);
	
	VozilaOpremaPrijem nadjiVoziloOpremaPradajaPoId(int id);
	
	ArrayList<VozilaOpremaPrijem> nadjiVozilaOpremaPredajaPoPP(VozilaPrimoPredaje primoPredaja);
}

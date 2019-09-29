package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozilaSaobracajne;

public interface VozilaSaobracajneDAO {

	void unesiSaobracajnu(VozilaSaobracajne saobracajna);
	
	void izmeniSaobracajnu(VozilaSaobracajne saobracajna);
	
	void izbrisiSaobracajnu(VozilaSaobracajne saobracajna);
	
	VozilaSaobracajne nadjiSaobracajnuPoId(int id);
	
	VozilaSaobracajne nadjiSaobracajnuPoBroju(String broj);
	
	ArrayList<VozilaSaobracajne> nadjiSveSaobracajne(Korisnici korisnik, boolean izbrisan);
}

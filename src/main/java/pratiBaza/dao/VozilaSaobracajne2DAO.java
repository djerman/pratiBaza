package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozilaSaobracajne;
import pratiBaza.tabele.VozilaSaobracajne2;

public interface VozilaSaobracajne2DAO {
	
	void unesiSaobracajnu2(VozilaSaobracajne2 saobracajna2);
	
	void izmeniSaobracajnu2(VozilaSaobracajne2 saobracajna2);
	
	void izbrisiSaobracajnu2(VozilaSaobracajne2 saobracajna2);
	
	VozilaSaobracajne2 nadjiSaobracajnu2PoId(int id);
	
	VozilaSaobracajne2 nadjiSaobracajnu2PoBroju(VozilaSaobracajne saobracajna);
	
	ArrayList<VozilaSaobracajne2> nadjiSveSaobracajne2(Korisnici korisnik, boolean izbrisan);
}

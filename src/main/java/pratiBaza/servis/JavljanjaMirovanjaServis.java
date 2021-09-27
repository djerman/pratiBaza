package pratiBaza.servis;

import java.util.ArrayList;
import java.util.List;
import pratiBaza.tabele.JavljanjaMirovanja;
import pratiBaza.tabele.Objekti;

public interface JavljanjaMirovanjaServis {

	void unesiJavljanjaMirovanja(JavljanjaMirovanja javljanje);
	
	void azurirajJavljanajMirovanja(JavljanjaMirovanja javljanje);
	
	void izbrisijavljanjaMirovanja(JavljanjaMirovanja javljanje);
	
	List<JavljanjaMirovanja> vratiListuJavljanjaMirovanja(ArrayList<Objekti> objekti);
	
	JavljanjaMirovanja nadjiJavljanjaMirovanjaPoObjektu(Objekti objekat);
}

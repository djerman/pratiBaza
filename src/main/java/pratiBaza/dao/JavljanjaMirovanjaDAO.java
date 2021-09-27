package pratiBaza.dao;

import java.util.ArrayList;
import java.util.List;

import pratiBaza.tabele.JavljanjaMirovanja;
import pratiBaza.tabele.Objekti;

public interface JavljanjaMirovanjaDAO {

	public void unesiJavljanjaMirovanja(JavljanjaMirovanja javljanje);
	
	public void azurirajJavljanajMirovanja(JavljanjaMirovanja javljanje);
	
	public void izbrisijavljanjaMirovanja(JavljanjaMirovanja javljanje);
	
	public List<JavljanjaMirovanja> vratiListuJavljanjaMirovanja(ArrayList<Objekti> objekti);
	
	public JavljanjaMirovanja nadjiJavljanjaMirovanjaPoObjektu(Objekti objekat);
}

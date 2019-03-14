package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemPretplatnici;

public interface ObjektiDAO {
	
	void unesiObjekte(Objekti objekat);

	void azurirajObjekte(Objekti objekat);
	
	void izbrisiObjekte(Objekti objekat);
	
	ArrayList<Objekti> vratiSveObjekte();
	
	ArrayList<Objekti> vratiObjektePoPretplatniku(SistemPretplatnici pretplatnik);
	
	ArrayList<Objekti> vratiObjektePoKorisniku(Korisnici korisnik);
	
	ArrayList<Objekti> vratiObjektePoGrupi(Grupe grupa);
}

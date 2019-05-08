package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

public interface ObjektiServis {
	
	void unesiObjekte(Objekti objekat);

	void azurirajObjekte(Objekti objekat);
	
	void izbrisiObjekte(Objekti objekat);
	
	ArrayList<Objekti> vratiSveObjekte(Korisnici korisnik, boolean aktivan);
	
	ArrayList<Objekti> vratiObjektePoPretplatniku(SistemPretplatnici pretplatnik);
	
	ArrayList<Objekti> vratiObjektePoKorisniku(Korisnici korisnik);
	
	ArrayList<Objekti> vratiObjektePoGrupi(Grupe grupa);
	
	Objekti nadjiObjekatPoId(int id);
	
	ArrayList<Objekti> vratiSveObjekte(SistemPretplatnici pretplatnik, Organizacije organizacija);
}

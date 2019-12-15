package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

public interface ObjektiServis {
	
	void unesiObjekte(Objekti objekat);

	void azurirajObjekte(Objekti objekat);
	
	void izbrisiObjekte(Objekti objekat);
	
	ArrayList<Objekti> vratiSveObjekte(Korisnici korisnik, boolean aktivan);
	
	ArrayList<Objekti> vratiObjektePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean aktivan);
	
	ArrayList<Objekti> vratiSvaVozila(Korisnici korisnik, boolean aktivan);
	
	ArrayList<Objekti> vratiObjektePoGrupi(Grupe grupa);
	
	Objekti nadjiObjekatPoId(int id);
	
	ArrayList<Objekti> vratiSveObjekte(SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	ArrayList<Objekti> vratiSveObjekteVozila(SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	Objekti nadjiObjekatPoUredjaju(Uredjaji uredjaj);
	
	ArrayList<Objekti> nadjiSveObjekteSavozilom(SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	ArrayList<Objekti> nadjiSveObjekteBezVozila(SistemPretplatnici pretplatnik, Organizacije organizacija);
}

package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

public interface ObjektiDAO {
	
	void unesiObjekte(Objekti objekat);

	void azurirajObjekte(Objekti objekat);
	
	void izbrisiObjekte(Objekti objekat);
	
	ArrayList<Objekti> vratiSveObjekte(Korisnici korisnik, boolean aktivan);
	
	ArrayList<Objekti> vratiObjektePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean aktivan);
	
	ArrayList<Objekti> vratiObjektePoKorisniku(Korisnici korisnik);
	
	ArrayList<Objekti> vratiObjektePoGrupi(Grupe grupa);
	
	Objekti nadjiObjekatPoId(int id);
	
	ArrayList<Objekti> vratiSveObjekte(SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	Objekti nadjiObjekatPoUredjaju(Uredjaji uredjaj);
	
}

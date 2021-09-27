package pratiBaza.dao;

import java.util.List;
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
	
	List<Objekti> vratiSveObjekte(Korisnici korisnik, boolean aktivan);
	
	List<Objekti> vratiObjektePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean aktivan);
	
	List<Objekti> vratiSvaVozila(Korisnici korisnik, boolean aktivan);
	
	List<Objekti> vratiObjektePoGrupi(Grupe grupa);
	
	Objekti nadjiObjekatPoId(int id);
	
	List<Objekti> vratiSveObjekte(SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	List<Objekti> vratiSveObjekteVozila(SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	Objekti nadjiObjekatPoUredjaju(Uredjaji uredjaj);
	
	List<Objekti> nadjiSveObjekteSavozilom(SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	List<Objekti> nadjiSveObjekteBezVozila(SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	Objekti nadjiObjekatSadrzi(SistemPretplatnici pretplatnik, String oznaka);
}

package pratiBaza.dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Racuni;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Troskovi;

public interface TroskoviDAO {

	public void unesiTrosak(Troskovi trosak);
	
	public void izmeniTrosak(Troskovi trosak);
	
	public void izbrisiTrosak(Troskovi trosak);
	
	public Troskovi nadjiTrosakPoId(int id);
	
	public ArrayList<Troskovi> nadjiSveTroskovePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan);
	
	public ArrayList<Troskovi> nadjiSveTroskove(Korisnici korisnik);
	
	public ArrayList<Troskovi> nadjiSvaOdrzavanjaPoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan);
	
	public ArrayList<Troskovi> nadjiSvaOdrzavanja(Korisnici korisnik);
	
	public ArrayList<Troskovi> nadjiSvuPotrosnjuPoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan);
	
	public ArrayList<Troskovi> nadjiSvuPotrosnju(Korisnici korisnik);
	
	public Troskovi nadjiPoslednjiTrosakDo(Timestamp datumVreme, int tipTroska);
	
	public ArrayList<Troskovi> nadjiSveTroskoveOd(Timestamp datumVremeOd, SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	public ArrayList<Troskovi> nadjiSveTroskoveUkupno(ArrayList<Objekti> vozila, Timestamp datumVremeOd, Timestamp datumVremeDo, Integer tipTroska);
	
	public ArrayList<Troskovi> nadjiSvuPotrosnjuPoRacunu(Racuni racun);
}

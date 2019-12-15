package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Projekti;
import pratiBaza.tabele.SistemPretplatnici;

public interface ProjektiServis {

	public void unesiProjekat(Projekti projekat);
	
	public void izmeniProjekat(Projekti projekat);
	
	public void izbrisiProjekat(Projekti projekat);
	
	public Projekti nadjiProjekatPoId(int id);
	
	public ArrayList<Projekti> nadjiSveProjekte(Korisnici korisnik);
	
	public ArrayList<Projekti> nadjiSveProjektePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan);

}

package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

public interface UredjajiServis {

	void unesiUredjaj(Uredjaji uredjaj);
	
	void izmeniUredjaj(Uredjaji uredjaj);
	
	void izbrisiUredjaj(Uredjaji uredjaj);
	
	ArrayList<Uredjaji> nadjiSveUredjaje(Korisnici korisnik, boolean aktivan);
	
	ArrayList<Uredjaji> nadjiSveAktivneSlobodneUredjajePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, Uredjaji uredjaj);
	
	ArrayList<Uredjaji> nadjiSveAktivneUredjaje(Korisnici korisnik, Uredjaji uredjaj);
	
	Uredjaji nadjiUredjajPoId(int id);
}

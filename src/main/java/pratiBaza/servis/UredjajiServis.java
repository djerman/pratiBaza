package pratiBaza.servis;

import java.util.List;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

public interface UredjajiServis {

	void unesiUredjaj(Uredjaji uredjaj);
	
	void izmeniUredjaj(Uredjaji uredjaj);
	
	void izbrisiUredjaj(Uredjaji uredjaj);
	
	List<Uredjaji> nadjiSveUredjaje(Korisnici korisnik, boolean aktivan);
	
	List<Uredjaji> nadjiSveAktivneSlobodneUredjajePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	List<Uredjaji> nadjiSveAktivneSlobodneUredjaje(Korisnici korisnik, SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	List<Uredjaji> nadjiSveAktivneUredjaje(Korisnici korisnik, Uredjaji uredjaj);
	
	Uredjaji nadjiUredjajPoId(int id);
	
	Uredjaji nadjiUredjajPoKodu(String kod);
}

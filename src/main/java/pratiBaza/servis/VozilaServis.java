package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaSaobracajne;

public interface VozilaServis {

	void unesiVozilo(Vozila vozilo);
	
	void azurirajVozilo(Vozila objekatDetalj);
	
	void izbrisiVozilo(Vozila objekatDetalj);

	Vozila nadjiVoziloPoObjektu(Objekti objekti);
	
	Vozila nadjiVoziloPoId(int id);
	
	ArrayList<Vozila> vratisvaVozila(Korisnici korisnik, boolean aktivan);
	
	Vozila vratiVoziloPoSaobracajnoj(VozilaSaobracajne saobracajna);
	
	ArrayList<Vozila> nadjisvaVozilaPoPretplatniku(SistemPretplatnici pretplatnik);
	
	ArrayList<Vozila> nadjisvaVozilaPoOrganizaciji(Organizacije organizacija);
	
	ArrayList<Vozila> nadjisvaVozilaPoObjektima(ArrayList<Objekti> objekti);
	
	ArrayList<Vozila> nadjiSvaVozilaBezSaobracajne(Korisnici korisnik, boolean aktivan);
	
	ArrayList<Vozila> nadjiSvaVozilaBezSaobracajnePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija);
	
	Vozila nadjiVoziloPoRegistraciji(SistemPretplatnici pretplatnik, String registracija);
}

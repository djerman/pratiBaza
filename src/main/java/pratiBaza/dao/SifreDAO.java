package pratiBaza.dao;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Sifre;
import pratiBaza.tabele.SistemPretplatnici;

public interface SifreDAO {

	public void unesiSifru(Sifre sifra);
	
	public void izmeniSifru(Sifre sifra);
	
	public void izbrisiSifru(Sifre sifra);
	
	public Sifre nadjiSifruPoId(int id);
	
	public ArrayList<Sifre> nadjiSveSifre(Korisnici korisnik);
	
	public ArrayList<Sifre> nadjiSveSifrePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan);
}

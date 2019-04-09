package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;

public interface OrganizacijeDAO {
	
	void unesiOrganizacije(Organizacije organizacija);

	void azurirajOrganizacije(Organizacije organizacija);
	
	void izbrisiOrganizacije(Organizacije organizacija);
	
	ArrayList<Organizacije> nadjiSveOrganizacije(Korisnici korisnik);
}

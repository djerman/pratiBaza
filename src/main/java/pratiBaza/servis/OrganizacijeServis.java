package pratiBaza.servis;

import java.util.ArrayList;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;

public interface OrganizacijeServis {
	
	void unesiOrganizacije(Organizacije organizacija);

	void azurirajOrganizacije(Organizacije organizacija);
	
	void izbrisiOrganizacije(Organizacije organizacija);
	
	ArrayList<Organizacije> nadjiSveOrganizacije(Korisnici korisnik);
}

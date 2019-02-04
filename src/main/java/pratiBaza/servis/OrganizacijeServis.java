package pratiBaza.servis;

import pratiBaza.tabele.Organizacije;

public interface OrganizacijeServis {
	
	void unesiOrganizacije(Organizacije organizacija);

	void azurirajOrganizacije(Organizacije organizacija);
	
	void izbrisiOrganizacije(Organizacije organizacija);
}

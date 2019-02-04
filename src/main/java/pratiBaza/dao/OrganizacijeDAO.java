package pratiBaza.dao;

import pratiBaza.tabele.Organizacije;

public interface OrganizacijeDAO {
	
	void unesiOrganizacije(Organizacije organizacija);

	void azurirajOrganizacije(Organizacije organizacija);
	
	void izbrisiOrganizacije(Organizacije organizacija);
}

package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.OrganizacijeDAO;
import pratiBaza.servis.OrganizacijeServis;
import pratiBaza.tabele.Organizacije;

@Service("organizacijaServis")
public class OrganizacijeServisImpl implements OrganizacijeServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	OrganizacijeDAO organizacijaDAO;

	@Transactional
	public void unesiOrganizacije(Organizacije organizacija) {
		organizacijaDAO.unesiOrganizacije(organizacija);
	}

	@Transactional
	public void azurirajOrganizacije(Organizacije organizacija) {
		organizacijaDAO.azurirajOrganizacije(organizacija);
	}

	@Transactional
	public void izbrisiOrganizacije(Organizacije organizacija) {
		organizacijaDAO.izbrisiOrganizacije(organizacija);
	}

	@Transactional
	public OrganizacijeDAO getOrganizacijaDAO() {
		return organizacijaDAO;
	}

	@Transactional
	public void setOrganizacijaDAO(OrganizacijeDAO organizacijaDAO) {
		this.organizacijaDAO = organizacijaDAO;
	}
	
}

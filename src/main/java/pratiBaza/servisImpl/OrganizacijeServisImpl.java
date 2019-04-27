package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.OrganizacijeDAO;
import pratiBaza.servis.OrganizacijeServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

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

	@Transactional
	public ArrayList<Organizacije> nadjiSveOrganizacije(Korisnici korisnik,  boolean aktivan) {
		return organizacijaDAO.nadjiSveOrganizacije(korisnik, aktivan);
	}

	@Transactional
	public Organizacije nadjiOrganizacijuPoId(int id) {
		return organizacijaDAO.nadjiOrganizacijuPoId(id);
	}

	@Transactional
	public ArrayList<Organizacije> nadjiSveOrganizacije(SistemPretplatnici pretplatnik, boolean aktivan) {
		return organizacijaDAO.nadjiSveOrganizacije(pretplatnik, aktivan);
	}
	
}

package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.GrupeDAO;
import pratiBaza.servis.GrupeServis;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;

@Service("grupaServis")
public class GrupeServisImpl implements GrupeServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	GrupeDAO grupaDAO;

	@Transactional
	public void unesiGrupu(Grupe grupa) {
		grupaDAO.unesiGrupu(grupa);
	}

	@Transactional
	public void azurirajGrupu(Grupe grupa) {
		grupaDAO.azurirajGrupu(grupa);
	}

	@Transactional
	public void izbrisiGrupu(Grupe grupa) {
		grupaDAO.izbrisiGrupu(grupa);
	}

	@Transactional
	public GrupeDAO getGrupaDAO() {
		return grupaDAO;
	}

	@Transactional
	public void setGrupaDAO(GrupeDAO grupaDAO) {
		this.grupaDAO = grupaDAO;
	}

	@Transactional
	public ArrayList<Grupe> vratiGrupe(Korisnici korisnik) {
		return grupaDAO.vratiGrupe(korisnik);
	}
	
}

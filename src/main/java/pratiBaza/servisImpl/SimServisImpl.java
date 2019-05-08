package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.SimDAO;
import pratiBaza.servis.SimServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Sim;
import pratiBaza.tabele.SistemPretplatnici;

@Service("simServis")
public class SimServisImpl implements SimServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	SimDAO simDAO;

	@Transactional
	public void unesiSim(Sim sim) {
		simDAO.unesiSim(sim);
	}

	@Transactional
	public void azurirajSim(Sim sim) {
		simDAO.azurirajSim(sim);
	}

	@Transactional
	public void izbrisiSim(Sim sim) {
		simDAO.izbrisiSim(sim);
	}

	@Transactional
	public SimDAO getSimDAO() {
		return simDAO;
	}

	@Transactional
	public void setSimDAO(SimDAO simDAO) {
		this.simDAO = simDAO;
	}

	@Transactional
	public ArrayList<Sim> vratiSveSimKartice(Korisnici korisnik, boolean aktivan) {
		return simDAO.vratiSveSimKartice(korisnik, aktivan);
	}

	@Transactional
	public Sim nadjiSimPoID(int id) {
		return simDAO.nadjiSimPoID(id);
	}

	@Transactional
	public ArrayList<Sim> vratiSveAktivneSimKartice(SistemPretplatnici pretplatnici, Organizacije organizacija, Sim sim) {
		return simDAO.vratiSveAktivneSimKartice(pretplatnici, organizacija, sim);
	}
	
}

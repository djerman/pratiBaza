package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.VozaciLekarskoDAO;
import pratiBaza.servis.VozaciLekarskoServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciLekarsko;

@Service("vozacLekarskoServis")
public class VozaciLekarskoServisImpl implements VozaciLekarskoServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozaciLekarskoDAO vozacLekarskoDAO;

	@Transactional
	@Override
	public void unesiVozacLekarsko(VozaciLekarsko lekarsko) {
		vozacLekarskoDAO.unesiVozacLekarsko(lekarsko);
	}

	@Transactional
	@Override
	public void izmeniVozacLekarsko(VozaciLekarsko lekarsko) {
		vozacLekarskoDAO.izmeniVozacLekarsko(lekarsko);
	}

	@Transactional
	@Override
	public void izbrisiVozacLekarsko(VozaciLekarsko lekarsko) {
		vozacLekarskoDAO.izbrisiVozacLekarsko(lekarsko);
	}

	@Transactional
	@Override
	public VozaciLekarsko nadjiVozacLekarskoPoId(int id) {
		return vozacLekarskoDAO.nadjiVozacLekarskoPoId(id);
	}

	@Transactional
	@Override
	public VozaciLekarsko nadjiVozacLekarskoPoKorisniku(Korisnici korisnik) {
		return vozacLekarskoDAO.nadjiVozacLekarskoPoKorisniku(korisnik);
	}

	@Transactional
	@Override
	public ArrayList<VozaciLekarsko> nadjiSveVozacLekarskePoKorisniku(Korisnici korisnik) {
		return vozacLekarskoDAO.nadjiSveVozacLekarskePoKorisniku(korisnik);
	}

	@Transactional
	@Override
	public ArrayList<VozaciLekarsko> nadjiSveVozacLekarske(Korisnici korisnik) {
		return vozacLekarskoDAO.nadjiSveVozacLekarske(korisnik);
	}

	@Transactional
	public VozaciLekarskoDAO getVozacLekarskoDAO() {
		return vozacLekarskoDAO;
	}

	@Transactional
	public void setVozacLekarskoDAO(VozaciLekarskoDAO vozacLekarskoDAO) {
		this.vozacLekarskoDAO = vozacLekarskoDAO;
	}
	
}

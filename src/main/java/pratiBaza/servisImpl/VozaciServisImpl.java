package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.VozaciDAO;
import pratiBaza.servis.VozaciServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozaci;

@Service("vozacServis")
public class VozaciServisImpl implements VozaciServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozaciDAO vozacDAO;
	
	@Override
	@Transactional
	public void unesiVozaca(Vozaci vozac) {
		vozacDAO.unesiVozaca(vozac);
	}

	@Override
	@Transactional
	public void izmeniVozaca(Vozaci vozac) {
		vozacDAO.izmeniVozaca(vozac);
	}

	@Override
	@Transactional
	public void izbrisiVozaca(Vozaci vozac) {
		vozacDAO.izbrisiVozaca(vozac);
	}

	@Override
	@Transactional
	public Vozaci nadjiVozacaPoId(int id) {
		return vozacDAO.nadjiVozacaPoId(id);
	}

	@Override
	@Transactional
	public Vozaci nadjiVozacaPoKorisniku(Korisnici korisnik) {
		return vozacDAO.nadjiVozacaPoKorisniku(korisnik);
	}

	@Override
	@Transactional
	public ArrayList<Vozaci> nadjiSveVozacePoKorisniku(Korisnici korisnik) {
		return vozacDAO.nadjiSveVozacePoKorisniku(korisnik);
	}

	@Override
	@Transactional
	public ArrayList<Vozaci> nadjiSveVozace(Korisnici korisnik) {
		return vozacDAO.nadjiSveVozace(korisnik);
	}

	@Transactional
	public VozaciDAO getVozacDAO() {
		return vozacDAO;
	}

	@Transactional
	public void setVozacDAO(VozaciDAO vozacDAO) {
		this.vozacDAO = vozacDAO;
	}

}

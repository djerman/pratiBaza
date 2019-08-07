package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.VozaciLicnaDAO;
import pratiBaza.servis.VozaciLicnaServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciLicna;

@Service("vozacLicnaServis")
public class VozaciLicnaServisImpl implements VozaciLicnaServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozaciLicnaDAO vozacLicnaDAO;

	@Transactional
	@Override
	public void unesiVozacLicna(VozaciLicna licna) {
		vozacLicnaDAO.unesiVozacLicna(licna);
	}

	@Transactional
	@Override
	public void izmeniVozacLicna(VozaciLicna licna) {
		vozacLicnaDAO.izmeniVozacLicna(licna);
	}

	@Transactional
	@Override
	public void izbrisiVozacLicna(VozaciLicna licna) {
		vozacLicnaDAO.izbrisiVozacLicna(licna);
	}

	@Transactional
	@Override
	public VozaciLicna nadjiVozacLicnaPoId(int id) {
		return vozacLicnaDAO.nadjiVozacLicnaPoId(id);
	}

	@Transactional
	@Override
	public VozaciLicna nadjiVozacLicnaPoKorisniku(Korisnici korisnik) {
		return vozacLicnaDAO.nadjiVozacLicnaPoKorisniku(korisnik);
	}

	@Transactional
	@Override
	public ArrayList<VozaciLicna> nadjiSveVozacLicnaPoKorisniku(Korisnici korisnik) {
		return vozacLicnaDAO.nadjiSveVozacLicnaPoKorisniku(korisnik);
	}

	@Transactional
	@Override
	public ArrayList<VozaciLicna> nadjiSveVozacLicna(Korisnici korisnik) {
		return vozacLicnaDAO.nadjiSveVozacLicna(korisnik);
	}

	@Transactional
	public VozaciLicnaDAO getVozacLicnaDAO() {
		return vozacLicnaDAO;
	}

	@Transactional
	public void setVozacLicnaDAO(VozaciLicnaDAO vozacLicnaDAO) {
		this.vozacLicnaDAO = vozacLicnaDAO;
	}
	
}

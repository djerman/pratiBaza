package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.VozaciLicenceDAO;
import pratiBaza.servis.VozaciLicenceServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozaci;
import pratiBaza.tabele.VozaciLicence;

@Service("vozacLicencaServis")
public class VozaciLicenceServisImpl implements VozaciLicenceServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozaciLicenceDAO vozacLicencaDAO;
	
	@Transactional
	@Override
	public void unesiVozacLicenca(VozaciLicence licenca) {
		vozacLicencaDAO.unesiVozacLicenca(licenca);
	}

	@Transactional
	@Override
	public void izmeniVozacLicenca(VozaciLicence licenca) {
		vozacLicencaDAO.izmeniVozacLicenca(licenca);
	}

	@Transactional
	@Override
	public void izbrisiVozacLicenca(VozaciLicence licenca) {
		vozacLicencaDAO.izbrisiVozacLicenca(licenca);
	}

	@Transactional
	@Override
	public VozaciLicence nadjiVozacLicencaPoId(int id) {
		return vozacLicencaDAO.nadjiVozacLicencaPoId(id);
	}

	@Transactional
	@Override
	public VozaciLicence nadjiVozacLicencaPoVozacu(Vozaci vozac) {
		return vozacLicencaDAO.nadjiVozacLicencaPoVozacu(vozac);
	}

	@Transactional
	@Override
	public ArrayList<VozaciLicence> nadjiSveVozacLicencaPoVozacu(Vozaci vozac) {
		return vozacLicencaDAO.nadjiSveVozacLicencaPoVozacu(vozac);
	}

	@Transactional
	@Override
	public ArrayList<VozaciLicence> nadjiSveVozacLicenca(Korisnici korisnik) {
		return vozacLicencaDAO.nadjiSveVozacLicenca(korisnik);
	}

	@Transactional
	public VozaciLicenceDAO getVozacLicencaDAO() {
		return vozacLicencaDAO;
	}

	@Transactional
	public void setVozacLicencaDAO(VozaciLicenceDAO vozacLicencaDAO) {
		this.vozacLicencaDAO = vozacLicencaDAO;
	}

}

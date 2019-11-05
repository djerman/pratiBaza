package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.VozaciDozvoleDAO;
import pratiBaza.servis.VozaciDozvoleServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciDozvole;

@Service("vozacDozvolaServis")
public class VozaciDozvoleServisImpl implements VozaciDozvoleServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozaciDozvoleDAO vozacDozvolaDAO;

	@Transactional
	@Override
	public void unesiVozacDozvola(VozaciDozvole dozvola) {
		vozacDozvolaDAO.unesiVozacDozvola(dozvola);
	}

	@Transactional
	@Override
	public void izmeniVozacDozvola(VozaciDozvole dozvola) {
		vozacDozvolaDAO.izmeniVozacDozvola(dozvola);
	}

	@Transactional
	@Override
	public void izbrisiVozacDozvola(VozaciDozvole dozvola) {
		vozacDozvolaDAO.izbrisiVozacDozvola(dozvola);
	}

	@Transactional
	@Override
	public VozaciDozvole nadjiVozacDozvolaPoId(int id) {
		return vozacDozvolaDAO.nadjiVozacDozvolaPoId(id);
	}

	@Transactional
	@Override
	public VozaciDozvole nadjiVozacDozvoluPoVozacu(Korisnici vozac) {
		return vozacDozvolaDAO.nadjiVozacDozvoluPoVozacu(vozac);
	}

	@Transactional
	@Override
	public ArrayList<VozaciDozvole> nadjiSveVozacDozvolePoVozacu(Korisnici vozac) {
		return vozacDozvolaDAO.nadjiSveVozacDozvolePoVozacu(vozac);
	}

	@Transactional
	@Override
	public ArrayList<VozaciDozvole> nadjiSveVozacDozvole(Korisnici korisnik) {
		return vozacDozvolaDAO.nadjiSveVozacDozvole(korisnik);
	}

	@Transactional
	public VozaciDozvoleDAO getVozacDozvolaDAO() {
		return vozacDozvolaDAO;
	}

	@Transactional
	public void setVozacDozvolaDAO(VozaciDozvoleDAO vozacDozvolaDAO) {
		this.vozacDozvolaDAO = vozacDozvolaDAO;
	}
	
}

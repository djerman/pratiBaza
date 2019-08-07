package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.VozaciPasosiDAO;
import pratiBaza.servis.VozaciPasosiServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciPasosi;

@Service("vozacPasosServis")
public class VozaciPasosiServisImpl implements VozaciPasosiServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozaciPasosiDAO vozacPasosDAO;
	
	@Transactional
	@Override
	public void unesiVozacPasos(VozaciPasosi pasos) {
		vozacPasosDAO.unesiVozacPasos(pasos);
	}

	@Transactional
	@Override
	public void izmeniVozacPasos(VozaciPasosi pasos) {
		vozacPasosDAO.izmeniVozacPasos(pasos);
	}

	@Transactional
	@Override
	public void izbrisiVozacPasos(VozaciPasosi pasos) {
		vozacPasosDAO.izbrisiVozacPasos(pasos);
	}

	@Transactional
	@Override
	public VozaciPasosi nadjiVozacPasosPoId(int id) {
		return vozacPasosDAO.nadjiVozacPasosPoId(id);
	}

	@Transactional
	@Override
	public VozaciPasosi nadjiVozacPasosPoKorisniku(Korisnici korisnik) {
		return vozacPasosDAO.nadjiVozacPasosPoKorisniku(korisnik);
	}

	@Transactional
	@Override
	public ArrayList<VozaciPasosi> nadjiSveVozacPasosPoKorisniku(Korisnici korisnik) {
		return vozacPasosDAO.nadjiSveVozacPasosPoKorisniku(korisnik);
	}

	@Transactional
	@Override
	public ArrayList<VozaciPasosi> nadjiSveVozacPasos(Korisnici korisnik) {
		return vozacPasosDAO.nadjiSveVozacPasos(korisnik);
	}

	@Transactional
	public VozaciPasosiDAO getVozacPasosDAO() {
		return vozacPasosDAO;
	}

	@Transactional
	public void setVozacPasosDAO(VozaciPasosiDAO vozacPasosDAO) {
		this.vozacPasosDAO = vozacPasosDAO;
	}

}

package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.VozilaNaloziDAO;
import pratiBaza.servis.VozilaNaloziServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.VozilaNalozi;

@Service("voziloNalogServis")
public class VozilaNaloziServisImpl implements VozilaNaloziServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozilaNaloziDAO voziloNalogDAO;
	
	@Transactional
	@Override
	public void unesiVoziloNalog(VozilaNalozi nalog) {
		voziloNalogDAO.unesiVoziloNalog(nalog);
	}

	@Transactional
	@Override
	public void izmeniVoziloNalog(VozilaNalozi nalog) {
		voziloNalogDAO.izmeniVoziloNalog(nalog);
	}

	@Transactional
	@Override
	public void izbrisiVoziloNalog(VozilaNalozi nalog) {
		voziloNalogDAO.izbrisiVoziloNalog(nalog);
	}

	@Transactional
	@Override
	public VozilaNalozi nadjiVoziloNalog(int id) {
		return voziloNalogDAO.nadjiVoziloNalog(id);
	}

	@Transactional
	@Override
	public VozilaNalozi nadjiVoziloNalogPoVozilu(Objekti objekat) {
		return voziloNalogDAO.nadjiVoziloNalogPoVozilu(objekat);
	}

	@Transactional
	@Override
	public ArrayList<VozilaNalozi> nadjiSveVozilaNalogePoObjektu(Objekti objekat) {
		return voziloNalogDAO.nadjiSveVozilaNalogePoObjektu(objekat);
	}

	@Transactional
	@Override
	public ArrayList<VozilaNalozi> nadjiSveVozilaNaloge(Korisnici korisnik) {
		return voziloNalogDAO.nadjiSveVozilaNaloge(korisnik);
	}

	@Transactional
	public VozilaNaloziDAO getVoziloNalogDAO() {
		return voziloNalogDAO;
	}

	@Transactional
	public void setVoziloNalogDAO(VozilaNaloziDAO voziloNalogDAO) {
		this.voziloNalogDAO = voziloNalogDAO;
	}

}

package pratiBaza.servisImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.VozilaNaloziDAO;
import pratiBaza.servis.VozilaNaloziServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Vozila;
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
	public VozilaNalozi nadjiVoziloNalogPoVozilu(Vozila vozilo) {
		return voziloNalogDAO.nadjiVoziloNalogPoVozilu(vozilo);
	}

	@Transactional
	@Override
	public ArrayList<VozilaNalozi> nadjiSveVozilaNalogePoVozilu(Vozila vozilo) {
		return voziloNalogDAO.nadjiSveVozilaNalogePoVozilu(vozilo);
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

	@Transactional
	@Override
	public ArrayList<VozilaNalozi> nadjiNalogeZaGrupuUPeriodu(ArrayList<Objekti> objekti, Timestamp pocetak, Timestamp kraj) {
		return voziloNalogDAO.nadjiNalogeZaGrupuUPeriodu(objekti, pocetak, kraj);
	}

}

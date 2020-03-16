package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.VozilaDAO;
import pratiBaza.servis.VozilaServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaSaobracajne;

@Service("voziloServis")
public class VozilaServisImpl implements VozilaServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozilaDAO voziloDAO;

	@Transactional
	public void unesiVozilo(Vozila voziloj) {
		voziloDAO.unesiVozilo(voziloj);
	}

	@Transactional
	public void azurirajVozilo(Vozila vozilo) {
		voziloDAO.azurirajVozilo(vozilo);
	}

	@Transactional
	public void izbrisiVozilo(Vozila vozilo) {
		voziloDAO.izbrisiVozilo(vozilo);
	}

	@Transactional
	public Vozila nadjiVoziloPoObjektu(Objekti objekti) {
		return voziloDAO.nadjiVoziloPoObjektu(objekti);
	}

	@Override
	@Transactional
	public Vozila nadjiVoziloPoId(int id) {
		return voziloDAO.nadjiVoziloPoId(id);
	}

	@Override
	@Transactional
	public ArrayList<Vozila> vratisvaVozila(Korisnici korisnik, boolean aktivan) {
		return voziloDAO.vratisvaVozila(korisnik, aktivan);
	}

	@Transactional
	public VozilaDAO getVoziloDAO() {
		return voziloDAO;
	}

	@Transactional
	public void setVoziloDAO(VozilaDAO voziloDAO) {
		this.voziloDAO = voziloDAO;
	}

	@Override
	@Transactional
	public Vozila vratiVoziloPoSaobracajnoj(VozilaSaobracajne saobracajna) {
		return voziloDAO.vratiVoziloPoSaobracajnoj(saobracajna);
	}

	@Override
	@Transactional
	public ArrayList<Vozila> nadjisvaVozilaPoPretplatniku(SistemPretplatnici pretplatnik) {
		return voziloDAO.nadjisvaVozilaPoPretplatniku(pretplatnik);
	}

	@Override
	@Transactional
	public ArrayList<Vozila> nadjisvaVozilaPoOrganizaciji(Organizacije organizacija) {
		return voziloDAO.nadjisvaVozilaPoOrganizaciji(organizacija);
	}

	@Override
	@Transactional
	public ArrayList<Vozila> nadjisvaVozilaPoObjektima(ArrayList<Objekti> objekti) {
		return voziloDAO.nadjisvaVozilaPoObjektima(objekti);
	}

	@Override
	@Transactional
	public ArrayList<Vozila> nadjiSvaVozilaBezSaobracajne(Korisnici korisnik, boolean aktivan) {
		return voziloDAO.nadjiSvaVozilaBezSaobracajne(korisnik, aktivan);
	}

	@Override
	@Transactional
	public ArrayList<Vozila> nadjiSvaVozilaBezSaobracajnePoPretplatniku(SistemPretplatnici pretplatnik,
			Organizacije organizacija) {
		return voziloDAO.nadjiSvaVozilaBezSaobracajnePoPretplatniku(pretplatnik, organizacija);
	}

	@Override
	@Transactional
	public Vozila nadjiVoziloPoRegistraciji(SistemPretplatnici pretplatnik, String registracija) {
		return voziloDAO.nadjiVoziloPoRegistraciji(pretplatnik, registracija);
	}
	
}

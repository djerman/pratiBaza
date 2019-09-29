package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.VozilaPrimoPredajeDAO;
import pratiBaza.servis.VozilaPrimoPredajeServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Vozila;
import pratiBaza.tabele.VozilaPrimoPredaje;

@Service("voziloPrimoPredajaServis")
public class VozilaPrimoPredajeServisImpl implements VozilaPrimoPredajeServis{

	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozilaPrimoPredajeDAO voziloPrimoPredajaDAO;
	
	@Transactional
	@Override
	public void unesiVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja) {
		voziloPrimoPredajaDAO.unesiVoziloPrimoPredaja(primoPredaja);
	}

	@Transactional
	@Override
	public void izmeniVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja) {
		voziloPrimoPredajaDAO.izmeniVoziloPrimoPredaja(primoPredaja);
	}

	@Transactional
	@Override
	public void izbrisiVoziloPrimoPredaja(VozilaPrimoPredaje primoPredaja) {
		voziloPrimoPredajaDAO.izbrisiVoziloPrimoPredaja(primoPredaja);
	}

	@Transactional
	@Override
	public VozilaPrimoPredaje nadjiVoziloPrimoPredajaPoId(int id) {
		return voziloPrimoPredajaDAO.nadjiVoziloPrimoPredajaPoId(id);
	}

	@Transactional
	@Override
	public VozilaPrimoPredaje nadjiVoziloPrimoPredajuPoVozilu(Vozila vozilo) {
		return voziloPrimoPredajaDAO.nadjiVoziloPrimoPredajuPoVozilu(vozilo);
	}

	@Transactional
	@Override
	public ArrayList<VozilaPrimoPredaje> nadjiSveVozilaPrimoPredajePoObjektu(Vozila vozilo) {
		return voziloPrimoPredajaDAO.nadjiSveVozilaPrimoPredajePoVozilu(vozilo);
	}

	@Transactional
	@Override
	public ArrayList<VozilaPrimoPredaje> nadjiSveVozilaPrimoPredaje(Korisnici korisnik) {
		return voziloPrimoPredajaDAO.nadjiSveVozilaPrimoPredaje(korisnik);
	}

	@Transactional
	public VozilaPrimoPredajeDAO getVoziloPrimoPredajaDAO() {
		return voziloPrimoPredajaDAO;
	}

	@Transactional
	public void setVoziloPrimoPredajaDAO(VozilaPrimoPredajeDAO voziloPrimoPredajaDAO) {
		this.voziloPrimoPredajaDAO = voziloPrimoPredajaDAO;
	}

}

package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.ObjekatZoneDAO;
import pratiBaza.servis.ObjekatZoneServis;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Zone;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.ObjekatZone;

@Service("zonaObjekatServis")
public class ObjekatZoneServisImpl implements ObjekatZoneServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	ObjekatZoneDAO zonaObjekatDAO;

	@Transactional
	public void unesiZonaObjekat(ObjekatZone zonaObjekat) {
		zonaObjekatDAO.unesiZonaObjekat(zonaObjekat);
	}

	@Transactional
	public void izmeniZonaObjekat(ObjekatZone zonaObjekat) {
		zonaObjekatDAO.izmeniZonaObjekat(zonaObjekat);
	}

	@Transactional
	public void izbrisiZonaObjekat(ObjekatZone zonaObjekat) {
		zonaObjekatDAO.izbrisiZonaObjekat(zonaObjekat);
	}

	@Transactional
	public ArrayList<ObjekatZone> nadjiZoneObjektePoObjektu(Objekti objekat) {
		return zonaObjekatDAO.nadjiZoneObjektePoObjektu(objekat);
	}

	@Transactional
	public ArrayList<ObjekatZone> nadjiZoneObjektePoZoni(Zone zona) {
		return zonaObjekatDAO.nadjiZoneObjektePoZoni(zona);
	}

	@Transactional
	public ObjekatZoneDAO getZonaObjekatDAO() {
		return zonaObjekatDAO;
	}

	@Transactional
	public void setZonaObjekatDAO(ObjekatZoneDAO zonaObjekatDAO) {
		this.zonaObjekatDAO = zonaObjekatDAO;
	}

	@Transactional
	public void izbrisiZoneObjektiPoObjektu(Objekti objekat) {
		zonaObjekatDAO.izbrisiZoneObjektiPoObjektu(objekat);
	}

	@Transactional
	public void izbrisiZoneObjektiPOZoni(Zone zona) {
		zonaObjekatDAO.izbrisiZoneObjektiPOZoni(zona);
	}

	@Transactional
	public ObjekatZone nadjiObjekatZoniPoId(int id) {
		return zonaObjekatDAO.nadjiObjekatZoniPoId(id);
	}

	@Transactional
	public ArrayList<ObjekatZone> vratiSveObjekatZone(Korisnici korisnik, boolean aktivan) {
		return zonaObjekatDAO.vratiSveObjekatZone(korisnik, aktivan);
	}

	@Override
	@Transactional
	public ArrayList<Zone> nadjiZonePoObjektu(Objekti objekat) {
		return zonaObjekatDAO.nadjiZonePoObjektu(objekat);
	}

	@Override
	@Transactional
	public ObjekatZone nadjiObjekatZonuPoZoniObjektu(Objekti objekat, Zone zona) {
		return zonaObjekatDAO.nadjiObjekatZonuPoZoniObjektu(objekat, zona);
	}
	
}

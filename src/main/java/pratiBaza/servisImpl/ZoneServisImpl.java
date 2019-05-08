package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.ZoneDAO;
import pratiBaza.servis.ZoneServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Zone;

@Service("zonaServis")
public class ZoneServisImpl implements ZoneServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	ZoneDAO zonaDAO;

	@Transactional
	public void unesiZonu(Zone zona) {
		zonaDAO.unesiZonu(zona);
	}

	@Transactional
	public void izmeniZonu(Zone zona) {
		zonaDAO.izmeniZonu(zona);
	}

	@Transactional
	public void izbrisiZonu(Zone zona) {
		zonaDAO.izbrisiZonu(zona);
	}

	@Transactional
	public ArrayList<Zone> nadjiSveZone(Korisnici korisnik) {
		return zonaDAO.nadjiSveZone(korisnik);
	}

	@Transactional
	public ArrayList<Zone> nadjiSveZonePoPretplatniku(SistemPretplatnici pretplatnik) {
		return zonaDAO.nadjiSveZonePoPretplatniku(pretplatnik);
	}

	@Transactional
	public ArrayList<Zone> nadjiSveZonePoOrganizaciji(Organizacije organizacija) {
		return zonaDAO.nadjiSveZonePoOrganizaciji(organizacija);
	}

	@Transactional
	public ZoneDAO getZonaDAO() {
		return zonaDAO;
	}

	@Transactional
	public void setZonaDAO(ZoneDAO zonaDAO) {
		this.zonaDAO = zonaDAO;
	}

	@Transactional
	public Zone nadjiZonuPoId(int id) {
		return zonaDAO.nadjiZonuPoId(id);
	}
	
}

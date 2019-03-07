package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.ZoneObjektiDAO;
import pratiBaza.servis.ZoneObjektiServis;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Zone;
import pratiBaza.tabele.ZoneObjekti;

@Service("zonaObjekatServis")
public class ZoneObjektiServisImpl implements ZoneObjektiServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	ZoneObjektiDAO zonaObjekatDAO;

	@Transactional
	public void unesiZonaObjekat(ZoneObjekti zonaObjekat) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void izmeniZonaObjekat(ZoneObjekti zonaObjekat) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void izbrisiZonaObjekat(ZoneObjekti zonaObjekat) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public ArrayList<ZoneObjekti> nadjiZoneObjektePoObjektu(Objekti objekat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public ArrayList<ZoneObjekti> nadjiZoneObjektePoZoni(Zone zona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public ZoneObjektiDAO getZonaObjekatDAO() {
		return zonaObjekatDAO;
	}

	@Transactional
	public void setZonaObjekatDAO(ZoneObjektiDAO zonaObjekatDAO) {
		this.zonaObjekatDAO = zonaObjekatDAO;
	}
	
}

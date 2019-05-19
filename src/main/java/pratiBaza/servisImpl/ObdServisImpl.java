package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.ObdDAO;
import pratiBaza.servis.ObdServis;
import pratiBaza.tabele.Obd;
import pratiBaza.tabele.Objekti;

@Service("obdServis")
public class ObdServisImpl implements ObdServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	ObdDAO obdDAO;

	@Transactional
	public void unesiObd(Obd obd) {
		obdDAO.unesiObd(obd);
	}

	@Transactional
	public void azurirajObd(Obd obd) {
		obdDAO.azurirajObd(obd);
	}

	@Transactional
	public void izbrisiObd(Obd obd) {
		obdDAO.izbrisiObd(obd);
	}

	@Transactional
	public ObdDAO getObdDAO() {
		return obdDAO;
	}

	@Transactional
	public void setObdDAO(ObdDAO obdDAO) {
		this.obdDAO = obdDAO;
	}

	@Transactional
	public Obd nadjiObdPoslednji(Objekti objekat) {
		return obdDAO.nadjiObdPoslednji(objekat);
	}
	
}

package pratiBaza.servisImpl;

import java.sql.Timestamp;
import java.util.ArrayList;

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

	@Override
	@Transactional
	public ArrayList<Obd> nadjiObdPoObjektuOdDo(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		return obdDAO.nadjiObdPoObjektuOdDo(objekat, datumVremeOd, datumVremeDo);
	}

	@Override
	@Transactional
	public ArrayList<Obd> nadjiObdPoObjektuOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		return obdDAO.nadjiObdPoObjektuOdDoPrvoPoslednje(objekat, datumVremeOd, datumVremeDo);
	}

	@Override
	@Transactional
	public ArrayList<Obd> nadjiObdPoslednjaStajanja(Objekti objekat, Timestamp datumVremeOd) {
		return obdDAO.nadjiObdPoslednjaStajanja(objekat, datumVremeOd);
	}
	
}

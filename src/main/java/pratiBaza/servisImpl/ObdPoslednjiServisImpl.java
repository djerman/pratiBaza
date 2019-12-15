package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.ObdPoslednjiDAO;
import pratiBaza.servis.ObdPoslednjiServis;
import pratiBaza.tabele.ObdPoslednji;
import pratiBaza.tabele.Objekti;

@Service("obdPoslednjiServis")
public class ObdPoslednjiServisImpl implements ObdPoslednjiServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	ObdPoslednjiDAO obdPoslednjiDAO;

	@Transactional
	public ObdPoslednjiDAO getObdPoslednjiDAO() {
		return obdPoslednjiDAO;
	}

	@Transactional
	public void setObdPoslednjiDAO(ObdPoslednjiDAO obdPoslednjiDAO) {
		this.obdPoslednjiDAO = obdPoslednjiDAO;
	}

	@Transactional
	@Override
	public void unesiObd(ObdPoslednji obdPoslednji) {
		obdPoslednjiDAO.unesiObd(obdPoslednji);
	}

	@Transactional
	@Override
	public void azurirajObd(ObdPoslednji obdPoslednji) {
		obdPoslednjiDAO.azurirajObd(obdPoslednji);
	}

	@Transactional
	@Override
	public void izbrisiObd(ObdPoslednji obdPoslednje) {
		obdPoslednjiDAO.izbrisiObd(obdPoslednje);
	}

	@Transactional
	@Override
	public ArrayList<ObdPoslednji> vratiListuObdPoslednjih(ArrayList<Objekti> objekti) {
		return obdPoslednjiDAO.vratiListuObdPoslednjih(objekti);
	}

	@Transactional
	@Override
	public ObdPoslednji nadjiObdPoslednjiPoObjektu(Objekti objekat) {
		return obdPoslednjiDAO.nadjiObdPoslednjiPoObjektu(objekat);
	}
	
}

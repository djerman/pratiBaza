package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.SistemAlarmiDAO;
import pratiBaza.servis.SistemAlarmiServis;
import pratiBaza.tabele.SistemAlarmi;

@Service("sistemAlarmServis")
public class SistemAlarmiServisImpl implements SistemAlarmiServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	SistemAlarmiDAO sistemAlarmDAO;

	@Transactional
	public void unesiAlarme(SistemAlarmi alarm) {
		sistemAlarmDAO.unesiAlarme(alarm);
	}

	@Transactional
	public void azurirajAlarme(SistemAlarmi alarm) {
		sistemAlarmDAO.azurirajAlarme(alarm);
	}

	@Transactional
	public void izbrisiAlarme(SistemAlarmi alarm) {
		sistemAlarmDAO.izbrisiAlarme(alarm);
	}

	@Transactional
	public SistemAlarmiDAO getSistemAlarmDAO() {
		return sistemAlarmDAO;
	}

	@Transactional
	public void setSistemAlarmDAO(SistemAlarmiDAO sistemAlarmDAO) {
		this.sistemAlarmDAO = sistemAlarmDAO;
	}

	@Transactional
	public ArrayList<SistemAlarmi> vratiSveAlarme() {
		return sistemAlarmDAO.vratiSveAlarme();
	}

	@Transactional
	public SistemAlarmi nadjiAlaramPoId(int id) {
		return sistemAlarmDAO.nadjiAlaramPoId(id);
	}
	
}

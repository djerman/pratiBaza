package pratiBaza.servisImpl;

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
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void azurirajAlarme(SistemAlarmi alarm) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void izbrisiAlarme(SistemAlarmi alarm) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public SistemAlarmiDAO getSistemAlarmDAO() {
		return sistemAlarmDAO;
	}

	@Transactional
	public void setSistemAlarmDAO(SistemAlarmiDAO sistemAlarmDAO) {
		this.sistemAlarmDAO = sistemAlarmDAO;
	}
	
}

package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.ObdDAO;
import pratiBaza.servis.ObdServis;
import pratiBaza.tabele.Obd;

@Service("obdServis")
public class ObdServisImpl implements ObdServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	ObdDAO obdDAO;

	@Transactional
	public void unesiObd(Obd obd) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void azurirajObd(Obd obd) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void izbrisiObd(Obd obd) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public ObdDAO getObdDAO() {
		return obdDAO;
	}

	@Transactional
	public void setObdDAO(ObdDAO obdDAO) {
		this.obdDAO = obdDAO;
	}
	
}

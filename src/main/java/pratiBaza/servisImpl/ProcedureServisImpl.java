package pratiBaza.servisImpl;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.ProcedureDAO;
import pratiBaza.pomocne.RadnoVremePutGPS;
import pratiBaza.servis.ProcedureServis;

@Service("proceduraService")
public class ProcedureServisImpl implements ProcedureServis{
	//mora da se doda i get i set metode za objekat DAO jer ne radi spring bez toga
	@Autowired
	ProcedureDAO proceduraDAO;
	
	@Override
	@Transactional
	public List<RadnoVremePutGPS> radnoVremePutGPS(int idObjekta, Timestamp pocetak, Timestamp kraj, int satiOd, int satiDo) {
		return proceduraDAO.radnoVremePutGPS(idObjekta, pocetak, kraj, satiOd, satiDo);
	}

	@Transactional
	public ProcedureDAO getProceduraDAO() {
		return proceduraDAO;
	}

	@Transactional
	public void setProceduraDAO(ProcedureDAO proceduraDAO) {
		this.proceduraDAO = proceduraDAO;
	}

	
}

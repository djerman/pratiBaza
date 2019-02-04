package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.ObjektiDAO;
import pratiBaza.servis.ObjektiServis;
import pratiBaza.tabele.Objekti;

@Service("objekatServis")
public class ObjektiServisImpl implements ObjektiServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	ObjektiDAO objekatDAO;

	@Transactional
	public void unesiObjekte(Objekti objekat) {
		objekatDAO.unesiObjekte(objekat);
	}

	@Transactional
	public void azurirajObjekte(Objekti objekat) {
		objekatDAO.azurirajObjekte(objekat);
	}

	@Transactional
	public void izbrisiObjekte(Objekti objekat) {
		objekatDAO.izbrisiObjekte(objekat);
	}

	@Transactional
	public ObjektiDAO getObjekatDAO() {
		return objekatDAO;
	}

	@Transactional
	public void setObjekatDAO(ObjektiDAO objekatDAO) {
		this.objekatDAO = objekatDAO;
	}
	

}

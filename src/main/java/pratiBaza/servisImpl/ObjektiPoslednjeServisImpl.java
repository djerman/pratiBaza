package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.ObjektiPoslednjeDAO;
import pratiBaza.servis.ObjektiPoslednjeServis;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.ObjektiPoslednje;

@Service("objekatPoslednjeServis")
public class ObjektiPoslednjeServisImpl  implements ObjektiPoslednjeServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	ObjektiPoslednjeDAO objekatPoslednjeDAO;

	@Override
	@Transactional
	public void unesiObjekatPoslednje(ObjektiPoslednje objekatPoslednje) {
		objekatPoslednjeDAO.unesiObjekatPoslednje(objekatPoslednje);
	}

	@Override
	@Transactional
	public void azurirajObjektiPoslednje(ObjektiPoslednje objekatPoslednje) {
		objekatPoslednjeDAO.azurirajObjektiPoslednje(objekatPoslednje);
	}

	@Override
	@Transactional
	public void izbrisiObjekatPoslednje(ObjektiPoslednje objekatPoslednje) {
		objekatPoslednjeDAO.izbrisiObjekatPoslednje(objekatPoslednje);
	}

	@Override
	@Transactional
	public ObjektiPoslednje nadjiObjekatPoslednjePoObjektu(Objekti objekat) {
		return objekatPoslednjeDAO.nadjiObjekatPoslednjePoObjektu(objekat);
	}

	@Transactional
	public ObjektiPoslednjeDAO getObjekatPoslednjeDAO() {
		return objekatPoslednjeDAO;
	}

	@Transactional
	public void setObjekatPoslednjeDAO(ObjektiPoslednjeDAO objekatPoslednjeDAO) {
		this.objekatPoslednjeDAO = objekatPoslednjeDAO;
	}

	@Override
	@Transactional
	public ObjektiPoslednje nadjiObjektiPoslednjePoId(int id) {
		return objekatPoslednjeDAO.nadjiObjektiPoslednjePoId(id);
	}
	
}

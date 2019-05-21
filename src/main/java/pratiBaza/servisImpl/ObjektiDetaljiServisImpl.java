package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.ObjektiDetaljiDAO;
import pratiBaza.servis.ObjektiDetaljiServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.ObjektiDetalji;

@Service("objekatDetaljServis")
public class ObjektiDetaljiServisImpl implements ObjektiDetaljiServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	ObjektiDetaljiDAO objekatDetaljDAO;

	@Transactional
	public void unesiObjektiDetalji(ObjektiDetalji objekatDetalj) {
		objekatDetaljDAO.unesiObjektiDetalji(objekatDetalj);
	}

	@Transactional
	public void azurirajObjektiDetalji(ObjektiDetalji objekatDetalj) {
		objekatDetaljDAO.azurirajObjektiDetalji(objekatDetalj);
	}

	@Transactional
	public void izbrisiObjektiDetalji(ObjektiDetalji objekatDetalj) {
		objekatDetaljDAO.izbrisiObjektiDetalji(objekatDetalj);
	}

	@Transactional
	public ObjektiDetaljiDAO getObjekatDetaljDAO() {
		return objekatDetaljDAO;
	}

	@Transactional
	public void setObjekatDetaljDAO(ObjektiDetaljiDAO objekatDetaljDAO) {
		this.objekatDetaljDAO = objekatDetaljDAO;
	}

	@Transactional
	public ObjektiDetalji nadjiObjekatDetaljePoObjektu(Objekti objekti) {
		return objekatDetaljDAO.nadjiObjekatDetaljePoObjektu(objekti);
	}

	@Override
	@Transactional
	public ObjektiDetalji nadjiObjektiDetaljiPoId(int id) {
		return objekatDetaljDAO.nadjiObjektiDetaljiPoId(id);
	}

	@Override
	@Transactional
	public ArrayList<ObjektiDetalji> vratisveObjekatDetalje(Korisnici korisnik, boolean aktivan) {
		return objekatDetaljDAO.vratisveObjekatDetalje(korisnik, aktivan);
	}
	
}

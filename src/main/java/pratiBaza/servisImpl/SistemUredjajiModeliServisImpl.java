package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.SistemUredjajiModeliDAO;
import pratiBaza.servis.SistemUredjajiModeliServis;
import pratiBaza.tabele.SistemUredjajiModeli;

@Service("sistemUredjajModelServis")
public class SistemUredjajiModeliServisImpl implements SistemUredjajiModeliServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	SistemUredjajiModeliDAO sistemUredjajModelDAO;

	@Transactional
	public void unesiUredjajModel(SistemUredjajiModeli model) {
		sistemUredjajModelDAO.unesiUredjajModel(model);
	}

	@Transactional
	public void izmeniUredjajModel(SistemUredjajiModeli model) {
		sistemUredjajModelDAO.izmeniUredjajModel(model);
	}

	@Transactional
	public void izbrisiUredjajModel(SistemUredjajiModeli model) {
		sistemUredjajModelDAO.izbrisiUredjajModel(model);
	}

	@Transactional
	public ArrayList<SistemUredjajiModeli> nadjiSveUredjajModele() {
		return sistemUredjajModelDAO.nadjiSveUredjajModele();
	}

	@Transactional
	public SistemUredjajiModeliDAO getSistemUredjajModelDAO() {
		return sistemUredjajModelDAO;
	}

	@Transactional
	public void setSistemUredjajModelDAO(SistemUredjajiModeliDAO sistemUredjajModelDAO) {
		this.sistemUredjajModelDAO = sistemUredjajModelDAO;
	}
	
}

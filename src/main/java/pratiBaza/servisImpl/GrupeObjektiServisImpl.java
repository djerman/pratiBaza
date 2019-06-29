package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.GrupeObjektiDAO;
import pratiBaza.servis.GrupeObjektiServis;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.GrupeObjekti;
import pratiBaza.tabele.Objekti;

@Service("grupaObjekatServis")
public class GrupeObjektiServisImpl implements GrupeObjektiServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	GrupeObjektiDAO grupaObjekatDAO;

	@Transactional
	public void unesiGrupaObjekat(GrupeObjekti grupaObjekat) {
		grupaObjekatDAO.unesiGrupaObjekat(grupaObjekat);
	}

	@Transactional
	public void azurirajGrupaObjekat(GrupeObjekti grupaObjekat) {
		grupaObjekatDAO.azurirajGrupaObjekat(grupaObjekat);
	}

	@Transactional
	public void izbrisiGrupaObjekat(GrupeObjekti grupaObjekat) {
		grupaObjekatDAO.izbrisiGrupaObjekat(grupaObjekat);
	}

	@Transactional
	public GrupeObjektiDAO getGrupaObjekatDAO() {
		return grupaObjekatDAO;
	}

	@Transactional
	public void setGrupaObjekatDAO(GrupeObjektiDAO grupaObjekatDAO) {
		this.grupaObjekatDAO = grupaObjekatDAO;
	}

	@Transactional
	public GrupeObjekti nadjiGrupaObjekatPoId(int id) {
		return grupaObjekatDAO.nadjiGrupaObjekatPoId(id);
	}

	@Transactional
	public void izbrisiSveGrupaObjekti(Grupe grupa) {
		grupaObjekatDAO.izbrisiSveGrupaObjekti(grupa);
	}

	@Transactional
	public ArrayList<GrupeObjekti> nadjiSveGrupaObjektePoGrupi(Grupe grupa) {
		return grupaObjekatDAO.nadjiSveGrupaObjektePoGrupi(grupa);
	}

	@Transactional
	public void izbrisiSveGrupeObjekatPoObjektu(Objekti objekat) {
		grupaObjekatDAO.izbrisiSveGrupeObjekatPoObjektu(objekat);
	}

	@Transactional
	public ArrayList<GrupeObjekti> nadjiSveGrupaObjektePoObjektu(Objekti objekat) {
		return grupaObjekatDAO.nadjiSveGrupaObjektePoObjektu(objekat);
	}

	@Override
	@Transactional
	public ArrayList<GrupeObjekti> nadjiSveGraupeObjektePoGrupama(ArrayList<Grupe> grupe) {
		return grupaObjekatDAO.nadjiSveGrupeObjektePoGrupama(grupe);
	}

	@Override
	@Transactional
	public ArrayList<Objekti> nadjiSveObjektePoGrupi(Grupe grupa) {
		return grupaObjekatDAO.nadjiSveObjektePoGrupi(grupa);
	}

	@Override
	@Transactional
	public ArrayList<Objekti> nadjiSveObjektePoGrupama(ArrayList<Grupe> grupe) {
		return grupaObjekatDAO.nadjiSveObjektePoGrupama(grupe);
	}
	

}

package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.ObjektiDAO;
import pratiBaza.servis.ObjektiServis;
import pratiBaza.tabele.Grupe;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

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

	@Transactional
	public ArrayList<Objekti> vratiSveObjekte(Korisnici korisnik, boolean aktivan) {
		return objekatDAO.vratiSveObjekte(korisnik, aktivan);
	}

	@Transactional
	public ArrayList<Objekti> vratiObjektePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean aktivan) {
		return objekatDAO.vratiObjektePoPretplatniku(pretplatnik, organizacija, aktivan);
	}

	@Transactional
	public ArrayList<Objekti> vratiSvaVozila(Korisnici korisnik) {
		return objekatDAO.vratiSvaVozila(korisnik);
	}

	@Transactional
	public ArrayList<Objekti> vratiObjektePoGrupi(Grupe grupa) {
		return objekatDAO.vratiObjektePoGrupi(grupa);
	}

	@Transactional
	public Objekti nadjiObjekatPoId(int id) {
		return objekatDAO.nadjiObjekatPoId(id);
	}

	@Transactional
	public ArrayList<Objekti> vratiSveObjekte(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		return objekatDAO.vratiSveObjekte(pretplatnik, organizacija);
	}

	@Override
	@Transactional
	public Objekti nadjiObjekatPoUredjaju(Uredjaji uredjaj) {
		return objekatDAO.nadjiObjekatPoUredjaju(uredjaj);
	}
	

}

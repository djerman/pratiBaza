package pratiBaza.servisImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.SistemSesijeDAO;
import pratiBaza.servis.SistemSesijeServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.SistemSesije;

@Service("sistemSesijaServis")
public class SistemSesijeServisImpl implements SistemSesijeServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	SistemSesijeDAO sistemSesijaDAO;

	@Transactional
	public void unesiSesiju(SistemSesije sesija) {
		sistemSesijaDAO.unesiSesiju(sesija);
	}

	@Transactional
	public void izmeniSesiju(SistemSesije sesija) {
		sistemSesijaDAO.izmeniSesiju(sesija);
	}

	@Transactional
	public void izbrisiSesiju(SistemSesije sesija) {
		sistemSesijaDAO.izbrisiSesiju(sesija);
	}

	@Transactional
	public ArrayList<SistemSesije> nadjiSveSesije() {
		return sistemSesijaDAO.nadjiSveSesije();
	}

	@Transactional
	public ArrayList<SistemSesije> nadjiSveSesijeKorisnika(Korisnici korisnik) {
		return sistemSesijaDAO.nadjiSveSesijeKorisnika(korisnik);
	}

	@Transactional
	public ArrayList<SistemSesije> nadjiSveSesijeKorisnikaPoVremenu(Korisnici korisnik, Timestamp datumVremeOd,
			Timestamp datumVremeDo) {
		return sistemSesijaDAO.nadjiSveSesijeKorisnikaPoVremenu(korisnik, datumVremeOd, datumVremeDo);
	}

	@Transactional
	public ArrayList<SistemSesije> nadjiSveSesijePretplatnika(SistemPretplatnici pretplatnik) {
		return sistemSesijaDAO.nadjiSveSesijePretplatnika(pretplatnik);
	}

	@Transactional
	public ArrayList<SistemSesije> nadjiSveSesijePretplatnikaPoVremenu(SistemPretplatnici pratplatnika,
			Timestamp datumVremeOd, Timestamp datumVremDo) {
		return sistemSesijaDAO.nadjiSveSesijePretplatnikaPoVremenu(pratplatnika, datumVremeOd, datumVremDo);
	}

	@Transactional
	public SistemSesijeDAO getSistemSesijaDAO() {
		return sistemSesijaDAO;
	}

	@Transactional
	public void setSistemSesijaDAO(SistemSesijeDAO sistemSesijaDAO) {
		this.sistemSesijaDAO = sistemSesijaDAO;
	}
	
}

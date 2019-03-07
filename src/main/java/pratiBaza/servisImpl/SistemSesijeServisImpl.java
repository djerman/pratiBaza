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
	SistemSesijeDAO sesijaDAO;

	@Transactional
	public void unesiSesiju(SistemSesije sesija) {
		sesijaDAO.unesiSesiju(sesija);
	}

	@Transactional
	public void izmeniSesiju(SistemSesije sesija) {
		sesijaDAO.izmeniSesiju(sesija);
	}

	@Transactional
	public void izbrisiSesiju(SistemSesije sesija) {
		sesijaDAO.izbrisiSesiju(sesija);
	}

	@Transactional
	public ArrayList<SistemSesije> nadjiSveSesije() {
		return sesijaDAO.nadjiSveSesije();
	}

	@Transactional
	public ArrayList<SistemSesije> nadjiSveSesijeKorisnika(Korisnici korisnik) {
		return sesijaDAO.nadjiSveSesijeKorisnika(korisnik);
	}

	@Transactional
	public ArrayList<SistemSesije> nadjiSveSesijeKorisnikaPoVremenu(Korisnici korisnik, Timestamp datumVremeOd,
			Timestamp datumVremeDo) {
		return sesijaDAO.nadjiSveSesijeKorisnikaPoVremenu(korisnik, datumVremeOd, datumVremeDo);
	}

	@Transactional
	public ArrayList<SistemSesije> nadjiSveSesijePretplatnika(SistemPretplatnici pretplatnik) {
		return sesijaDAO.nadjiSveSesijePretplatnika(pretplatnik);
	}

	@Transactional
	public ArrayList<SistemSesije> nadjiSveSesijePretplatnikaPoVremenu(SistemPretplatnici pratplatnika,
			Timestamp datumVremeOd, Timestamp datumVremDo) {
		return sesijaDAO.nadjiSveSesijePretplatnikaPoVremenu(pratplatnika, datumVremeOd, datumVremDo);
	}

	@Transactional
	public SistemSesijeDAO getSesijaDAO() {
		return sesijaDAO;
	}

	@Transactional
	public void setSesijaDAO(SistemSesijeDAO sesijaDAO) {
		this.sesijaDAO = sesijaDAO;
	}
	
}

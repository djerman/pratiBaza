package pratiBaza.servisImpl;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.TroskoviDAO;
import pratiBaza.servis.TroskoviServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Troskovi;

@Service("trosakServis")
public class TroskoviServisImpl implements TroskoviServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	TroskoviDAO trosakDAO;

	@Transactional
	public TroskoviDAO getTrosakDAO() {
		return trosakDAO;
	}

	@Transactional
	public void setTrosakDAO(TroskoviDAO trosakDAO) {
		this.trosakDAO = trosakDAO;
	}

	@Override
	@Transactional
	public void unesiTrosak(Troskovi trosak) {
		trosakDAO.unesiTrosak(trosak);
	}

	@Override
	@Transactional
	public void izmeniTrosak(Troskovi trosak) {
		trosakDAO.izmeniTrosak(trosak);
	}

	@Override
	@Transactional
	public void izbrisiTrosak(Troskovi trosak) {
		trosakDAO.izbrisiTrosak(trosak);
	}

	@Override
	@Transactional
	public Troskovi nadjiTrosakPoId(int id) {
		return trosakDAO.nadjiTrosakPoId(id);
	}

	@Override
	@Transactional
	public ArrayList<Troskovi> nadjiSveTroskovePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan) {
		return trosakDAO.nadjiSveTroskovePoPretplatniku(pretplatnik, organizacija, izbrisan);
	}

	@Override
	@Transactional
	public ArrayList<Troskovi> nadjiSveTroskove(Korisnici korisnik) {
		return trosakDAO.nadjiSveTroskove(korisnik);
	}

	@Override
	@Transactional
	public ArrayList<Troskovi> nadjiSvaOdrzavanjaPoPretplatniku(SistemPretplatnici pretplatnik,
			Organizacije organizacija, boolean izbrisan) {
		return trosakDAO.nadjiSvaOdrzavanjaPoPretplatniku(pretplatnik, organizacija, izbrisan);
	}

	@Override
	@Transactional
	public ArrayList<Troskovi> nadjiSvaOdrzavanja(Korisnici korisnik) {
		return trosakDAO.nadjiSvaOdrzavanja(korisnik);
	}

	@Override
	@Transactional
	public ArrayList<Troskovi> nadjiSvuPotrosnjuPoPretplatniku(SistemPretplatnici pretplatnik,
			Organizacije organizacija, boolean izbrisan) {
		return trosakDAO.nadjiSvuPotrosnjuPoPretplatniku(pretplatnik, organizacija, izbrisan);
	}

	@Override
	@Transactional
	public ArrayList<Troskovi> nadjiSvuPotrosnju(Korisnici korisnik) {
		return trosakDAO.nadjiSvuPotrosnju(korisnik);
	}

	@Override
	@Transactional
	public Troskovi nadjiPoslednjiTrosakDo(Timestamp datumVreme, int tipTroska) {
		return trosakDAO.nadjiPoslednjiTrosakDo(datumVreme, tipTroska);
	}

	@Override
	@Transactional
	public ArrayList<Troskovi> nadjiSveTroskoveOd(Timestamp datumVremeOd, SistemPretplatnici pretplatnik, Organizacije organizacija) {
		return trosakDAO.nadjiSveTroskoveOd(datumVremeOd, pretplatnik, organizacija);
	}

	@Override
	@Transactional
	public ArrayList<Troskovi> nadjiSveTroskoveUkupno(ArrayList<Objekti> vozila, Timestamp datumVremeOd, Timestamp datumVremeDo, Integer tipTroska) {
		return trosakDAO.nadjiSveTroskoveUkupno(vozila, datumVremeOd, datumVremeDo, tipTroska);
	}
	
}

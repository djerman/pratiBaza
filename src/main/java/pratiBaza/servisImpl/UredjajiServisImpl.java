package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.UredjajiDAO;
import pratiBaza.servis.UredjajiServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.Uredjaji;

@Service("uredjajServis")
public class UredjajiServisImpl implements UredjajiServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	UredjajiDAO uredjajDAO;

	@Transactional
	public void unesiUredjaj(Uredjaji uredjaj) {
		uredjajDAO.unesiUredjaj(uredjaj);
	}

	@Transactional
	public void izmeniUredjaj(Uredjaji uredjaj) {
		uredjajDAO.izmeniUredjaj(uredjaj);
	}

	@Transactional
	public void izbrisiUredjaj(Uredjaji uredjaj) {
		uredjajDAO.izbrisiUredjaj(uredjaj);
	}

	@Transactional
	public ArrayList<Uredjaji> nadjiSveUredjaje(Korisnici korisnik, boolean aktivan) {
		return uredjajDAO.nadjiSveUredjaje(korisnik, aktivan);
	}

	@Transactional
	public ArrayList<Uredjaji> nadjiSveAktivneSlobodneUredjajePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, Uredjaji uredjaj) {
		return uredjajDAO.nadjiSveAktivneSlobodneUredjajePoPretplatniku(pretplatnik, organizacija, uredjaj);
	}

	@Transactional
	public UredjajiDAO getUredjajDAO() {
		return uredjajDAO;
	}

	@Transactional
	public void setUredjajDAO(UredjajiDAO uredjajDAO) {
		this.uredjajDAO = uredjajDAO;
	}

	@Transactional
	public ArrayList<Uredjaji> nadjiSveAktivneUredjaje(Korisnici korisnik, Uredjaji uredjaj) {
		return uredjajDAO.nadjiSveAktivneUredjaje(korisnik, uredjaj);
	}

	@Transactional
	public Uredjaji nadjiUredjajPoId(int id) {
		return uredjajDAO.nadjiUredjajPoId(id);
	}

	@Override
	@Transactional
	public Uredjaji nadjiUredjajPoKodu(String kod) {
		return uredjajDAO.nadjiUredjajPoKodu(kod);
	}
	
}

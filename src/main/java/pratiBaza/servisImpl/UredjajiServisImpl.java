package pratiBaza.servisImpl;

import java.util.List;
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

	@Override
	@Transactional
	public void unesiUredjaj(Uredjaji uredjaj) {
		uredjajDAO.unesiUredjaj(uredjaj);
	}

	@Override
	@Transactional
	public void izmeniUredjaj(Uredjaji uredjaj) {
		uredjajDAO.izmeniUredjaj(uredjaj);
	}

	@Override
	@Transactional
	public void izbrisiUredjaj(Uredjaji uredjaj) {
		uredjajDAO.izbrisiUredjaj(uredjaj);
	}

	@Override
	@Transactional
	public List<Uredjaji> nadjiSveUredjaje(Korisnici korisnik, boolean aktivan) {
		return uredjajDAO.nadjiSveUredjaje(korisnik, aktivan);
	}

	@Override
	@Transactional
	public List<Uredjaji> nadjiSveAktivneSlobodneUredjajePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija) {
		return uredjajDAO.nadjiSveAktivneSlobodneUredjajePoPretplatniku(pretplatnik, organizacija);
	}

	@Transactional
	public UredjajiDAO getUredjajDAO() {
		return uredjajDAO;
	}

	@Transactional
	public void setUredjajDAO(UredjajiDAO uredjajDAO) {
		this.uredjajDAO = uredjajDAO;
	}

	@Override
	@Transactional
	public List<Uredjaji> nadjiSveAktivneUredjaje(Korisnici korisnik, Uredjaji uredjaj) {
		return uredjajDAO.nadjiSveAktivneUredjaje(korisnik, uredjaj);
	}

	@Override
	@Transactional
	public Uredjaji nadjiUredjajPoId(int id) {
		return uredjajDAO.nadjiUredjajPoId(id);
	}

	@Override
	@Transactional
	public Uredjaji nadjiUredjajPoKodu(String kod) {
		return uredjajDAO.nadjiUredjajPoKodu(kod);
	}

	@Transactional
	@Override
	public List<Uredjaji> nadjiSveAktivneSlobodneUredjaje(Korisnici korisnik, SistemPretplatnici pretplatnik,Organizacije organizacija) {
		return uredjajDAO.nadjiSveAktivneSlobodneUredjaje(korisnik, pretplatnik, organizacija);
	}
	
}

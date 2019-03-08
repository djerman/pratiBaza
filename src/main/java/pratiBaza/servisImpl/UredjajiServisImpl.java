package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.UredjajiDAO;
import pratiBaza.servis.UredjajiServis;
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
	public ArrayList<Uredjaji> nadjiSveUredjaje() {
		return uredjajDAO.nadjiSveUredjaje();
	}

	@Transactional
	public ArrayList<Uredjaji> nadjiSveUredjajePoPretplatniku(SistemPretplatnici pretplatnik) {
		return uredjajDAO.nadjiSveUredjajePoPretplatniku(pretplatnik);
	}

	@Transactional
	public UredjajiDAO getUredjajDAO() {
		return uredjajDAO;
	}

	@Transactional
	public void setUredjajDAO(UredjajiDAO uredjajDAO) {
		this.uredjajDAO = uredjajDAO;
	}
	
}

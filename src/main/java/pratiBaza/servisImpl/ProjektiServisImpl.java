package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.ProjektiDAO;
import pratiBaza.servis.ProjektiServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Projekti;
import pratiBaza.tabele.SistemPretplatnici;

@Service("projektServis")
public class ProjektiServisImpl implements ProjektiServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	ProjektiDAO projektDAO;
	
	@Transactional
	public ProjektiDAO getProjektDAO() {
		return projektDAO;
	}

	@Transactional
	public void setProjektDAO(ProjektiDAO projektDAO) {
		this.projektDAO = projektDAO;
	}

	@Override
	@Transactional
	public void unesiProjekat(Projekti projekat) {
		projektDAO.unesiProjekat(projekat);
	}

	@Override
	@Transactional
	public void izmeniProjekat(Projekti projekat) {
		projektDAO.izmeniProjekat(projekat);
	}

	@Override
	@Transactional
	public void izbrisiProjekat(Projekti projekat) {
		projektDAO.izbrisiProjekat(projekat);
	}

	@Override
	@Transactional
	public Projekti nadjiProjekatPoId(int id) {
		return projektDAO.nadjiProjekatPoId(id);
	}

	@Override
	@Transactional
	public ArrayList<Projekti> nadjiSveProjekte(Korisnici korisnik) {
		return projektDAO.nadjiSveProjekte(korisnik);
	}

	@Override
	@Transactional
	public ArrayList<Projekti> nadjiSveProjektePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan) {
		return projektDAO.nadjiSveProjektePoPretplatniku(pretplatnik, organizacija, izbrisan);
	}

}

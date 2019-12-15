package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.SifreDAO;
import pratiBaza.servis.SifreServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Sifre;
import pratiBaza.tabele.SistemPretplatnici;

@Service("sifraServis")
public class SifreServisImpl implements SifreServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	SifreDAO sifraDAO;
	
	@Transactional
	public SifreDAO getSifraDAO() {
		return sifraDAO;
	}

	@Transactional
	public void setSifraDAO(SifreDAO sifraDAO) {
		this.sifraDAO = sifraDAO;
	}

	@Transactional
	@Override
	public void unesiSifru(Sifre sifra) {
		sifraDAO.unesiSifru(sifra);
	}

	@Transactional
	@Override
	public void izmeniSifru(Sifre sifra) {
		sifraDAO.izmeniSifru(sifra);
	}

	@Transactional
	@Override
	public void izbrisiSifru(Sifre sifra) {
		sifraDAO.izbrisiSifru(sifra);
	}

	@Transactional
	@Override
	public Sifre nadjiSifruPoId(int id) {
		return sifraDAO.nadjiSifruPoId(id);
	}

	@Transactional
	@Override
	public ArrayList<Sifre> nadjiSveSifre(Korisnici korisnik) {
		return sifraDAO.nadjiSveSifre(korisnik);
	}

	@Transactional
	@Override
	public ArrayList<Sifre> nadjiSveSifrePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija, boolean izbrisan) {
		return sifraDAO.nadjiSveSifrePoPretplatniku(pretplatnik, organizacija, izbrisan);
	}

}

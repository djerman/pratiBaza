package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.GrupeKorisniciDAO;
import pratiBaza.servis.GrupeKorisniciServis;
import pratiBaza.tabele.GrupeKorisnici;

@Service("grupaKorisnikServis")
public class GrupeKorisniciServisImpl implements GrupeKorisniciServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	GrupeKorisniciDAO grupaKorisnikDAO;

	@Transactional
	public void unesiGrupaZaposleni(GrupeKorisnici grupaKorisnik) {
		grupaKorisnikDAO.unesiGrupaZaposleni(grupaKorisnik);
	}

	@Transactional
	public void azurirajGrupaZaposleni(GrupeKorisnici grupaKorisnik) {
		grupaKorisnikDAO.azurirajGrupaZaposleni(grupaKorisnik);
	}

	@Transactional
	public void izbrisiGrupaZaposleni(GrupeKorisnici grupaKorisnik) {
		grupaKorisnikDAO.izbrisiGrupaZaposleni(grupaKorisnik);
	}

	@Transactional
	public GrupeKorisniciDAO getGrupaKorisnikDAO() {
		return grupaKorisnikDAO;
	}

	@Transactional
	public void setGrupaKorisnikDAO(GrupeKorisniciDAO grupaKorisnikDAO) {
		this.grupaKorisnikDAO = grupaKorisnikDAO;
	}

	@Transactional
	public GrupeKorisnici nadjiGrupaKorisnikPoId(int id) {
		return grupaKorisnikDAO.nadjiGrupaKorisnikPoId(id);
	}
	
}

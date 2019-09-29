package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.PartneriDAO;
import pratiBaza.servis.PartneriServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Partneri;
import pratiBaza.tabele.SistemPretplatnici;

@Service("partnerServis")
public class PartneriServisImpl implements PartneriServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	PartneriDAO partnerDAO;
	
	@Override
	@Transactional
	public void unesiPartnera(Partneri partner) {
		partnerDAO.unesiPartnera(partner);
	}

	@Override
	@Transactional
	public void izmeniPartnera(Partneri partner) {
		partnerDAO.izmeniPartnera(partner);
	}

	@Override
	@Transactional
	public void izbrisiPartnera(Partneri partner) {
		partnerDAO.izbrisiPartnera(partner);
	}

	@Override
	@Transactional
	public Partneri nadjiPartneraPoId(int id) {
		return partnerDAO.nadjiPartneraPoId(id);
	}

	@Override
	@Transactional
	public ArrayList<Partneri> nadjiSvePartnere(Korisnici korisnik, boolean izbrisan) {
		return partnerDAO.nadjiSvePartnere(korisnik, izbrisan);
	}

	@Transactional
	public PartneriDAO getPartnerDAO() {
		return partnerDAO;
	}

	@Transactional
	public void setPartnerDAO(PartneriDAO partnerDAO) {
		this.partnerDAO = partnerDAO;
	}

	@Override
	@Transactional
	public Partneri nadjiPartneraPoPibu(SistemPretplatnici pretplatnik, int pib) {
		return partnerDAO.nadjiPartneraPoPibu(pretplatnik, pib);
	}

}

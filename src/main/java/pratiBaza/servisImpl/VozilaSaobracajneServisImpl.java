package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.VozilaSaobracajneDAO;
import pratiBaza.servis.VozilaSaobracajneServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.VozilaSaobracajne;

@Service("saobracajnaServis")
public class VozilaSaobracajneServisImpl implements VozilaSaobracajneServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozilaSaobracajneDAO saobracajnaDAO;
	
	@Override
	@Transactional
	public void unesiSaobracajnu(VozilaSaobracajne saobracajna) {
		saobracajnaDAO.unesiSaobracajnu(saobracajna);
	}

	@Override
	@Transactional
	public void izmeniSaobracajnu(VozilaSaobracajne saobracajna) {
		saobracajnaDAO.izmeniSaobracajnu(saobracajna);
	}

	@Override
	@Transactional
	public void izbrisiSaobracajnu(VozilaSaobracajne saobracajna) {
		saobracajnaDAO.izbrisiSaobracajnu(saobracajna);
	}

	@Override
	@Transactional
	public VozilaSaobracajne nadjiSaobracajnuPoId(int id) {
		return saobracajnaDAO.nadjiSaobracajnuPoId(id);
	}

	@Override
	@Transactional
	public VozilaSaobracajne nadjiSaobracajnuPoBroju(String broj) {
		return saobracajnaDAO.nadjiSaobracajnuPoBroju(broj);
	}

	@Override
	@Transactional
	public ArrayList<VozilaSaobracajne> nadjiSveSaobracajne(Korisnici korisnik, boolean izbrisan) {
		return saobracajnaDAO.nadjiSveSaobracajne(korisnik, izbrisan);
	}

	@Transactional
	public VozilaSaobracajneDAO getSaobracajnaDAO() {
		return saobracajnaDAO;
	}

	@Transactional
	public void setSaobracajnaDAO(VozilaSaobracajneDAO saobracajnaDAO) {
		this.saobracajnaDAO = saobracajnaDAO;
	}

	@Override
	@Transactional
	public ArrayList<VozilaSaobracajne> nadjiSlobodneSaobracajne(Korisnici korisnik, boolean izbrisan) {
		return saobracajnaDAO.nadjiSlobodneSaobracajne(korisnik, izbrisan);
	}

	@Override
	@Transactional
	public ArrayList<VozilaSaobracajne> nadjiSlobodneSaobracajnePoPretplatniku(SistemPretplatnici pretplatnik,
			Organizacije organizacija) {
		return saobracajnaDAO.nadjiSlobodneSaobracajnePoPretplatniku(pretplatnik, organizacija);
	}

}

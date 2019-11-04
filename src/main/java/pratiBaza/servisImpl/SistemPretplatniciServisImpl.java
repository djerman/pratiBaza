package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.SistemPretplatniciDAO;
import pratiBaza.servis.SistemPretplatniciServis;
import pratiBaza.tabele.SistemPretplatnici;

@Service("sistemPretplatnikServis")
public class SistemPretplatniciServisImpl implements SistemPretplatniciServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	SistemPretplatniciDAO sistemPretplatnikDAO;

	@Transactional
	public void unesiPretplatnika(SistemPretplatnici pretplatnik) {
		sistemPretplatnikDAO.unesiPretplatnika(pretplatnik);
	}

	@Transactional
	public void izmeniPretplatnika(SistemPretplatnici pretplatnik) {
		sistemPretplatnikDAO.izmeniPretplatnika(pretplatnik);
	}

	@Transactional
	public void izbrisiPretplatnika(SistemPretplatnici pretplatnik) {
		sistemPretplatnikDAO.izbrisiPretplatnika(pretplatnik);
	}

	@Transactional
	public ArrayList<SistemPretplatnici> nadjiSvePretplatnike() {
		return sistemPretplatnikDAO.nadjiSvePretplatnike();
	}

	@Transactional
	public SistemPretplatniciDAO getSistemPretplatnikDAO() {
		return sistemPretplatnikDAO;
	}

	@Transactional
	public void setSistemPretplatnikDAO(SistemPretplatniciDAO sistemPretplatnikDAO) {
		this.sistemPretplatnikDAO = sistemPretplatnikDAO;
	}

	@Transactional
	public SistemPretplatnici nadjiPretplatnikaPoId(int id) {
		return sistemPretplatnikDAO.nadjiPretplatnikaPoId(id);
	}

	@Transactional
	public ArrayList<SistemPretplatnici> nadjiSveAktivnePretplatnike() {
		return sistemPretplatnikDAO.nadjiSveAktivnePretplatnike();
	}

	@Transactional
	@Override
	public ArrayList<SistemPretplatnici> nadjiSveAktivneSistemskePretplatnike() {
		return sistemPretplatnikDAO.nadjiSveAktivneSistemskePretplatnike();
	}
	
}

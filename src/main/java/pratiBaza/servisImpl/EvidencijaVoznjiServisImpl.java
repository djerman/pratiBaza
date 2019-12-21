package pratiBaza.servisImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.EvidencijaVoznjiDAO;
import pratiBaza.servis.EvidencijaVoznjiServis;
import pratiBaza.tabele.EvidencijaVoznji;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

@Service("evidencijaServis")
public class EvidencijaVoznjiServisImpl implements EvidencijaVoznjiServis{

	EvidencijaVoznjiDAO evidencijaDAO;
	
	@Transactional
	public EvidencijaVoznjiDAO getEvidencijaDAO() {
		return evidencijaDAO;
	}

	@Transactional
	public void setEvidencijaDAO(EvidencijaVoznjiDAO evidencijaDAO) {
		this.evidencijaDAO = evidencijaDAO;
	}

	@Transactional
	@Override
	public void unesiEvidenciju(EvidencijaVoznji evidencija) {
		evidencijaDAO.unesiEvidenciju(evidencija);
	}

	@Transactional
	@Override
	public void izmeniEvidenciju(EvidencijaVoznji evidencija) {
		evidencijaDAO.izmeniEvidenciju(evidencija);
	}

	@Transactional
	@Override
	public void izbrisiEvidenciju(EvidencijaVoznji evidencija) {
		evidencijaDAO.izbrisiEvidenciju(evidencija);
	}

	@Transactional
	@Override
	public EvidencijaVoznji nadjiEvidencijuPoId(int id) {
		return evidencijaDAO.nadjiEvidencijuPoId(id);
	}

	@Transactional
	@Override
	public ArrayList<EvidencijaVoznji> vratiEvidencije(SistemPretplatnici pretplatnik, Organizacije organizacija,
			String nalog, String registracija, String vozac, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		return evidencijaDAO.vratiEvidencije(pretplatnik, organizacija, nalog, registracija, vozac, datumVremeOd, datumVremeDo);
	}

}

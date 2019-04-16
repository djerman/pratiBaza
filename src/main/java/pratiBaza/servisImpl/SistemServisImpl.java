package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.SistemDAO;
import pratiBaza.servis.SistemServis;
import pratiBaza.tabele.Sistem;

@Service("sistemServis")
public class SistemServisImpl implements SistemServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	SistemDAO sistemDAO;

	@Transactional
	public void unesiSistem(Sistem sistem) {
		sistemDAO.unesiSistem(sistem);
	}

	@Transactional
	public void azurirajSistem(Sistem sistem) {
		sistemDAO.azurirajSistem(sistem);
	}

	@Transactional
	public void izbrisiSistem(Sistem sistem) {
		sistemDAO.izbrisiSistem(sistem);
	}

	@Transactional
	public SistemDAO getSistemDAO() {
		return sistemDAO;
	}

	@Transactional
	public void setSistemDAO(SistemDAO sistemDAO) {
		this.sistemDAO = sistemDAO;
	}

	@Transactional
	public Sistem vratiSistem() {
		return sistemDAO.vratiSistem();
	}

	@Transactional
	public Sistem nadjiSistemPoId(int id) {
		return sistemDAO.nadjiSistemPoId(id);
	}
	
}

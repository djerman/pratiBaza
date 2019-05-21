package pratiBaza.servisImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.SistemGorivaDAO;
import pratiBaza.servis.SistemGorivoServis;
import pratiBaza.tabele.SistemGoriva;

@Service("sistemGorivoServis")
public class SistemGorivoServisImpl implements SistemGorivoServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	SistemGorivaDAO sistemGorivoDAO;

	@Transactional
	public void unesiGorivo(SistemGoriva gorivo) {
		sistemGorivoDAO.unesiGorivo(gorivo);
	}

	@Transactional
	public void azurirajGorivo(SistemGoriva gorivo) {
		sistemGorivoDAO.azurirajGorivo(gorivo);
	}

	@Transactional
	public void izbrisiGorivo(SistemGoriva gorivo) {
		sistemGorivoDAO.izbrisiGorivo(gorivo);
	}

	@Transactional
	public SistemGorivaDAO getSistemGorivoDAO() {
		return sistemGorivoDAO;
	}

	@Transactional
	public void setSistemGorivoDAO(SistemGorivaDAO sistemGorivoDAO) {
		this.sistemGorivoDAO = sistemGorivoDAO;
	}

	@Transactional
	public ArrayList<SistemGoriva> vratiSvaGoriva(boolean izbrisan) {
		return sistemGorivoDAO.vratiSvaGoriva(izbrisan);
	}

	@Transactional
	public SistemGoriva nadjiGorivoPoId(int id) {
		return sistemGorivoDAO.nadjiGorivoPoId(id);
	}
	
}

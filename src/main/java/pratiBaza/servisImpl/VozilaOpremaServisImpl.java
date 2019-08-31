package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.VozilaOpremaDAO;
import pratiBaza.servis.VozilaOpremaServis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozilaOprema;

@Service("voziloOpremaServis")
public class VozilaOpremaServisImpl implements VozilaOpremaServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozilaOpremaDAO voziloOpremaDAO;

	@Transactional
	@Override
	public void unesiVoziloOpremu(VozilaOprema oprema) {
		voziloOpremaDAO.unesiVoziloOpremu(oprema);
	}

	@Transactional
	@Override
	public void izmeniVoziloOpremu(VozilaOprema oprema) {
		voziloOpremaDAO.izmeniVoziloOpremu(oprema);
	}

	@Transactional
	@Override
	public void izbrisiVoziloOpremu(VozilaOprema oprema) {
		voziloOpremaDAO.izbrisiVoziloOpremu(oprema);
	}

	@Transactional
	@Override
	public VozilaOprema nadjiVoziloOpremuPoId(int id) {
		return voziloOpremaDAO.nadjiVoziloOpremuPoId(id);
	}

	@Transactional
	@Override
	public ArrayList<VozilaOprema> nadjiSveVozilaOprema(Korisnici korisnik) {
		return voziloOpremaDAO.nadjiSveVozilaOprema(korisnik);
	}

	@Transactional
	public VozilaOpremaDAO getVoziloOpremaDAO() {
		return voziloOpremaDAO;
	}

	@Transactional
	public void setVoziloOpremaDAO(VozilaOpremaDAO voziloOpremaDAO) {
		this.voziloOpremaDAO = voziloOpremaDAO;
	}
	
}

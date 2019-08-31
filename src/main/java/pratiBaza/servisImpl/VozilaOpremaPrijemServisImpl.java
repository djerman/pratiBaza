package pratiBaza.servisImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.VozilaOpremaPrijemDAO;
import pratiBaza.servis.VozilaOpremaPrijemServis;
import pratiBaza.tabele.VozilaOpremaPrijem;
import pratiBaza.tabele.VozilaPrimoPredaje;

@Service("voziloOpremaPrijemServis")
public class VozilaOpremaPrijemServisImpl implements VozilaOpremaPrijemServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozilaOpremaPrijemDAO voziloOpremaPrijemDAO;

	@Transactional
	@Override
	public void unesiVoziloOpremaPredaja(VozilaOpremaPrijem predaja) {
		voziloOpremaPrijemDAO.unesiVoziloOpremaPredaja(predaja);
	}

	@Transactional
	@Override
	public void izmeniVoziloOpremaPredaja(VozilaOpremaPrijem predaja) {
		voziloOpremaPrijemDAO.izmeniVoziloOpremaPredaja(predaja);
	}

	@Transactional
	@Override
	public void izbrisiVoziloOpremaPredaja(VozilaOpremaPrijem predaja) {
		voziloOpremaPrijemDAO.izbrisiVoziloOpremaPredaja(predaja);
	}

	@Transactional
	@Override
	public VozilaOpremaPrijem nadjiVoziloOpremaPradajaPoId(int id) {
		return voziloOpremaPrijemDAO.nadjiVoziloOpremaPradajaPoId(id);
	}

	@Transactional
	@Override
	public ArrayList<VozilaOpremaPrijem> nadjiVozilaOpremaPredajaPoPP(VozilaPrimoPredaje primoPredaja) {
		return voziloOpremaPrijemDAO.nadjiVozilaOpremaPredajaPoPP(primoPredaja);
	}

	@Transactional
	public VozilaOpremaPrijemDAO getVoziloOpremaPrijemDAO() {
		return voziloOpremaPrijemDAO;
	}

	@Transactional
	public void setVoziloOpremaPrijemDAO(VozilaOpremaPrijemDAO voziloOpremaPredajaDAO) {
		this.voziloOpremaPrijemDAO = voziloOpremaPredajaDAO;
	}

	@Transactional
	@Override
	public void izbrisiSvaVoziloOpremaPredaja(VozilaPrimoPredaje primoPredaja) {
		voziloOpremaPrijemDAO.izbrisiSvaVoziloOpremaPredaja(primoPredaja);
	}

	@Transactional
	@Override
	public void unesiSvaVoziloOpremaPredaja(List<VozilaOpremaPrijem> opremaStavke) {
		voziloOpremaPrijemDAO.unesiSvaVoziloOpremaPredaja(opremaStavke);
	}
	
}

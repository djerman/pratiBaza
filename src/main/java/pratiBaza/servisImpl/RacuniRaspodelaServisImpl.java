package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.RacuniRaspodelaDAO;
import pratiBaza.servis.RacuniRaspodelaServis;
import pratiBaza.tabele.Partneri;
import pratiBaza.tabele.Racuni;
import pratiBaza.tabele.RacuniRaspodela;

@Service("racunRaspodelaServis")
public class RacuniRaspodelaServisImpl implements RacuniRaspodelaServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	RacuniRaspodelaDAO racunRaspodelaDAO;

	@Transactional
	public RacuniRaspodelaDAO getRacunRaspodelaDAO() {
		return racunRaspodelaDAO;
	}

	@Transactional
	public void setRacunRaspodelaDAO(RacuniRaspodelaDAO racunRaspodelaDAO) {
		this.racunRaspodelaDAO = racunRaspodelaDAO;
	}

	@Transactional
	@Override
	public void unesiRacunRaspodelu(RacuniRaspodela raspodela) {
		racunRaspodelaDAO.unesiRacunRaspodelu(raspodela);
	}

	@Transactional
	@Override
	public void izmeniRacunRaspodelu(RacuniRaspodela raspodela) {
		racunRaspodelaDAO.izmeniRacunRaspodelu(raspodela);
	}

	@Transactional
	@Override
	public void izbrisiRacunRaspodelu(RacuniRaspodela raspodela) {
		racunRaspodelaDAO.izbrisiRacunRaspodelu(raspodela);
	}

	@Transactional
	@Override
	public RacuniRaspodela nadjiRacunRaspodeluPoId(int id) {
		return racunRaspodelaDAO.nadjiRacunRaspodeluPoId(id);
	}

	@Transactional
	@Override
	public ArrayList<RacuniRaspodela> nadjiRacuneRaspodelePoRacunu(Racuni racun) {
		return racunRaspodelaDAO.nadjiRacuneRaspodelePoRacunu(racun);
	}

	@Transactional
	@Override
	public ArrayList<RacuniRaspodela> nadjiRacuneRaspodelePoPartneru(Partneri partner) {
		return racunRaspodelaDAO.nadjiRacuneRaspodelePoPartneru(partner);
	}
	
	
}

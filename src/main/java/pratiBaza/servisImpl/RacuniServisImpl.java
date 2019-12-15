package pratiBaza.servisImpl;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.RacuniDAO;
import pratiBaza.servis.RacuniServis;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.Partneri;
import pratiBaza.tabele.Racuni;
import pratiBaza.tabele.SistemPretplatnici;

@Service("racunServis")
public class RacuniServisImpl implements RacuniServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	RacuniDAO racunDAO;
	
	@Transactional
	public RacuniDAO getRacunDAO() {
		return racunDAO;
	}

	@Transactional
	public void setRacunDAO(RacuniDAO racunDAO) {
		this.racunDAO = racunDAO;
	}

	@Transactional
	@Override
	public void unesiRacun(Racuni racun) {
		racunDAO.unesiRacun(racun);
	}

	@Transactional
	@Override
	public void izmeniRacun(Racuni racun) {
		racunDAO.izmeniRacun(racun);
	}

	@Transactional
	@Override
	public void izbrisiRacun(Racuni racun) {
		racunDAO.izbrisiRacun(racun);
	}

	@Transactional
	@Override
	public Racuni nadjiRacunPoId(int id) {
		return racunDAO.nadjiRacunPoId(id);
	}

	@Transactional
	@Override
	public ArrayList<Racuni> nadjiRacunePoPretplatniku(SistemPretplatnici pretplatnik, Organizacije organizacija,
			boolean izbrisan, Date datumOd, Date DatumDo, Partneri partner) {
		return racunDAO.nadjiRacunePoPretplatniku(pretplatnik, organizacija, izbrisan, datumOd, DatumDo, partner);
	}

}

package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.AlarmiKorisnikDAO;
import pratiBaza.servis.AlarmiKorisnikServis;
import pratiBaza.tabele.AlarmiKorisnik;

@Service("alarmKorisnikServis")
public class AlarmiKorisnikServisImpl implements AlarmiKorisnikServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	AlarmiKorisnikDAO alarmKorisnikDAO;

	@Transactional
	public void unesiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		alarmKorisnikDAO.unesiAlarmiKorisnik(alarmKorisnik);
	}

	@Transactional
	public void azurirajAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		alarmKorisnikDAO.azurirajAlarmiKorisnik(alarmKorisnik);
	}

	@Transactional
	public void izbrisiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		alarmKorisnikDAO.izbrisiAlarmiKorisnik(alarmKorisnik);
	}

	@Transactional
	public AlarmiKorisnikDAO getAlarmKorisnikDAO() {
		return alarmKorisnikDAO;
	}

	@Transactional
	public void setAlarmKorisnikDAO(AlarmiKorisnikDAO alarmKorisnikDAO) {
		this.alarmKorisnikDAO = alarmKorisnikDAO;
	}
	
	
}

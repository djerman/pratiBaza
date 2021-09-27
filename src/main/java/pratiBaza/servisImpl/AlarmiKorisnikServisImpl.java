package pratiBaza.servisImpl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.AlarmiKorisnikDAO;
import pratiBaza.servis.AlarmiKorisnikServis;
import pratiBaza.tabele.AlarmiKorisnik;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemAlarmi;

@Service("alarmKorisnikServis")
public class AlarmiKorisnikServisImpl implements AlarmiKorisnikServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	AlarmiKorisnikDAO alarmKorisnikDAO;

	@Transactional
	public void unesiAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		alarmKorisnikDAO.unesiAlarmiKorisnik(alarmKorisnik);
	}

	@Transactional
	public List<AlarmiKorisnik> vratiAlarmePoKorisniku(Korisnici korisnik, boolean aktivno, boolean email, boolean obavestenje) {
		return alarmKorisnikDAO.vratiAlarmePoKorisniku(korisnik, aktivno, email, obavestenje);
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

	@Transactional
	public AlarmiKorisnik nadjiAlarmKorisnikPoId(int id) {
		return alarmKorisnikDAO.nadjiAlarmKorisnikPoId(id);
	}

	@Override
	@Transactional
	public AlarmiKorisnik nadjiAlarmePoKorisnikObjekatAlarm(Korisnici korisnik, Objekti objekat, SistemAlarmi alarm) {
		return alarmKorisnikDAO.nadjiAlarmePoKorisnikObjekatAlarm(korisnik, objekat, alarm);
	}

	@Override
	@Transactional
	public List<AlarmiKorisnik> nadjiSveAlarmePoKorisniku(Korisnici korisnik, boolean aktivno, boolean email, boolean obavestenje) {
		return alarmKorisnikDAO.nadjiSveAlarmePoKorisniku(korisnik, aktivno, email, obavestenje);
	}

	@Override
	@Transactional
	public List<AlarmiKorisnik> nadjiSveAlarmeKorisnikePoObjektu(Objekti objekat) {
		return alarmKorisnikDAO.nadjiSveAlarmeKorisnikePoObjektu(objekat);
	}
	
}

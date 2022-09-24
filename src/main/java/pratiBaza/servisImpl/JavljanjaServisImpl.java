package pratiBaza.servisImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.JavljanjaDAO;
import pratiBaza.pomocne.KontrolaGoriva;
import pratiBaza.pomocne.PredjeniPut;
import pratiBaza.pomocne.PredjeniPutGPS;
import pratiBaza.pomocne.StajanjeMirovanje;
import pratiBaza.servis.JavljanjaServis;
import pratiBaza.tabele.Javljanja;
import pratiBaza.tabele.Obd;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemAlarmi;
import pratiBaza.tabele.Vozila;

@Service("javljanjeServis")
public class JavljanjaServisImpl implements JavljanjaServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	JavljanjaDAO javljanjeDAO;

	@Override
	@Transactional
	public void unesiJavljanja(Javljanja javljanje) {
		javljanjeDAO.unesiJavljanja(javljanje);
	}

	@Override
	@Transactional
	public void azurirajJavljanja(Javljanja javljanje) {
		javljanjeDAO.azurirajJavljanja(javljanje);
	}

	@Override
	@Transactional
	public void izbrisiJavljanja(Javljanja javljanje) {
		javljanjeDAO.izbrisiJavljanja(javljanje);
	}

	@Transactional
	public JavljanjaDAO getJavljanjeDAO() {
		return javljanjeDAO;
	}

	@Transactional
	public void setJavljanjeDAO(JavljanjaDAO javljanjeDAO) {
		this.javljanjeDAO = javljanjeDAO;
	}

	@Override
	@Transactional
	public Javljanja nadjiPoslednjeJavljanjePoObjektu(Objekti objekat) {
		return javljanjeDAO.nadjiPoslednjeJavljanjePoObjektu(objekat);
	}

	@Override
	@Transactional
	public List<Javljanja> vratiJavljanjaObjektaOdDo(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		return javljanjeDAO.vratiJavljanjaObjektaOdDo(objekat, vremeOd, vremeDo);
	}

	@Override
	@Transactional
	public List<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmima(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		return javljanjeDAO.vratiJavljanjaObjektaOdDoSaAlarmima(objekat, vremeOd, vremeDo);
	}

	@Override
	@Transactional
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		return javljanjeDAO.vratiJavljanjaObjektaOdDoPrvoPoslednje(objekat, datumVremeOd, datumVremeDo);
	}

	@Override
	@Transactional
	public ArrayList<Obd> nadjiObdPoObjektuOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		return javljanjeDAO.nadjiObdPoObjektuOdDoPrvoPoslednje(objekat, datumVremeOd, datumVremeDo);
	}
	
	@Override
	@Transactional
	public List<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmimaZona(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo, ArrayList<SistemAlarmi> alarmi) {
		return javljanjeDAO.vratiJavljanjaObjektaOdDoSaAlarmimaZona(objekat, vremeOd, vremeDo, alarmi);
	}

	@Override
	@Transactional
	public List<Javljanja> vratiJavljanjaObjekataOdDoSaAlarmima(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, boolean pregled) {
		return javljanjeDAO.vratiJavljanjaObjekataOdDoSaAlarmima(objekti, vremeOd, vremeDo, pregled);
	}

	@Override
	@Transactional
	public List<Javljanja> vratiJavljanjaObjekataOdDoSaBrzinama(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo) {
		return javljanjeDAO.vratiJavljanjaObjekataOdDoSaBrzinama(objekti, vremeOd, vremeDo);
	}

	@Override
	@Transactional
	public Javljanja vratiJavljanjeObjektaDoIliOd(Objekti objekat, Timestamp datumVreme, boolean vremeDo) {
		return javljanjeDAO.vratiJavljanjeObjektaDoIliOd(objekat, datumVreme, vremeDo);
	}

	@Override
	@Transactional
	public Obd vratiObdObjektaDoIliOd(Objekti objekat, Timestamp datumVreme, boolean vremeDo) {
		return javljanjeDAO.vratiObdObjektaDoIliOd(objekat, datumVreme, vremeDo);
	}
	
	@Override
	@Transactional
	public ArrayList<Javljanja> vratiJavljanjaZaStajanja(Objekti objekat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ArrayList<StajanjeMirovanje> vratiStajanjaMirovanja(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, int duzina) {
		return javljanjeDAO.vratiStajanjaMirovanja(objekti, vremeOd, vremeDo, duzina);
	}

	@Override
	@Transactional
	public Javljanja vratiJavljanjeZaStajanje(Objekti objekat) {
		return javljanjeDAO.vratiJavljanjeZaStajanje(objekat);
	}

	@Override
	@Transactional
	public ArrayList<PredjeniPut> vratiPredjeniPut(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo) {
		return javljanjeDAO.vratiPredjeniPut(objekti, vremeOd, vremeDo);
	}

	@Override
	@Transactional
	public ArrayList<PredjeniPut> nadjiPredjeniPut(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo) {
		return javljanjeDAO.nadjiPredjeniPut(objekti, vremeOd, vremeDo);
	}

	@Override
	@Transactional
	public ArrayList<PredjeniPutGPS> nadjiPredjeniPutGPS(ArrayList<Objekti> objekti, Timestamp vremeOd,
			Timestamp vremeDo) {
		return javljanjeDAO.nadjiPredjeniPutGPS(objekti, vremeOd, vremeDo);
	}
	
	@Override
	@Transactional
	public ArrayList<KontrolaGoriva> vratiKontroluGoriva(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo){
		return javljanjeDAO.vratiKontroluGoriva(objekti, vremeOd, vremeDo);
	}

	@Override
	@Transactional
	public ArrayList<Vozila> vratiVozilaZaServise(ArrayList<Objekti> objekti, int tipServisa, int doServisa) {
		return javljanjeDAO.vratiVozilaZaServise(objekti, tipServisa, doServisa);
	}
	
	@Override
	@Transactional
	public float nadjiSumuPotroseneKolicine(Objekti objakat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		return javljanjeDAO.nadjiSumuPotroseneKolicine(objakat, datumVremeOd, datumVremeDo);
	}
	
}

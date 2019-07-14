package pratiBaza.servisImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.JavljanjaDAO;
import pratiBaza.servis.JavljanjaServis;
import pratiBaza.tabele.Javljanja;
import pratiBaza.tabele.Objekti;
import pratiBaza.tabele.SistemAlarmi;

@Service("javljanjeServis")
public class JavljanjaServisImpl implements JavljanjaServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	JavljanjaDAO javljanjeDAO;

	@Transactional
	public void unesiJavljanja(Javljanja javljanje) {
		javljanjeDAO.unesiJavljanja(javljanje);
	}

	@Transactional
	public void azurirajJavljanja(Javljanja javljanje) {
		javljanjeDAO.azurirajJavljanja(javljanje);
	}

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
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDo(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		return javljanjeDAO.vratiJavljanjaObjektaOdDo(objekat, vremeOd, vremeDo);
	}

	@Override
	@Transactional
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmima(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo) {
		return javljanjeDAO.vratiJavljanjaObjektaOdDoSaAlarmima(objekat, vremeOd, vremeDo);
	}

	@Override
	@Transactional
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoPrvoPoslednje(Objekti objekat, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		return javljanjeDAO.vratiJavljanjaObjektaOdDoPrvoPoslednje(objekat, datumVremeOd, datumVremeDo);
	}

	@Override
	@Transactional
	public ArrayList<Javljanja> vratiJavljanjaObjektaOdDoSaAlarmimaZona(Objekti objekat, Timestamp vremeOd, Timestamp vremeDo, ArrayList<SistemAlarmi> alarmi) {
		return javljanjeDAO.vratiJavljanjaObjektaOdDoSaAlarmimaZona(objekat, vremeOd, vremeDo, alarmi);
	}

	@Override
	@Transactional
	public ArrayList<Javljanja> vratiJavljanjaObjekataOdDoSaAlarmima(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo, boolean pregled) {
		return javljanjeDAO.vratiJavljanjaObjekataOdDoSaAlarmima(objekti, vremeOd, vremeDo, pregled);
	}

	@Override
	@Transactional
	public ArrayList<Javljanja> vratiJavljanjaObjekataOdDoSaBrzinama(ArrayList<Objekti> objekti, Timestamp vremeOd, Timestamp vremeDo) {
		return javljanjeDAO.vratiJavljanjaObjekataOdDoSaBrzinama(objekti, vremeOd, vremeDo);
	}

	@Override
	@Transactional
	public Javljanja vratiJavljanjePoslednjeObjektaDo(Objekti objekat, Timestamp vremeDo) {
		return javljanjeDAO.vratiJavljanjePoslednjeObjektaDo(objekat, vremeDo);
	}
	
}

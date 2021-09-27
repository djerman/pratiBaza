package pratiBaza.servisImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.JavljanjaPoslednjaDAO;
import pratiBaza.servis.JavljanjaPoslednjaServis;
import pratiBaza.tabele.JavljanjaPoslednja;
import pratiBaza.tabele.Objekti;

@Service("javljanjePoslednjeServis")
public class JavljanjaPoslednjaServisImpl implements JavljanjaPoslednjaServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	JavljanjaPoslednjaDAO javljanjePoslednjeDAO;

	@Transactional
	public void unesiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje) {
		javljanjePoslednjeDAO.unesiJavljanjaPoslednja(javljanjePoslednje);
	}

	@Transactional
	public void azurirajJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje) {
		javljanjePoslednjeDAO.azurirajJavljanjaPoslednja(javljanjePoslednje);
	}

	@Transactional
	public void izbrisiJavljanjaPoslednja(JavljanjaPoslednja javljanjePoslednje) {
		javljanjePoslednjeDAO.izbrisiJavljanjaPoslednja(javljanjePoslednje);
	}

	@Transactional
	public JavljanjaPoslednjaDAO getJavljanjePoslednjeDAO() {
		return javljanjePoslednjeDAO;
	}

	@Transactional
	public void setJavljanjePoslednjeDAO(JavljanjaPoslednjaDAO javljanjePOslednjeDAO) {
		this.javljanjePoslednjeDAO = javljanjePOslednjeDAO;
	}

	@Transactional
	public List<JavljanjaPoslednja> vratiListuJavljanjaPoslednjih(ArrayList<Objekti> objekti) {
		return javljanjePoslednjeDAO.vratiListuJavljanjaPoslednjih(objekti);
	}

	@Override
	@Transactional
	public JavljanjaPoslednja nadjiJavljanjaPoslednjaPoObjektu(Objekti objekat) {
		return javljanjePoslednjeDAO.nadjiJavljanjaPoslednjaPoObjektu(objekat);
	}
	
}

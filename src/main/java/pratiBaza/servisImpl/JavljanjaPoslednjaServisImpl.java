package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.JavljanjaPoslednjaDAO;
import pratiBaza.servis.JavljanjaPoslednjaServis;
import pratiBaza.tabele.JavljanjaPoslednja;

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
	public JavljanjaPoslednjaDAO getJavljanjePOslednjeDAO() {
		return javljanjePoslednjeDAO;
	}

	@Transactional
	public void setJavljanjePOslednjeDAO(JavljanjaPoslednjaDAO javljanjePOslednjeDAO) {
		this.javljanjePoslednjeDAO = javljanjePOslednjeDAO;
	}
	

}

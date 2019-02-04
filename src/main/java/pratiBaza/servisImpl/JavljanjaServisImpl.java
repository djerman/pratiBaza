package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.JavljanjaDAO;
import pratiBaza.servis.JavljanjaServis;
import pratiBaza.tabele.Javljanja;

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
	
}

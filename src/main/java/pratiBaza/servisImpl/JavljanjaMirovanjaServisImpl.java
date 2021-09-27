package pratiBaza.servisImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.JavljanjaMirovanjaDAO;
import pratiBaza.servis.JavljanjaMirovanjaServis;
import pratiBaza.tabele.JavljanjaMirovanja;
import pratiBaza.tabele.Objekti;

@Service("javljanjeMirovanjeServis")
public class JavljanjaMirovanjaServisImpl implements JavljanjaMirovanjaServis{

	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	JavljanjaMirovanjaDAO javljanjeMirovanjeDAO;
	
	@Override
	@Transactional
	public void unesiJavljanjaMirovanja(JavljanjaMirovanja javljanje) {
		javljanjeMirovanjeDAO.unesiJavljanjaMirovanja(javljanje);
	}

	@Override
	@Transactional
	public void azurirajJavljanajMirovanja(JavljanjaMirovanja javljanje) {
		javljanjeMirovanjeDAO.azurirajJavljanajMirovanja(javljanje);	
	}

	@Override
	@Transactional
	public void izbrisijavljanjaMirovanja(JavljanjaMirovanja javljanje) {
		javljanjeMirovanjeDAO.izbrisijavljanjaMirovanja(javljanje);
	}
	
	@Override
	@Transactional
	public List<JavljanjaMirovanja> vratiListuJavljanjaMirovanja(ArrayList<Objekti> objekti) {
		return javljanjeMirovanjeDAO.vratiListuJavljanjaMirovanja(objekti);
	}

	@Override
	@Transactional
	public JavljanjaMirovanja nadjiJavljanjaMirovanjaPoObjektu(Objekti objekat) {
		return javljanjeMirovanjeDAO.nadjiJavljanjaMirovanjaPoObjektu(objekat);
	}

	@Transactional
	public void setJavljanjeMirovanjeDAO(JavljanjaMirovanjaDAO javljanjeMirovanjeDAO) {
		this.javljanjeMirovanjeDAO = javljanjeMirovanjeDAO;
	}

	@Transactional
	public JavljanjaMirovanjaDAO getJavljanjeMirovanjeDAO() {
		return javljanjeMirovanjeDAO;
	}

}

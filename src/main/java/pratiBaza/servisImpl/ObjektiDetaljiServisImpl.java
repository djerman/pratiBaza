package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.ObjektiDetaljiDAO;
import pratiBaza.servis.ObjektiDetaljiServis;
import pratiBaza.tabele.ObjektiDetalji;

@Service("objekatDetaljServis")
public class ObjektiDetaljiServisImpl implements ObjektiDetaljiServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	ObjektiDetaljiDAO objekatDetaljDAO;

	@Transactional
	public void unesiObjektiDetalji(ObjektiDetalji objekatDetalj) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void azurirajObjektiDetalji(ObjektiDetalji objekatDetalj) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void izbrisiObjektiDetalji(ObjektiDetalji objekatDetalj) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public ObjektiDetaljiDAO getObjekatDetaljDAO() {
		return objekatDetaljDAO;
	}

	@Transactional
	public void setObjekatDetaljDAO(ObjektiDetaljiDAO objekatDetaljDAO) {
		this.objekatDetaljDAO = objekatDetaljDAO;
	}
	
}

package pratiBaza.servisImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pratiBaza.dao.GrupeObjektiDAO;
import pratiBaza.servis.GrupeObjektiServis;
import pratiBaza.tabele.GrupeObjekti;

@Service("grupaObjekatServis")
public class GrupeObjektiServisImpl implements GrupeObjektiServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	GrupeObjektiDAO grupaObjekatDAO;

	@Transactional
	public void unesiGrupaObjekat(GrupeObjekti grupaObjekat) {
		grupaObjekatDAO.unesiGrupaObjekat(grupaObjekat);
	}

	@Transactional
	public void azurirajGrupaObjekat(GrupeObjekti grupaObjekat) {
		grupaObjekatDAO.azurirajGrupaObjekat(grupaObjekat);
	}

	@Transactional
	public void izbrisiGrupaObjekat(GrupeObjekti grupaObjekat) {
		grupaObjekatDAO.izbrisiGrupaObjekat(grupaObjekat);
	}

	public GrupeObjektiDAO getGrupaObjekatDAO() {
		return grupaObjekatDAO;
	}

	public void setGrupaObjekatDAO(GrupeObjektiDAO grupaObjekatDAO) {
		this.grupaObjekatDAO = grupaObjekatDAO;
	}
	

}

package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.SistemUredjajiProizvodjaciDAO;
import pratiBaza.servis.SistemUredjajiProizvodjaciServis;
import pratiBaza.tabele.SistemUredjajiProizvodjac;

@Service("sistemUredjajProizvodjacServis")
public class SistemUredjajiProizvodjaciServisImpl implements SistemUredjajiProizvodjaciServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	SistemUredjajiProizvodjaciDAO sistemUredjajProizvodjacDAO;

	@Transactional
	public void unesiSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		sistemUredjajProizvodjacDAO.unesiSistemUredjajProizvodjaca(proizvodjac);
	}

	@Transactional
	public void izmeniSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		sistemUredjajProizvodjacDAO.izmeniSistemUredjajProizvodjaca(proizvodjac);
	}

	@Transactional
	public void izbrisiSistemUredjajProizvodjaca(SistemUredjajiProizvodjac proizvodjac) {
		sistemUredjajProizvodjacDAO.izbrisiSistemUredjajProizvodjaca(proizvodjac);
	}

	@Transactional
	public ArrayList<SistemUredjajiProizvodjac> nadjiSveSistemUredjajeProizvodjace() {
		return sistemUredjajProizvodjacDAO.nadjiSveSistemUredjajeProizvodjace();
	}

	@Transactional
	public SistemUredjajiProizvodjaciDAO getSistemUredjajProizvodjacDAO() {
		return sistemUredjajProizvodjacDAO;
	}

	@Transactional
	public void setSistemUredjajProizvodjacDAO(SistemUredjajiProizvodjaciDAO sistemUredjajProizvodjacDAO) {
		this.sistemUredjajProizvodjacDAO = sistemUredjajProizvodjacDAO;
	}

	@Transactional
	public SistemUredjajiProizvodjac nadjiProizvodjacaPoId(int id) {
		return sistemUredjajProizvodjacDAO.nadjiProizvodjacaPoId(id);
	}
	
}

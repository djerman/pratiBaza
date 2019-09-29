package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.VozilaSaobracajne2DAO;
import pratiBaza.servis.VozilaSaobracajne2Servis;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozilaSaobracajne;
import pratiBaza.tabele.VozilaSaobracajne2;

@Service("saobracajna2Servis")
public class VozilaSaobracajne2ServisImpl implements VozilaSaobracajne2Servis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	VozilaSaobracajne2DAO saobracajna2DAO;

	@Override
	@Transactional
	public void unesiSaobracajnu2(VozilaSaobracajne2 saobracajna2) {
		saobracajna2DAO.unesiSaobracajnu2(saobracajna2);
	}

	@Override
	@Transactional
	public void izmeniSaobracajnu2(VozilaSaobracajne2 saobracajna2) {
		saobracajna2DAO.izmeniSaobracajnu2(saobracajna2);
	}

	@Override
	@Transactional
	public void izbrisiSaobracajnu2(VozilaSaobracajne2 saobracajna2) {
		saobracajna2DAO.izbrisiSaobracajnu2(saobracajna2);
	}

	@Override
	@Transactional
	public VozilaSaobracajne2 nadjiSaobracajnu2PoId(int id) {
		return saobracajna2DAO.nadjiSaobracajnu2PoId(id);
	}

	@Override
	@Transactional
	public VozilaSaobracajne2 nadjiSaobracajnu2PoBroju(VozilaSaobracajne saobracajna) {
		return saobracajna2DAO.nadjiSaobracajnu2PoBroju(saobracajna);
	}

	@Override
	@Transactional
	public ArrayList<VozilaSaobracajne2> nadjiSveSaobracajne2(Korisnici korisnik, boolean izbrisan) {
		return saobracajna2DAO.nadjiSveSaobracajne2(korisnik, izbrisan);
	}

	@Transactional
	public VozilaSaobracajne2DAO getSaobracajna2DAO() {
		return saobracajna2DAO;
	}

	@Transactional
	public void setSaobracajna2DAO(VozilaSaobracajne2DAO saobracajna2dao) {
		saobracajna2DAO = saobracajna2dao;
	}

}

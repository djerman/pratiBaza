package pratiBaza.servisImpl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pratiBaza.dao.SistemOperateriDAO;
import pratiBaza.servis.SistemOperateriServis;
import pratiBaza.tabele.SistemOperateri;

@Service("sistemOperaterServis")
public class SistemOperateriServisImpl implements SistemOperateriServis{
	//mora da se doda i get i set metode za objekat AkcijeDAO jer ne radi spring bez toga
	SistemOperateriDAO sistemOperaterDAO;

	@Transactional
	public void unesiOperatera(SistemOperateri operater) {
		sistemOperaterDAO.unesiOperatera(operater);
	}

	@Transactional
	public void azurirajOperatera(SistemOperateri operater) {
		sistemOperaterDAO.azurirajOperatera(operater);
	}

	@Transactional
	public void izbrisiOperatera(SistemOperateri operater) {
		sistemOperaterDAO.izbrisiOperatera(operater);
	}

	@Transactional
	public ArrayList<SistemOperateri> nadjiSveOperatere() {
		return sistemOperaterDAO.nadjiSveOperatere();
	}

	@Transactional
	public SistemOperateriDAO getSistemOperaterDAO() {
		return sistemOperaterDAO;
	}

	@Transactional
	public void setSistemOperaterDAO(SistemOperateriDAO sistemOperaterDAO) {
		this.sistemOperaterDAO = sistemOperaterDAO;
	}

	@Transactional
	public SistemOperateri nadjiOperateraPoId(int id) {
		return sistemOperaterDAO.nadjiOperateraPoId(id);
	}
	
}

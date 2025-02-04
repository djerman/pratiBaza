package pratiBaza.dao;

import java.util.ArrayList;

import pratiBaza.tabele.SistemPretplatnici;

public interface SistemPretplatniciDAO {
	
	void unesiPretplatnika(SistemPretplatnici pretplatnik);
	
	void izmeniPretplatnika(SistemPretplatnici pretplatnik);
	
	void izbrisiPretplatnika(SistemPretplatnici pretplatnik);
	
	ArrayList<SistemPretplatnici> nadjiSvePretplatnike();

	SistemPretplatnici nadjiPretplatnikaPoId(int id);
	
	ArrayList<SistemPretplatnici> nadjiSveAktivnePretplatnike();
	
	ArrayList<SistemPretplatnici> nadjiSveAktivneSistemskePretplatnike();
}

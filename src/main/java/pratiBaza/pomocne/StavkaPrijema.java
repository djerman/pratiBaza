package pratiBaza.pomocne;

import java.io.Serializable;

import pratiBaza.tabele.VozilaOprema;

public class StavkaPrijema implements Serializable{

	private static final long serialVersionUID = 1L;
	private VozilaOprema oprema;
	private int kolicina;

	public StavkaPrijema() {
		// TODO Auto-generated constructor stub
	}
	
	public StavkaPrijema(VozilaOprema opr, int kol) {
		oprema = opr;
		kolicina = kol;
	}

	public VozilaOprema getOprema() {
		return oprema;
	}

	public void setOprema(VozilaOprema oprema) {
		this.oprema = oprema;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
}

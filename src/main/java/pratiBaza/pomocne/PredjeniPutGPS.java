package pratiBaza.pomocne;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PredjeniPutGPS implements Serializable{

	private static final long serialVersionUID = 1L;
	private String objekatNaziv;
	private float pocetna, kraj, razlika;
	
	public PredjeniPutGPS(String objekatNaziv, float pocetna, float kraj, float razlika) {
		this.objekatNaziv = objekatNaziv;
		this.pocetna = pocetna;
		this.kraj = kraj;
		this.razlika = razlika;
	}

	public String getObjekatNaziv() {
		return objekatNaziv;
	}

	public void setObjekatNaziv(String objekatNaziv) {
		this.objekatNaziv = objekatNaziv;
	}

	public float getPocetna() {
		return pocetna;
	}

	public void setPocetna(float pocetna) {
		this.pocetna = pocetna;
	}

	public float getKraj() {
		return kraj;
	}

	public void setKraj(float kraj) {
		this.kraj = kraj;
	}

	public float getRazlika() {
		return razlika;
	}

	public void setRazlika(float razlika) {
		this.razlika = razlika;
	}

}

package pratiBaza.pomocne;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class StanjeOBD implements Serializable{

	private static final long serialVersionUID = 1L;
	private String objekatNaziv;
	private int ukupnoKm;
	private float ukupnoGorivo, ukupnoVreme, potrosnja;

	public StanjeOBD() {
		
	}
	
	public StanjeOBD(String objekatNaziv, int ukupnoKm, float ukupnoGorivo, float ukupnoVreme) {
		this.objekatNaziv = objekatNaziv;
		this.ukupnoKm = ukupnoKm;
		this.ukupnoGorivo = ukupnoGorivo;
		this.ukupnoVreme = ukupnoVreme;
		this.potrosnja = (ukupnoGorivo * 100)/ukupnoKm;
	}

	public String getObjekatNaziv() {
		return objekatNaziv;
	}

	public void setObjekatNaziv(String objekatNaziv) {
		this.objekatNaziv = objekatNaziv;
	}

	public int getUkupnoKm() {
		return ukupnoKm;
	}

	public void setUkupnoKm(int ukupnoKm) {
		this.ukupnoKm = ukupnoKm;
	}

	public float getUkupnoGorivo() {
		return ukupnoGorivo;
	}

	public void setUkupnoGorivo(float ukupnoGorivo) {
		this.ukupnoGorivo = ukupnoGorivo;
	}

	public float getUkupnoVreme() {
		return ukupnoVreme;
	}

	public void setUkupnoVreme(float ukupnoVreme) {
		this.ukupnoVreme = ukupnoVreme;
	}

	public float getPotrosnja() {
		return potrosnja;
	}

	public void setPotrosnja(float potrosnja) {
		this.potrosnja = potrosnja;
	}
	
}

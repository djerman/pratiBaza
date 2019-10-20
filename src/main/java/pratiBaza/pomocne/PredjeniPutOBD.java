package pratiBaza.pomocne;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PredjeniPutOBD implements Serializable{

	private static final long serialVersionUID = 1L;
	private String objekatNaziv;
	private int pocetakKm, krajKm, razlikaKm;
	private float pocetakGorivo, krajGorivo, razlikaGorivo, prosPotrosnja;
	
	public PredjeniPutOBD(String objekatNaziv, int pocetnaKm, int krajKm, int razlikaKm, float pocetakGorivo, float krajGorivo, float razlikaGorivo, float prosPotrosnja) {
		this.objekatNaziv = objekatNaziv;
		this.pocetakKm = pocetnaKm;
		this.krajKm = krajKm;
		this.razlikaKm = razlikaKm;
		this.pocetakGorivo = pocetakGorivo;
		this.krajGorivo = krajGorivo;
		this.razlikaGorivo = razlikaGorivo;
		this.prosPotrosnja = prosPotrosnja;
	}

	public String getObjekatNaziv() {
		return objekatNaziv;
	}

	public void setObjekatNaziv(String objekatNaziv) {
		this.objekatNaziv = objekatNaziv;
	}

	public int getPocetakKm() {
		return pocetakKm;
	}

	public void setPocetakKm(int pocetakKm) {
		this.pocetakKm = pocetakKm;
	}

	public int getKrajKm() {
		return krajKm;
	}

	public void setKrajKm(int krajKm) {
		this.krajKm = krajKm;
	}

	public int getRazlikaKm() {
		return razlikaKm;
	}

	public void setRazlikaKm(int razlikaKm) {
		this.razlikaKm = razlikaKm;
	}

	public float getPocetakGorivo() {
		return pocetakGorivo;
	}

	public void setPocetakGorivo(float pocetakGorivo) {
		this.pocetakGorivo = pocetakGorivo;
	}

	public float getKrajGorivo() {
		return krajGorivo;
	}

	public void setKrajGorivo(float krajGorivo) {
		this.krajGorivo = krajGorivo;
	}

	public float getRazlikaGorivo() {
		return razlikaGorivo;
	}

	public void setRazlikaGorivo(float razlikaGorivo) {
		this.razlikaGorivo = razlikaGorivo;
	}

	public float getProsPotrosnja() {
		return prosPotrosnja;
	}

	public void setProsPotrosnja(float prosPotrosnja) {
		this.prosPotrosnja = prosPotrosnja;
	}
	
}

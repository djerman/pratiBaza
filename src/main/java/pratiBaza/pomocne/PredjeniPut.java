package pratiBaza.pomocne;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PredjeniPut implements Serializable{

	private static final long serialVersionUID = 1L;
	private String objekatNaziv;
	private float virtualOdo;
	private float ukupnoKm;
	private float ukupnoGorivo;
	private float prosPotGps;
	private float prosPotr;
	
	public PredjeniPut(String objekatNaziv, float virtualOdo, float ukupnoKm, float ukupnoGorivo, float prosPotGps, float prosPotr) {
		this.objekatNaziv = objekatNaziv;
		this.virtualOdo = virtualOdo;
		this.ukupnoKm = ukupnoKm;
		this.ukupnoGorivo = ukupnoGorivo;
		this.prosPotGps = prosPotGps;
		this.prosPotr = prosPotr;
	}

	public String getObjekatNaziv() {
		return objekatNaziv;
	}

	public void setObjekatNaziv(String objekatNaziv) {
		this.objekatNaziv = objekatNaziv;
	}

	public float getVirtualOdo() {
		return virtualOdo;
	}

	public void setVirtualOdo(float virtualOdo) {
		this.virtualOdo = virtualOdo;
	}

	public float getUkupnoKm() {
		return ukupnoKm;
	}

	public void setUkupnoKm(float ukupnoKm) {
		this.ukupnoKm = ukupnoKm;
	}

	public float getUkupnoGorivo() {
		return ukupnoGorivo;
	}

	public void setUkupnoGorivo(float ukupnoGorivo) {
		this.ukupnoGorivo = ukupnoGorivo;
	}

	public float getProsPotGps() {
		return prosPotGps;
	}

	public void setProsPotGps(float prosPotGps) {
		this.prosPotGps = prosPotGps;
	}

	public float getProsPotr() {
		return prosPotr;
	}

	public void setProsPotr(float prosPotr) {
		this.prosPotr = prosPotr;
	}
	
}

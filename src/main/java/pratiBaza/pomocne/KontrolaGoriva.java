package pratiBaza.pomocne;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class KontrolaGoriva implements Serializable{

	private static final long serialVersionUID = 1L;
	private String objekatNaziv;
	private String registracija;
	private float virtualOdo;
	private float ukupnoKm;
	private float ukupnoGorivo;
	private float prosPotGps;
	private float prosPotr;
	private float potrosnja;
	private float kolicina;
	private float gpsKolicina;
	private float odoKolicina;
	
	public KontrolaGoriva() {
		// TODO Auto-generated constructor stub
	}
	
	public KontrolaGoriva(String objekatNaziv, String registracija, float virtualOdo, float ukupnoKm, float ukupnoGorivo, 
			float prsPoGps, float prosPotr, float potrosnja, float kolicina, float gpsKolicina, float odoKolicina) {
		this.objekatNaziv = objekatNaziv;
		this.registracija = registracija;
		this.virtualOdo = virtualOdo;
		this.ukupnoKm = ukupnoKm;
		this.ukupnoGorivo = ukupnoGorivo;
		this.prosPotGps = prsPoGps;
		this.potrosnja = potrosnja;
		this.gpsKolicina = gpsKolicina;
		this.odoKolicina = odoKolicina;
	}

	public String getObjekatNaziv() {
		return objekatNaziv;
	}

	public void setObjekatNaziv(String objekatNaziv) {
		this.objekatNaziv = objekatNaziv;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
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

	public float getPotrosnja() {
		return potrosnja;
	}

	public void setPotrosnja(float potrosnja) {
		this.potrosnja = potrosnja;
	}

	public float getKolicina() {
		return kolicina;
	}

	public void setKolicina(float kolicina) {
		this.kolicina = kolicina;
	}

	public float getGpsKolicina() {
		return gpsKolicina;
	}

	public void setGpsKolicina(float gpsKolicina) {
		this.gpsKolicina = gpsKolicina;
	}

	public float getOdoKolicina() {
		return odoKolicina;
	}

	public void setOdoKolicina(float odoKolicina) {
		this.odoKolicina = odoKolicina;
	}

}

package pratiBaza.pomocne;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class GorivoSaCenama implements Serializable{

	private static final long serialVersionUID = 1L;
	private String objekatGb;
	private String mesto;
	private String markaTip;
	private String registracija;
	private float potrosnja;
	private float dozvoljenaProsPotrosnja;
	private float razlikaProsPotrosnje;
	private float predjeniKmGPS;
	private int predjeniKmObd;
	private float cenaGoriva;
	private float kolicinaPotrosenogGoriva;
	private float cenaUkupnoPotrosenogGoriva;
	private float kolicinaVisePotrosenogGoriva;
	private float cenaVisePotrosenogGoriva;

	public GorivoSaCenama() {
		// TODO Auto-generated constructor stub
	}
	
	public GorivoSaCenama(String objekatGb, String mesto, String markaTip, String registracija, float potrosnja, float dozvoljenaProsPotrosnja,
			float razlikaProsPotrosnje, float predjeniKmGPS, int predjeniKmObd, float cenaGoriva, float kolicinaPotrosenogGoriva,
			float cenaUkupnoPotrosenogGoriva, float kolicinaVisePotrosenogGoriva, float cenaVisePotrosenogGoriva) {
		this.objekatGb = objekatGb;
		this.mesto = mesto;
		this.markaTip = markaTip;
		this.registracija = registracija;
		this.potrosnja = potrosnja;
		this.dozvoljenaProsPotrosnja = dozvoljenaProsPotrosnja;
		this.razlikaProsPotrosnje = razlikaProsPotrosnje;
		this.predjeniKmGPS = predjeniKmGPS;
		this.predjeniKmObd = predjeniKmObd;
		this.cenaGoriva = cenaGoriva;
		this.kolicinaPotrosenogGoriva = kolicinaPotrosenogGoriva;
		this.cenaUkupnoPotrosenogGoriva = cenaUkupnoPotrosenogGoriva;
		this.kolicinaVisePotrosenogGoriva = kolicinaVisePotrosenogGoriva;
		this.cenaVisePotrosenogGoriva = cenaVisePotrosenogGoriva;
	}

	public String getObjekatGb() {
		return objekatGb;
	}

	public void setObjekatGb(String objekatGb) {
		this.objekatGb = objekatGb;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getMarkaTip() {
		return markaTip;
	}

	public void setMarkaTip(String markaTip) {
		this.markaTip = markaTip;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public float getPotrosnja() {
		return potrosnja;
	}

	public void setPotrosnja(float potrosnja) {
		this.potrosnja = potrosnja;
	}

	public float getDozvoljenaProsPotrosnja() {
		return dozvoljenaProsPotrosnja;
	}

	public void setDozvoljenaProsPotrosnja(float dozvoljenaProsPotrosnja) {
		this.dozvoljenaProsPotrosnja = dozvoljenaProsPotrosnja;
	}

	public float getRazlikaProsPotrosnje() {
		return razlikaProsPotrosnje;
	}

	public void setRazlikaProsPotrosnje(float razlikaProsPotrosnje) {
		this.razlikaProsPotrosnje = razlikaProsPotrosnje;
	}

	public float getPredjeniKmGPS() {
		return predjeniKmGPS;
	}

	public void setPredjeniKmGPS(float predjeniKmGPS) {
		this.predjeniKmGPS = predjeniKmGPS;
	}

	public int getPredjeniKmObd() {
		return predjeniKmObd;
	}

	public void setPredjeniKmObd(int predjeniKmObd) {
		this.predjeniKmObd = predjeniKmObd;
	}

	public float getCenaGoriva() {
		return cenaGoriva;
	}

	public void setCenaGoriva(float cenaGoriva) {
		this.cenaGoriva = cenaGoriva;
	}

	public float getKolicinaPotrosenogGoriva() {
		return kolicinaPotrosenogGoriva;
	}

	public void setKolicinaPotrosenogGoriva(float kolicinaPotrosenogGoriva) {
		this.kolicinaPotrosenogGoriva = kolicinaPotrosenogGoriva;
	}

	public float getCenaUkupnoPotrosenogGoriva() {
		return cenaUkupnoPotrosenogGoriva;
	}

	public void setCenaUkupnoPotrosenogGoriva(float cenaUkupnoPotrosenogGoriva) {
		this.cenaUkupnoPotrosenogGoriva = cenaUkupnoPotrosenogGoriva;
	}

	public float getKolicinaVisePotrosenogGoriva() {
		return kolicinaVisePotrosenogGoriva;
	}

	public void setKolicinaVisePotrosenogGoriva(float kolicinaVisePotrosenogGoriva) {
		this.kolicinaVisePotrosenogGoriva = kolicinaVisePotrosenogGoriva;
	}

	public float getCenaVisePotrosenogGoriva() {
		return cenaVisePotrosenogGoriva;
	}

	public void setCenaVisePotrosenogGoriva(float cenaVisePotrosenogGoriva) {
		this.cenaVisePotrosenogGoriva = cenaVisePotrosenogGoriva;
	}
	
}

package pratiBaza.pomocne;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class KontrolaTocenja implements Serializable{

	private static final long serialVersionUID = 1L;
	private String oznaka;
	private String registracija;
	private String markaTip;
	private int brojSipanja;
	private Date pocetak;
	private Date poslednjeSipanje;
	private float ukupno;
	private float gpsPut;
	private int obdPut;
	private float prosGps;
	private float prosObd;

	public KontrolaTocenja() {
		// TODO Auto-generated constructor stub
	}
	
	public KontrolaTocenja(String oznaka, String registracija, String markaTip, int brojSipanja, Date pocetak, Date poslednjeSipanje,
			float ukupno, float gpsPut, int obdPut, float prosGps, float prosObd) {
		this.oznaka = oznaka;
		this.registracija = registracija;
		this.markaTip = markaTip;
		this.brojSipanja = brojSipanja;
		this.pocetak = pocetak;
		this.poslednjeSipanje = poslednjeSipanje;
		this.ukupno = ukupno;
		this.gpsPut = gpsPut;
		this.obdPut = obdPut;
		this.prosGps = prosGps;
		this.prosObd = prosObd;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public String getMarkaTip() {
		return markaTip;
	}

	public void setMarkaTip(String markaTip) {
		this.markaTip = markaTip;
	}

	public int getBrojSipanja() {
		return brojSipanja;
	}

	public void setBrojSipanja(int brojSipanja) {
		this.brojSipanja = brojSipanja;
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getPoslednjeSipanje() {
		return poslednjeSipanje;
	}

	public void setPoslednjeSipanje(Date poslednjeSipanje) {
		this.poslednjeSipanje = poslednjeSipanje;
	}

	public float getUkupno() {
		return ukupno;
	}

	public void setUkupno(float ukupno) {
		this.ukupno = ukupno;
	}

	public float getGpsPut() {
		return gpsPut;
	}

	public void setGpsPut(float gpsPut) {
		this.gpsPut = gpsPut;
	}

	public int getObdPut() {
		return obdPut;
	}

	public void setObdPut(int obdPut) {
		this.obdPut = obdPut;
	}

	public float getProsGps() {
		return prosGps;
	}

	public void setProsGps(float prosGps) {
		this.prosGps = prosGps;
	}

	public float getProsObd() {
		return prosObd;
	}

	public void setProsObd(float prosObd) {
		this.prosObd = prosObd;
	}
	
}

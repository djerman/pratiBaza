package pratiBaza.pomocne;

import java.io.Serializable;
import java.util.Date;

public class StajanjeMirovanje implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date pocetak, kraj;
	private String objekat, opis, vremeStajanja, vremeMirovanja;
	
	public StajanjeMirovanje() {
		// TODO Auto-generated constructor stub
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

	public String getObjekat() {
		return objekat;
	}

	public void setObjekat(String objekat) {
		this.objekat = objekat;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getVremeStajanja() {
		return vremeStajanja;
	}

	public void setVremeStajanja(String vremeStajanja) {
		this.vremeStajanja = vremeStajanja;
	}

	public String getVremeMirovanja() {
		return vremeMirovanja;
	}

	public void setVremeMirovanja(String vremeMirovanja) {
		this.vremeMirovanja = vremeMirovanja;
	}
	
}

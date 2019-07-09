package pratiBaza.pomocne;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.SqlResultSetMapping;

@Embeddable
@SqlResultSetMapping(name = "radnoVremeGPS")
public class RadnoVremePutGPS implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date datum, pocetak, kraj;
	private Float maxBrzina, predjeniPut;

	public RadnoVremePutGPS() {
		
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
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

	public Float getMaxBrzina() {
		return maxBrzina;
	}

	public void setMaxBrzina(Float maxBrzina) {
		this.maxBrzina = maxBrzina;
	}

	public Float getPredjeniPut() {
		return predjeniPut;
	}

	public void setPredjeniPut(Float predjeniPut) {
		this.predjeniPut = predjeniPut;
	}
	
}

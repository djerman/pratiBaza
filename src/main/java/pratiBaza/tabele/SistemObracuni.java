package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="sistemObracuni")
@NamedQuery(name="SistemObracuni.findAll", query="SELECT s FROM SistemObracuni s")
public class SistemObracuni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private int brzinaMaks;

	private int brzinaProsecna;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private float gpsKm;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private int obdKm;

	private int objekatId;

	private float odoGPS;

	private int odoObd;

	private float opterecenjeMaks;

	private float opterecenjeProsek;

	private float potrosnjaProsek;

	private float potrosnjaUkupna;

	private int rpmMaks;

	private int rpmProsek;

	private int tempMaks;

	private int tempProsek;

	private int version;

	public SistemObracuni() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrzinaMaks() {
		return this.brzinaMaks;
	}

	public void setBrzinaMaks(int brzinaMaks) {
		this.brzinaMaks = brzinaMaks;
	}

	public int getBrzinaProsecna() {
		return this.brzinaProsecna;
	}

	public void setBrzinaProsecna(int brzinaProsecna) {
		this.brzinaProsecna = brzinaProsecna;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public float getGpsKm() {
		return this.gpsKm;
	}

	public void setGpsKm(float gpsKm) {
		this.gpsKm = gpsKm;
	}

	public Timestamp getIzmenjeno() {
		return this.izmenjeno;
	}

	public void setIzmenjeno(Timestamp izmenjeno) {
		this.izmenjeno = izmenjeno;
	}

	public Timestamp getKreirano() {
		return this.kreirano;
	}

	public void setKreirano(Timestamp kreirano) {
		this.kreirano = kreirano;
	}

	public int getObdKm() {
		return this.obdKm;
	}

	public void setObdKm(int obdKm) {
		this.obdKm = obdKm;
	}

	public int getObjekatId() {
		return this.objekatId;
	}

	public void setObjekatId(int objekatId) {
		this.objekatId = objekatId;
	}

	public float getOdoGPS() {
		return this.odoGPS;
	}

	public void setOdoGPS(float odoGPS) {
		this.odoGPS = odoGPS;
	}

	public int getOdoObd() {
		return this.odoObd;
	}

	public void setOdoObd(int odoObd) {
		this.odoObd = odoObd;
	}

	public float getOpterecenjeMaks() {
		return this.opterecenjeMaks;
	}

	public void setOpterecenjeMaks(float opterecenjeMaks) {
		this.opterecenjeMaks = opterecenjeMaks;
	}

	public float getOpterecenjeProsek() {
		return this.opterecenjeProsek;
	}

	public void setOpterecenjeProsek(float opterecenjeProsek) {
		this.opterecenjeProsek = opterecenjeProsek;
	}

	public float getPotrosnjaProsek() {
		return this.potrosnjaProsek;
	}

	public void setPotrosnjaProsek(float potrosnjaProsek) {
		this.potrosnjaProsek = potrosnjaProsek;
	}

	public float getPotrosnjaUkupna() {
		return this.potrosnjaUkupna;
	}

	public void setPotrosnjaUkupna(float potrosnjaUkupna) {
		this.potrosnjaUkupna = potrosnjaUkupna;
	}

	public int getRpmMaks() {
		return this.rpmMaks;
	}

	public void setRpmMaks(int rpmMaks) {
		this.rpmMaks = rpmMaks;
	}

	public int getRpmProsek() {
		return this.rpmProsek;
	}

	public void setRpmProsek(int rpmProsek) {
		this.rpmProsek = rpmProsek;
	}

	public int getTempMaks() {
		return this.tempMaks;
	}

	public void setTempMaks(int tempMaks) {
		this.tempMaks = tempMaks;
	}

	public int getTempProsek() {
		return this.tempProsek;
	}

	public void setTempProsek(int tempProsek) {
		this.tempProsek = tempProsek;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
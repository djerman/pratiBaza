package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the alarmiKorisnik database table.
 * 
 */
@Entity
@Table(name="alarmiKorisnik")
@NamedQuery(name="AlarmiKorisnik.findAll", query="SELECT a FROM AlarmiKorisnik a")
public class AlarmiKorisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte alarmiranje;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private byte pocetna;

	private byte pracenje;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;

	//bi-directional many-to-one association to Korisnici
	@ManyToOne
	@JoinColumn(name="korisnikId")
	private Korisnici korisnik;

	//bi-directional many-to-one association to SistemAlarmi
	@ManyToOne
	@JoinColumn(name="alarmId")
	private SistemAlarmi sistemAlarm;

	public AlarmiKorisnik() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte getAlarmiranje() {
		return this.alarmiranje;
	}

	public void setAlarmiranje(byte alarmiranje) {
		this.alarmiranje = alarmiranje;
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

	public byte getPocetna() {
		return this.pocetna;
	}

	public void setPocetna(byte pocetna) {
		this.pocetna = pocetna;
	}

	public byte getPracenje() {
		return this.pracenje;
	}

	public void setPracenje(byte pracenje) {
		this.pracenje = pracenje;
	}

	public SistemPretplatnici getSistemPretplatnici() {
		return this.sistemPretplatnici;
	}

	public void setSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		this.sistemPretplatnici = sistemPretplatnici;
	}

	public Korisnici getKorisnici() {
		return this.korisnik;
	}

	public void setKorisnici(Korisnici korisnici) {
		this.korisnik = korisnici;
	}

	public SistemAlarmi getSistemAlarmi() {
		return this.sistemAlarm;
	}

	public void setSistemAlarmi(SistemAlarmi sistemAlarmi) {
		this.sistemAlarm = sistemAlarmi;
	}

}
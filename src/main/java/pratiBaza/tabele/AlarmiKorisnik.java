package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="alarmiKorisnik")
@NamedQuery(name="AlarmiKorisnik.findAll", query="SELECT a FROM AlarmiKorisnik a")
public class AlarmiKorisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private int version;
	
	private boolean alarmiranje;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private boolean pocetna;

	private boolean pracenje;
	
	private boolean izbrisan;

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
	
	//bi-directional many-to-one association to SistemAlarmi
	@ManyToOne
	@JoinColumn(name="objekatId")
	private Objekti objekti;


	public AlarmiKorisnik() {
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean getAlarmiranje() {
		return this.alarmiranje;
	}

	public void setAlarmiranje(boolean alarmiranje) {
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

	public Korisnici getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnici korisnik) {
		this.korisnik = korisnik;
	}

	public Objekti getObjekti() {
		return objekti;
	}

	public void setObjekti(Objekti objekti) {
		this.objekti = objekti;
	}

	public SistemAlarmi getSistemAlarm() {
		return sistemAlarm;
	}

	public void setSistemAlarm(SistemAlarmi sistemAlarm) {
		this.sistemAlarm = sistemAlarm;
	}

	public boolean isPocetna() {
		return pocetna;
	}

	public void setPocetna(boolean pocetna) {
		this.pocetna = pocetna;
	}

	public boolean isPracenje() {
		return pracenje;
	}

	public void setPracenje(boolean pracenje) {
		this.pracenje = pracenje;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
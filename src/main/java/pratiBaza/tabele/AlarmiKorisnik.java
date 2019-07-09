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
	
	private boolean aktivan;
	
	private boolean email;
	
	private boolean obavestenje;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;

	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;
	
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

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean email) {
		this.aktivan = email;
	}

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}

	public boolean isObavestenje() {
		return obavestenje;
	}

	public void setObavestenje(boolean obavestenje) {
		this.obavestenje = obavestenje;
	}

	public SistemAlarmi getSistemAlarm() {
		return sistemAlarm;
	}

	public void setSistemAlarm(SistemAlarmi sistemAlarm) {
		this.sistemAlarm = sistemAlarm;
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

	public Organizacije getOrganizacija() {
		return organizacija;
	}

	public void setOrganizacija(Organizacije organizacija) {
		this.organizacija = organizacija;
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

}
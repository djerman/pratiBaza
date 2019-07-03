package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="javljanja")
@NamedQuery(name="Javljanja.findAll", query="SELECT j FROM Javljanja j")
public class Javljanja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private int brzina;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datumVreme;

	private String eventData;

	private String ibutton;

	private Timestamp izmenjeno;

	private boolean kontakt;

	private Timestamp kreirano;

	private double lat;

	private double lon;

	private float pravac;

	private boolean valid;

	private int version;

	private float visina;
	
	private float virtualOdo;

	//bi-directional many-to-one association to SistemAlarmi
	@ManyToOne
	@JoinColumn(name="alarmId")
	private SistemAlarmi sistemAlarmi;

	//bi-directional many-to-one association to Obd
	@ManyToOne
	@JoinColumn(name="obdId")
	private Obd bd;
	
	//bi-directional many-to-one association to Obd
	@ManyToOne
	@JoinColumn(name="zona")
	private Zone zona;

	//bi-directional many-to-one association to Objekti
	@ManyToOne
	@JoinColumn(name="objekatId")
	private Objekti objekti;
	
	//bi-directional many-to-one association to Objekti
	@ManyToOne
	@JoinColumn(name="korisnikId")
	private Korisnici korisnik;

	public Javljanja() {
		
	}

	public String getNazivAlarma() {
		if(sistemAlarmi != null) {
			return sistemAlarmi.getNaziv();
		}else {
			return " ";
		}
	}
	
	public String getNazivObjekta() {
		if(objekti != null) {
			return objekti.getOznaka();
		}else {
			return " ";
		}
	}
	
	public String getNazivZone() {
		if(zona != null) {
			return zona.getNaziv();
		}else {
			return " ";
		}
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrzina() {
		return this.brzina;
	}

	public void setBrzina(int brzina) {
		this.brzina = brzina;
	}

	public Date getDatumVreme() {
		return this.datumVreme;
	}

	public void setDatumVreme(Date datumVreme) {
		this.datumVreme = datumVreme;
	}

	public String getEventData() {
		return this.eventData;
	}

	public void setEventData(String eventData) {
		this.eventData = eventData;
	}

	public String getIbutton() {
		return this.ibutton;
	}

	public void setIbutton(String ibutton) {
		this.ibutton = ibutton;
	}

	public Timestamp getIzmenjeno() {
		return this.izmenjeno;
	}

	public void setIzmenjeno(Timestamp izmenjeno) {
		this.izmenjeno = izmenjeno;
	}

	public boolean isKontakt() {
		return kontakt;
	}

	public void setKontakt(boolean kontakt) {
		this.kontakt = kontakt;
	}

	public Timestamp getKreirano() {
		return this.kreirano;
	}

	public void setKreirano(Timestamp kreirano) {
		this.kreirano = kreirano;
	}

	public double getLat() {
		return this.lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return this.lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public float getPravac() {
		return this.pravac;
	}

	public void setPravac(float pravac) {
		this.pravac = pravac;
	}
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public float getVisina() {
		return this.visina;
	}

	public void setVisina(float visina) {
		this.visina = visina;
	}

	public SistemAlarmi getSistemAlarmi() {
		return this.sistemAlarmi;
	}

	public void setSistemAlarmi(SistemAlarmi sistemAlarmi) {
		this.sistemAlarmi = sistemAlarmi;
	}

	public Objekti getObjekti() {
		return this.objekti;
	}

	public void setObjekti(Objekti objekti) {
		this.objekti = objekti;
	}

	public float getVirtualOdo() {
		return virtualOdo;
	}

	public void setVirtualOdo(float virtualOdo) {
		this.virtualOdo = virtualOdo;
	}

	public Korisnici getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnici korisnik) {
		this.korisnik = korisnik;
	}

	public Obd getBd() {
		return bd;
	}

	public void setBd(Obd bd) {
		this.bd = bd;
	}

	public Zone getZona() {
		return zona;
	}

	public void setZona(Zone zona) {
		this.zona = zona;
	}

}
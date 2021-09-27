package pratiBaza.tabele;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ce_javljanjaMirovanja")
@NamedQuery(name="JavljanjaMirovanja.findAll", query="SELECT j FROM JavljanjaMirovanja j")
public class JavljanjaMirovanja implements Serializable{
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
	@ManyToOne
	@JoinColumn(name="objekatId")
	private Objekti objekti;
	@ManyToOne
	@JoinColumn(name="alarmId")
	private SistemAlarmi sistemAlarmi;
	@ManyToOne
	@JoinColumn(name="zona")
	private Zone zona;
	@ManyToOne
	@JoinColumn(name="korisnikId")
	private Korisnici korisnik;

	public JavljanjaMirovanja() {
		
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
		if(eventData.length() > 255) {
			this.eventData = eventData.substring(0, 255);
			}else {
				this.eventData = eventData;
				}
		}

	public String getIbutton() {
		return this.ibutton;
		}

	public void setIbutton(String ibutton) {
		if(ibutton.length() > 100) {
			this.ibutton = ibutton.substring(0, 99);
			}else {
				this.ibutton = ibutton;
				}
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

	public boolean isKontakt() {
		return kontakt;
		}

	public void setKontakt(boolean kontakt) {
		this.kontakt = kontakt;
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

	public Objekti getObjekti() {
		return this.objekti;
		}

	public void setObjekti(Objekti objekti) {
		this.objekti = objekti;
		}

	public SistemAlarmi getSistemAlarmi() {
		return this.sistemAlarmi;
		}

	public void setSistemAlarmi(SistemAlarmi sistemAlarmi) {
		this.sistemAlarmi = sistemAlarmi;
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

	public Zone getZona() {
		return zona;
		}

	public void setZona(Zone zona) {
		this.zona = zona;
		}
	}

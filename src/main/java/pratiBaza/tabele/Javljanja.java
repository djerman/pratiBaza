package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="ce_javljanja")
@NamedQuery(name="Javljanja.findAll", query="SELECT j FROM Javljanja j")
public class Javljanja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int version;
	private boolean valid;
	@ManyToOne(/*cascade = CascadeType.MERGE*/)
	@JoinColumn(name="objekatId")
	private Objekti objekti;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumVreme;
	private double lon;
	private double lat;
	private float pravac;
	private float visina;
	private int brzina;
	private boolean kontakt;
	@ManyToOne(/*cascade = CascadeType.MERGE*/)
	@JoinColumn(name="alarmId")
	private SistemAlarmi sistemAlarmi;
	private float virtualOdo;
	private String eventData;
	@ManyToOne
	@JoinColumn(name="zona")
	private Zone zona;
	private String ibutton;
	@ManyToOne(/*cascade = CascadeType.MERGE*/)
	@JoinColumn(name="korisnikId")
	private Korisnici korisnik;
	private Timestamp izmenjeno;
	private Timestamp kreirano;

	public Javljanja() {
		
	}

	public String getNazivAlarma() {
		SistemAlarmi alarm = this.getSistemAlarmi();
		if(alarm != null) {
			return alarm.getNaziv();
			}else {
				return " ";
				}
		}
	
	public String getNazivObjekta() {
		Objekti objekat = this.getObjekti();
		if(objekat != null) {
			return objekat.getOznaka();
			}else {
				return " ";
				}
		}
	
	public String getNazivZone() {
		Zone zonaGet = this.getZona();
		if(zonaGet != null) {
			return zonaGet.getNaziv();
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
		if(eventData.length() > 255) {
			this.eventData = eventData.substring(0, 254);
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
		//Hibernate.initialize(sistemAlarmi);
		return sistemAlarmi;
		}

	public void setSistemAlarmi(SistemAlarmi sistemAlarmi) {
		this.sistemAlarmi = sistemAlarmi;
		}

	public Objekti getObjekti() {
		//Hibernate.initialize(objekti);
		return objekti;
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

	/*public Obd getBd() {
		return obd;
	}

	public void setBd(Obd bd) {
		this.obd = bd;
	}**/

	public Zone getZona() {
		//Hibernate.initialize(zona);
		return zona;
		}

	public void setZona(Zone zona) {
		this.zona = zona;
		}
	
	}
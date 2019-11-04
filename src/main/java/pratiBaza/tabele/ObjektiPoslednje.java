package pratiBaza.tabele;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="dx_objektiPoslednje")
@NamedQuery(name="ObjektiPoslednje.findAll", query="SELECT o FROM ObjektiPoslednje o")
public class ObjektiPoslednje implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;
	
	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="objekatId")
	private Objekti objekti;
	
	private Date maliServis;
	
	private Date velikiServis;
	
	private Date registracije;
	
	private Timestamp izmenjeno;

	private Timestamp kreirano;
	
	private int maliKm;
	
	private int velikiKm;
	
	private int version;
	
	public ObjektiPoslednje() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Objekti getObjekti() {
		return objekti;
	}

	public void setObjekti(Objekti objekti) {
		this.objekti = objekti;
	}

	public Organizacije getOrganizacija() {
		return organizacija;
	}

	public void setOrganizacija(Organizacije organizacija) {
		this.organizacija = organizacija;
	}

	public SistemPretplatnici getSistemPretplatnici() {
		return sistemPretplatnici;
	}

	public void setSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		this.sistemPretplatnici = sistemPretplatnici;
	}

	public Date getMaliServis() {
		return maliServis;
	}

	public void setMaliServis(Date maliServis) {
		this.maliServis = maliServis;
	}

	public Date getVelikiServis() {
		return velikiServis;
	}

	public void setVelikiServis(Date velikiServis) {
		this.velikiServis = velikiServis;
	}

	public int getMaliKm() {
		return maliKm;
	}

	public void setMaliKm(int maliKm) {
		this.maliKm = maliKm;
	}

	public int getVelikiKm() {
		return velikiKm;
	}

	public void setVelikiKm(int velikiKm) {
		this.velikiKm = velikiKm;
	}

	public Date getRegistracije() {
		return registracije;
	}

	public void setRegistracije(Date registracije) {
		this.registracije = registracije;
	}

	public Timestamp getIzmenjeno() {
		return izmenjeno;
	}

	public void setIzmenjeno(Timestamp izmenjeno) {
		this.izmenjeno = izmenjeno;
	}

	public Timestamp getKreirano() {
		return kreirano;
	}

	public void setKreirano(Timestamp kreirano) {
		this.kreirano = kreirano;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}

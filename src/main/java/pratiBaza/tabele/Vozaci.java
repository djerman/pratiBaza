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
@Table(name="ci_vozaci")
@NamedQuery(name="Vozaci.findAll", query="SELECT v FROM Vozaci v")
public class Vozaci implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int version;
	
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
	private Korisnici korisnici;
	
	private String jmbg;
	
	@Temporal(TemporalType.DATE)
	private Date zaposlenOd;
	
	@Temporal(TemporalType.DATE)
	private Date zaposlenDo;
	
	private String prebivaliste;
	
	private Timestamp kreirano;
	
	private Timestamp izmenjeno;
	
	private boolean izbrisan;
	
	public Vozaci() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
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

	public SistemPretplatnici getSistemPretplatnici() {
		return sistemPretplatnici;
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

	public Korisnici getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(Korisnici korisnici) {
		this.korisnici = korisnici;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public Date getZaposlenOd() {
		return zaposlenOd;
	}

	public void setZaposlenOd(Date zaposlenOd) {
		this.zaposlenOd = zaposlenOd;
	}

	public Date getZaposlenDo() {
		return zaposlenDo;
	}

	public void setZaposlenDo(Date zaposlenDo) {
		this.zaposlenDo = zaposlenDo;
	}

	public String getPrebivaliste() {
		return prebivaliste;
	}

	public void setPrebivaliste(String prebivaliste) {
		this.prebivaliste = prebivaliste;
	}

	public Timestamp getKreirano() {
		return kreirano;
	}

	public void setKreirano(Timestamp kreirano) {
		this.kreirano = kreirano;
	}

	public Timestamp getIzmenjeno() {
		return izmenjeno;
	}

	public void setIzmenjeno(Timestamp izmenjeno) {
		this.izmenjeno = izmenjeno;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}
	
}

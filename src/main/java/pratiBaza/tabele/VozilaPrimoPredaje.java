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
@Table(name="ck_voziloPrimoPredaja")
@NamedQuery(name="VozilaPrimoPredaje.findAll", query="SELECT vpp FROM VozilaPrimoPredaje vpp")
public class VozilaPrimoPredaje implements Serializable{

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
	
	private String broj;
	
	@Temporal(TemporalType.DATE)
	private Date datum;
	
	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="voziloId")
	private Vozila vozilo;
	
	//bi-directional many-to-one association to Korisnici
	@ManyToOne
	@JoinColumn(name="vozacPredaja")
	private Vozaci vozacPredaja;
	
	//bi-directional many-to-one association to Korisnici
	@ManyToOne
	@JoinColumn(name="administrator")
	private Korisnici administrator;
	
	//bi-directional many-to-one association to Korisnici
	@ManyToOne
	@JoinColumn(name="vozacPrijem")
	private Vozaci vozacPrijem;
	
	private String komentar;
	
	private Timestamp kreirano;
	
	private Timestamp izmenjeno;
	
	private boolean izbrisan;
	
	public VozilaPrimoPredaje() {
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

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Vozila getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozila vozilo) {
		this.vozilo = vozilo;
	}

	public Vozaci getVozacPredaja() {
		return vozacPredaja;
	}

	public void setVozacPredaja(Vozaci vozacPredaja) {
		this.vozacPredaja = vozacPredaja;
	}

	public Korisnici getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Korisnici administrator) {
		this.administrator = administrator;
	}

	public Vozaci getVozacPrijem() {
		return vozacPrijem;
	}

	public void setVozacPrijem(Vozaci vozacPrijem) {
		this.vozacPrijem = vozacPrijem;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
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

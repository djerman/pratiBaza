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
@Table(name="cl_voziloNalog")
@NamedQuery(name="VozilaNalozi.findAll", query="SELECT vn FROM VozilaNalozi vn")
public class VozilaNalozi implements Serializable{

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
	
	private String brojNaloga;
	
	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="voziloId")
	private Objekti vozilo;
	
	private String odMesta;
	
	private String doMesta;
	
	private String medjuTacke;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ocekivaniPolazak;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ocekivaniDolazak;
	
	//bi-directional many-to-one association to Korisnici
	@ManyToOne
	@JoinColumn(name="vozacId")
	private Korisnici vozac;
	
	private String komentar;
	
	private Timestamp kreirano;
	
	private Timestamp izmenjeno;
	
	private boolean izbrisan;
	
	public VozilaNalozi() {
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

	public String getBrojNaloga() {
		return brojNaloga;
	}

	public void setBrojNaloga(String brojNaloga) {
		this.brojNaloga = brojNaloga;
	}


	public Objekti getVozilo() {
		return vozilo;
	}

	public void setVozilo(Objekti vozilo) {
		this.vozilo = vozilo;
	}

	public String getOdMesta() {
		return odMesta;
	}

	public void setOdMesta(String odMesta) {
		this.odMesta = odMesta;
	}

	public String getDoMesta() {
		return doMesta;
	}

	public void setDoMesta(String doMesta) {
		this.doMesta = doMesta;
	}

	
	public String getMedjuTacke() {
		return medjuTacke;
	}

	public void setMedjuTacke(String medjuTacke) {
		this.medjuTacke = medjuTacke;
	}

	public Date getOcekivaniPolazak() {
		return ocekivaniPolazak;
	}

	public void setOcekivaniPolazak(Date ocekivaniPolazak) {
		this.ocekivaniPolazak = ocekivaniPolazak;
	}

	public Date getOcekivaniDolazak() {
		return ocekivaniDolazak;
	}

	public void setOcekivaniDolazak(Date ocekivaniDolazak) {
		this.ocekivaniDolazak = ocekivaniDolazak;
	}

	public Korisnici getVozac() {
		return vozac;
	}

	public void setVozac(Korisnici vozac) {
		this.vozac = vozac;
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

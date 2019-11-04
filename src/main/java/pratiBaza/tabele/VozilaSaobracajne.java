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
@Table(name="dg_voziloSaobracajna")
@NamedQuery(name="VozilaSaobracajne.findAll", query="SELECT vs FROM VozilaSaobracajne vs")
public class VozilaSaobracajne implements Serializable{

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
	
	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="voziloId")
	private Vozila vozilo;
	
	private String brojSaobracajne;
	
	@Temporal(TemporalType.DATE)
	private Date datumIzdavanja;
	
	private String izdao;
	
	private String homologacija;
	
	private String sasija;
	
	private String brojMotora;
	
	private double snagaMotora;
	
	private int zapreminaMotora;
	
	private int zapreminaRezervoara;
	
	private int zapreminaRezervoaraAdBlue;
	
	private String boja;
	
	private String masa;
	
	private String ukupnaMasa;
	
	private String kategorija;
	
	private String nosivost;
	
	private int mestaSedenja;
	
	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="saobracajna2Id")
	private VozilaSaobracajne2 saobracajna2;
	
	private Timestamp izmenjeno;

	private Timestamp kreirano;
	
	private boolean izbrisan;
	
	public VozilaSaobracajne() {
		
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

	public Vozila getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozila vozilo) {
		this.vozilo = vozilo;
	}

	public String getBrojSaobracajne() {
		return brojSaobracajne;
	}

	public void setBrojSaobracajne(String brojSaobracajne) {
		this.brojSaobracajne = brojSaobracajne;
	}

	public Date getDatumIzdavanja() {
		return datumIzdavanja;
	}

	public void setDatumIzdavanja(Date datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}

	public String getIzdao() {
		return izdao;
	}

	public void setIzdao(String izdao) {
		this.izdao = izdao;
	}

	public String getHomologacija() {
		return homologacija;
	}

	public void setHomologacija(String homologacija) {
		this.homologacija = homologacija;
	}

	public String getSasija() {
		return sasija;
	}

	public void setSasija(String sasija) {
		this.sasija = sasija;
	}

	public String getBrojMotora() {
		return brojMotora;
	}

	public void setBrojMotora(String brojMotora) {
		this.brojMotora = brojMotora;
	}

	public double getSnagaMotora() {
		return snagaMotora;
	}

	public void setSnagaMotora(double snagaMotora) {
		this.snagaMotora = snagaMotora;
	}

	public int getZapreminaMotora() {
		return zapreminaMotora;
	}

	public void setZapreminaMotora(int zapreminaMotora) {
		this.zapreminaMotora = zapreminaMotora;
	}

	public int getZapreminaRezervoara() {
		return zapreminaRezervoara;
	}

	public void setZapreminaRezervoara(int zapreminaRezervoara) {
		this.zapreminaRezervoara = zapreminaRezervoara;
	}

	public int getZapreminaRezervoaraAdBlue() {
		return zapreminaRezervoaraAdBlue;
	}

	public void setZapreminaRezervoaraAdBlue(int zapreminaRezervoaraAdBlue) {
		this.zapreminaRezervoaraAdBlue = zapreminaRezervoaraAdBlue;
	}

	public String getBoja() {
		return boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}

	public String getMasa() {
		return masa;
	}

	public void setMasa(String masa) {
		this.masa = masa;
	}

	public String getUkupnaMasa() {
		return ukupnaMasa;
	}

	public void setUkupnaMasa(String ukupnaMasa) {
		this.ukupnaMasa = ukupnaMasa;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public String getNosivost() {
		return nosivost;
	}

	public void setNosivost(String nosivost) {
		this.nosivost = nosivost;
	}

	public int getMestaSedenja() {
		return mestaSedenja;
	}

	public void setMestaSedenja(int mestaSedenja) {
		this.mestaSedenja = mestaSedenja;
	}

	public VozilaSaobracajne2 getSaobracajna2() {
		return saobracajna2;
	}

	public void setSaobracajna2(VozilaSaobracajne2 saobracajna2) {
		this.saobracajna2 = saobracajna2;
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

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}
	
}

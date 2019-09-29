package pratiBaza.tabele;

import java.io.Serializable;
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
@Table(name="voziloSaobracajna2")
@NamedQuery(name="VozilaSaobracajne2.findAll", query="SELECT vss FROM VozilaSaobracajne2 vss")
public class VozilaSaobracajne2 implements Serializable{

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
	@JoinColumn(name="saobracajnaId")
	private VozilaSaobracajne saobracajna;
	
	String vlasnik, adresaVlasnika, jmbgVlasnika, korisnik, adresaKorisnika, dimenzijeGuma, pritisakGume, dimenzijeTovara, odnosSnagaMasa, 
	mestaStajanje, kupljenoDoniranoOd, nabavljenoPoRacunuSert, dobavljacDonator;
	
	private Timestamp izmenjeno;

	private Timestamp kreirano;
	
	private boolean izbrisan;
	
	public VozilaSaobracajne2() {
		
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

	public VozilaSaobracajne getSaobracajna() {
		return saobracajna;
	}

	public void setSaobracajna(VozilaSaobracajne saobracajna) {
		this.saobracajna = saobracajna;
	}

	public String getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
	}

	public String getAdresaVlasnika() {
		return adresaVlasnika;
	}

	public void setAdresaVlasnika(String adresaVlasnika) {
		this.adresaVlasnika = adresaVlasnika;
	}

	public String getJmbgVlasnika() {
		return jmbgVlasnika;
	}

	public void setJmbgVlasnika(String jmbgVlasnika) {
		this.jmbgVlasnika = jmbgVlasnika;
	}

	public String getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}

	public String getAdresaKorisnika() {
		return adresaKorisnika;
	}

	public void setAdresaKorisnika(String adresaKorisnika) {
		this.adresaKorisnika = adresaKorisnika;
	}

	public String getDimenzijeGuma() {
		return dimenzijeGuma;
	}

	public void setDimenzijeGuma(String dimenzijeGuma) {
		this.dimenzijeGuma = dimenzijeGuma;
	}

	public String getPritisakGume() {
		return pritisakGume;
	}

	public void setPritisakGume(String pritisakGume) {
		this.pritisakGume = pritisakGume;
	}

	public String getDimenzijeTovara() {
		return dimenzijeTovara;
	}

	public void setDimenzijeTovara(String dimenzijeTovara) {
		this.dimenzijeTovara = dimenzijeTovara;
	}

	public String getOdnosSnagaMasa() {
		return odnosSnagaMasa;
	}

	public void setOdnosSnagaMasa(String odnosSnagaMasa) {
		this.odnosSnagaMasa = odnosSnagaMasa;
	}

	public String getMestaStajanje() {
		return mestaStajanje;
	}

	public void setMestaStajanje(String mestaStajanje) {
		this.mestaStajanje = mestaStajanje;
	}

	public String getKupljenoDoniranoOd() {
		return kupljenoDoniranoOd;
	}

	public void setKupljenoDoniranoOd(String kupljenoDoniranoOd) {
		this.kupljenoDoniranoOd = kupljenoDoniranoOd;
	}

	public String getNabavljenoPoRacunuSert() {
		return nabavljenoPoRacunuSert;
	}

	public void setNabavljenoPoRacunuSert(String nabavljenoPoRacunuSert) {
		this.nabavljenoPoRacunuSert = nabavljenoPoRacunuSert;
	}

	public String getDobavljacDonator() {
		return dobavljacDonator;
	}

	public void setDobavljacDonator(String dobavljacDonator) {
		this.dobavljacDonator = dobavljacDonator;
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

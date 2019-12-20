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
@Table(name="dm_evidencijaVoznji")
@NamedQuery(name="EvidencijaVoznji.findAll", query="SELECT e FROM Sifre e")
public class EvidencijaVoznji implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int version;
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;
	private String voziloNalog, vozac, registracijaVozila, relacija, brojPutnogNaloga, preuzetoIz, vrstaRobe, magacin, otpremnica, sifra, program;
	private Timestamp datumVremePolaska, datumVremeDolaska, kreirano, izmenjeno; 
	private float pocetnaKm, zavrsnaKm, razlikaKm, potrosnja, gorivoCena, prevozCena, putniTroskovi, troskoviGoriva, prevozPutniTrosak, utrosenoLitara;
	private int dana, sati, kolicina;
	private boolean zakljucan;
	@ManyToOne
	@JoinColumn(name="korisnikId")
	private Korisnici uradio;
	
	public EvidencijaVoznji() {
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

	public String getVoziloNalog() {
		return voziloNalog;
	}

	public void setVoziloNalog(String voziloNalog) {
		this.voziloNalog = voziloNalog;
	}

	public String getVozac() {
		return vozac;
	}

	public void setVozac(String vozac) {
		this.vozac = vozac;
	}

	public String getRegistracijaVozila() {
		return registracijaVozila;
	}

	public void setRegistracijaVozila(String registracijaVozila) {
		this.registracijaVozila = registracijaVozila;
	}

	public String getRelacija() {
		return relacija;
	}

	public void setRelacija(String relacija) {
		this.relacija = relacija;
	}

	public String getBrojPutnogNaloga() {
		return brojPutnogNaloga;
	}

	public void setBrojPutnogNaloga(String brojPutnogNaloga) {
		this.brojPutnogNaloga = brojPutnogNaloga;
	}

	public String getPreuzetoIz() {
		return preuzetoIz;
	}

	public void setPreuzetoIz(String preuzetoIz) {
		this.preuzetoIz = preuzetoIz;
	}

	public String getVrstaRobe() {
		return vrstaRobe;
	}

	public void setVrstaRobe(String vrstaRobe) {
		this.vrstaRobe = vrstaRobe;
	}

	public String getMagacin() {
		return magacin;
	}

	public void setMagacin(String magacin) {
		this.magacin = magacin;
	}

	public String getOtpremnica() {
		return otpremnica;
	}

	public void setOtpremnica(String otpremnica) {
		this.otpremnica = otpremnica;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public Timestamp getDatumVremePolaska() {
		return datumVremePolaska;
	}

	public void setDatumVremePolaska(Timestamp datumVremePolaska) {
		this.datumVremePolaska = datumVremePolaska;
	}

	public Timestamp getDatumVremeDolaska() {
		return datumVremeDolaska;
	}

	public void setDatumVremeDolaska(Timestamp datumVremeDolaska) {
		this.datumVremeDolaska = datumVremeDolaska;
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

	public float getPocetnaKm() {
		return pocetnaKm;
	}

	public void setPocetnaKm(float pocetnaKm) {
		this.pocetnaKm = pocetnaKm;
	}

	public float getZavrsnaKm() {
		return zavrsnaKm;
	}

	public void setZavrsnaKm(float zavrsnaKm) {
		this.zavrsnaKm = zavrsnaKm;
	}

	public float getRazlikaKm() {
		return razlikaKm;
	}

	public void setRazlikaKm(float razlikaKm) {
		this.razlikaKm = razlikaKm;
	}

	public float getPotrosnja() {
		return potrosnja;
	}

	public void setPotrosnja(float potrosnja) {
		this.potrosnja = potrosnja;
	}

	public float getGorivoCena() {
		return gorivoCena;
	}

	public void setGorivoCena(float gorivoCena) {
		this.gorivoCena = gorivoCena;
	}

	public float getPrevozCena() {
		return prevozCena;
	}

	public void setPrevozCena(float prevozCena) {
		this.prevozCena = prevozCena;
	}

	public float getPutniTroskovi() {
		return putniTroskovi;
	}

	public void setPutniTroskovi(float putniTroskovi) {
		this.putniTroskovi = putniTroskovi;
	}

	public float getTroskoviGoriva() {
		return troskoviGoriva;
	}

	public void setTroskoviGoriva(float troskoviGoriva) {
		this.troskoviGoriva = troskoviGoriva;
	}

	public float getPrevozPutniTrosak() {
		return prevozPutniTrosak;
	}

	public void setPrevozPutniTrosak(float prevozPutniTrosak) {
		this.prevozPutniTrosak = prevozPutniTrosak;
	}

	public float getUtrosenoLitara() {
		return utrosenoLitara;
	}

	public void setUtrosenoLitara(float utrosenoLitara) {
		this.utrosenoLitara = utrosenoLitara;
	}

	public int getDana() {
		return dana;
	}

	public void setDana(int dana) {
		this.dana = dana;
	}

	public int getSati() {
		return sati;
	}

	public void setSati(int sati) {
		this.sati = sati;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public boolean isZakljucan() {
		return zakljucan;
	}

	public void setZakljucan(boolean zakljucan) {
		this.zakljucan = zakljucan;
	}

	public Korisnici getUradio() {
		return uradio;
	}

	public void setUradio(Korisnici uradio) {
		this.uradio = uradio;
	}
	
}

package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the korisnici database table.
 * 
 */
@Entity
@Table(name="korisnici")
@NamedQuery(name="Korisnici.findAll", query="SELECT k FROM Korisnici k")
public class Korisnici implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte admin;

	private byte aktivan;

	@Temporal(TemporalType.DATE)
	private Date aktivanDo;

	private String email;

	private String ibutton;

	private String ime;

	private Timestamp izmenjeno;

	private byte korisnik;

	private Timestamp kreirano;

	private String lozinka;

	private String mobilni;

	private String prezime;

	private byte sistem;

	private String slikaUrl;

	private String telefon;

	private int version;

	private byte vozac;

	//bi-directional many-to-one association to AlarmiKorisnik
	@OneToMany(mappedBy="korisnici")
	private List<AlarmiKorisnik> alarmiKorisniks;

	//bi-directional many-to-one association to GrupeZaposleni
	@OneToMany(mappedBy="korisnici")
	private List<GrupeKorisnici> grupeZaposlenis;

	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;

	//bi-directional many-to-one association to SistemSesije
	@OneToMany(mappedBy="korisnici")
	private List<SistemSesije> sistemSesijes;

	public Korisnici() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte getAdmin() {
		return this.admin;
	}

	public void setAdmin(byte admin) {
		this.admin = admin;
	}

	public byte getAktivan() {
		return this.aktivan;
	}

	public void setAktivan(byte aktivan) {
		this.aktivan = aktivan;
	}

	public Date getAktivanDo() {
		return this.aktivanDo;
	}

	public void setAktivanDo(Date aktivanDo) {
		this.aktivanDo = aktivanDo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIbutton() {
		return this.ibutton;
	}

	public void setIbutton(String ibutton) {
		this.ibutton = ibutton;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Timestamp getIzmenjeno() {
		return this.izmenjeno;
	}

	public void setIzmenjeno(Timestamp izmenjeno) {
		this.izmenjeno = izmenjeno;
	}

	public byte getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(byte korisnik) {
		this.korisnik = korisnik;
	}

	public Timestamp getKreirano() {
		return this.kreirano;
	}

	public void setKreirano(Timestamp kreirano) {
		this.kreirano = kreirano;
	}

	public String getLozinka() {
		return this.lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getMobilni() {
		return this.mobilni;
	}

	public void setMobilni(String mobilni) {
		this.mobilni = mobilni;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public byte getSistem() {
		return this.sistem;
	}

	public void setSistem(byte sistem) {
		this.sistem = sistem;
	}

	public String getSlikaUrl() {
		return this.slikaUrl;
	}

	public void setSlikaUrl(String slikaUrl) {
		this.slikaUrl = slikaUrl;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public byte getVozac() {
		return this.vozac;
	}

	public void setVozac(byte vozac) {
		this.vozac = vozac;
	}

	public List<AlarmiKorisnik> getAlarmiKorisniks() {
		return this.alarmiKorisniks;
	}

	public void setAlarmiKorisniks(List<AlarmiKorisnik> alarmiKorisniks) {
		this.alarmiKorisniks = alarmiKorisniks;
	}

	public AlarmiKorisnik addAlarmiKorisnik(AlarmiKorisnik alarmiKorisnik) {
		getAlarmiKorisniks().add(alarmiKorisnik);
		alarmiKorisnik.setKorisnici(this);

		return alarmiKorisnik;
	}

	public AlarmiKorisnik removeAlarmiKorisnik(AlarmiKorisnik alarmiKorisnik) {
		getAlarmiKorisniks().remove(alarmiKorisnik);
		alarmiKorisnik.setKorisnici(null);

		return alarmiKorisnik;
	}

	public List<GrupeKorisnici> getGrupeZaposlenis() {
		return this.grupeZaposlenis;
	}

	public void setGrupeZaposlenis(List<GrupeKorisnici> grupeZaposlenis) {
		this.grupeZaposlenis = grupeZaposlenis;
	}

	public GrupeKorisnici addGrupeZaposleni(GrupeKorisnici grupeZaposleni) {
		getGrupeZaposlenis().add(grupeZaposleni);
		grupeZaposleni.setKorisnici(this);

		return grupeZaposleni;
	}

	public GrupeKorisnici removeGrupeZaposleni(GrupeKorisnici grupeZaposleni) {
		getGrupeZaposlenis().remove(grupeZaposleni);
		grupeZaposleni.setKorisnici(null);

		return grupeZaposleni;
	}

	public Organizacije getOrganizacija() {
		return this.organizacija;
	}

	public void setOrganizacija(Organizacije organizacija) {
		this.organizacija = organizacija;
	}

	public SistemPretplatnici getSistemPretplatnici() {
		return this.sistemPretplatnici;
	}

	public void setSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		this.sistemPretplatnici = sistemPretplatnici;
	}

	public List<SistemSesije> getSistemSesijes() {
		return this.sistemSesijes;
	}

	public void setSistemSesijes(List<SistemSesije> sistemSesijes) {
		this.sistemSesijes = sistemSesijes;
	}

	public SistemSesije addSistemSesije(SistemSesije sistemSesije) {
		getSistemSesijes().add(sistemSesije);
		sistemSesije.setKorisnici(this);

		return sistemSesije;
	}

	public SistemSesije removeSistemSesije(SistemSesije sistemSesije) {
		getSistemSesijes().remove(sistemSesije);
		sistemSesije.setKorisnici(null);

		return sistemSesije;
	}

}
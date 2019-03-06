package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the sistemPretplatnici database table.
 * 
 */
@Entity
@Table(name="sistemPretplatnici")
@NamedQuery(name="SistemPretplatnici.findAll", query="SELECT s FROM SistemPretplatnici s")
public class SistemPretplatnici implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte aktivan;

	@Temporal(TemporalType.DATE)
	private Date aktivanDo;

	private String apiKey;

	private String email;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private String naziv;

	private byte podrazumevani;

	private byte tip;

	private int version;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to AlarmiKorisnik
	@OneToMany(mappedBy="sistemPretplatnici")
	private List<AlarmiKorisnik> alarmiKorisniks;

	//bi-directional many-to-one association to Grupe
	@OneToMany(mappedBy="sistemPretplatnici")
	private List<Grupe> grupes;

	//bi-directional many-to-one association to GrupeObjekti
	@OneToMany(mappedBy="sistemPretplatnici")
	private List<GrupeObjekti> grupeObjektis;

	//bi-directional many-to-one association to GrupeZaposleni
	@OneToMany(mappedBy="sistemPretplatnici")
	private List<GrupeKorisnici> grupeZaposlenis;

	//bi-directional many-to-one association to Korisnici
	@OneToMany(mappedBy="sistemPretplatnici")
	private List<Korisnici> korisnicis;

	//bi-directional many-to-one association to Objekti
	@OneToMany(mappedBy="sistemPretplatnici")
	private List<Objekti> objektis;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="nadzorId")
	private SistemPretplatnici sistemPretplatnici;

	//bi-directional many-to-one association to SistemPretplatnici
	@OneToMany(mappedBy="sistemPretplatnici")
	private List<SistemPretplatnici> sistemPretplatnicis;

	//bi-directional many-to-one association to Uredjaji
	@OneToMany(mappedBy="sistemPretplatnici")
	private List<Uredjaji> uredjajis;

	//bi-directional many-to-one association to Zone
	@OneToMany(mappedBy="sistemPretplatnici")
	private List<Zone> zones;

	public SistemPretplatnici() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getApiKey() {
		return this.apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public byte getPodrazumevani() {
		return this.podrazumevani;
	}

	public void setPodrazumevani(byte podrazumevani) {
		this.podrazumevani = podrazumevani;
	}

	public byte getTip() {
		return this.tip;
	}

	public void setTip(byte tip) {
		this.tip = tip;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<AlarmiKorisnik> getAlarmiKorisniks() {
		return this.alarmiKorisniks;
	}

	public void setAlarmiKorisniks(List<AlarmiKorisnik> alarmiKorisniks) {
		this.alarmiKorisniks = alarmiKorisniks;
	}

	public AlarmiKorisnik addAlarmiKorisnik(AlarmiKorisnik alarmiKorisnik) {
		getAlarmiKorisniks().add(alarmiKorisnik);
		alarmiKorisnik.setSistemPretplatnici(this);

		return alarmiKorisnik;
	}

	public AlarmiKorisnik removeAlarmiKorisnik(AlarmiKorisnik alarmiKorisnik) {
		getAlarmiKorisniks().remove(alarmiKorisnik);
		alarmiKorisnik.setSistemPretplatnici(null);

		return alarmiKorisnik;
	}

	public List<Grupe> getGrupes() {
		return this.grupes;
	}

	public void setGrupes(List<Grupe> grupes) {
		this.grupes = grupes;
	}

	public Grupe addGrupe(Grupe grupe) {
		getGrupes().add(grupe);
		grupe.setSistemPretplatnici(this);

		return grupe;
	}

	public Grupe removeGrupe(Grupe grupe) {
		getGrupes().remove(grupe);
		grupe.setSistemPretplatnici(null);

		return grupe;
	}

	public List<GrupeObjekti> getGrupeObjektis() {
		return this.grupeObjektis;
	}

	public void setGrupeObjektis(List<GrupeObjekti> grupeObjektis) {
		this.grupeObjektis = grupeObjektis;
	}

	public GrupeObjekti addGrupeObjekti(GrupeObjekti grupeObjekti) {
		getGrupeObjektis().add(grupeObjekti);
		grupeObjekti.setSistemPretplatnici(this);

		return grupeObjekti;
	}

	public GrupeObjekti removeGrupeObjekti(GrupeObjekti grupeObjekti) {
		getGrupeObjektis().remove(grupeObjekti);
		grupeObjekti.setSistemPretplatnici(null);

		return grupeObjekti;
	}

	public List<GrupeKorisnici> getGrupeZaposlenis() {
		return this.grupeZaposlenis;
	}

	public void setGrupeZaposlenis(List<GrupeKorisnici> grupeZaposlenis) {
		this.grupeZaposlenis = grupeZaposlenis;
	}

	public GrupeKorisnici addGrupeZaposleni(GrupeKorisnici grupeZaposleni) {
		getGrupeZaposlenis().add(grupeZaposleni);
		grupeZaposleni.setSistemPretplatnici(this);

		return grupeZaposleni;
	}

	public GrupeKorisnici removeGrupeZaposleni(GrupeKorisnici grupeZaposleni) {
		getGrupeZaposlenis().remove(grupeZaposleni);
		grupeZaposleni.setSistemPretplatnici(null);

		return grupeZaposleni;
	}

	public List<Korisnici> getKorisnicis() {
		return this.korisnicis;
	}

	public void setKorisnicis(List<Korisnici> korisnicis) {
		this.korisnicis = korisnicis;
	}

	public Korisnici addKorisnici(Korisnici korisnici) {
		getKorisnicis().add(korisnici);
		korisnici.setSistemPretplatnici(this);

		return korisnici;
	}

	public Korisnici removeKorisnici(Korisnici korisnici) {
		getKorisnicis().remove(korisnici);
		korisnici.setSistemPretplatnici(null);

		return korisnici;
	}

	public List<Objekti> getObjektis() {
		return this.objektis;
	}

	public void setObjektis(List<Objekti> objektis) {
		this.objektis = objektis;
	}

	public Objekti addObjekti(Objekti objekti) {
		getObjektis().add(objekti);
		objekti.setSistemPretplatnici(this);

		return objekti;
	}

	public Objekti removeObjekti(Objekti objekti) {
		getObjektis().remove(objekti);
		objekti.setSistemPretplatnici(null);

		return objekti;
	}

	public SistemPretplatnici getSistemPretplatnici() {
		return this.sistemPretplatnici;
	}

	public void setSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		this.sistemPretplatnici = sistemPretplatnici;
	}

	public List<SistemPretplatnici> getSistemPretplatnicis() {
		return this.sistemPretplatnicis;
	}

	public void setSistemPretplatnicis(List<SistemPretplatnici> sistemPretplatnicis) {
		this.sistemPretplatnicis = sistemPretplatnicis;
	}

	public SistemPretplatnici addSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		getSistemPretplatnicis().add(sistemPretplatnici);
		sistemPretplatnici.setSistemPretplatnici(this);

		return sistemPretplatnici;
	}

	public SistemPretplatnici removeSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		getSistemPretplatnicis().remove(sistemPretplatnici);
		sistemPretplatnici.setSistemPretplatnici(null);

		return sistemPretplatnici;
	}

	public List<Uredjaji> getUredjajis() {
		return this.uredjajis;
	}

	public void setUredjajis(List<Uredjaji> uredjajis) {
		this.uredjajis = uredjajis;
	}

	public Uredjaji addUredjaji(Uredjaji uredjaji) {
		getUredjajis().add(uredjaji);
		uredjaji.setSistemPretplatnici(this);

		return uredjaji;
	}

	public Uredjaji removeUredjaji(Uredjaji uredjaji) {
		getUredjajis().remove(uredjaji);
		uredjaji.setSistemPretplatnici(null);

		return uredjaji;
	}

	public List<Zone> getZones() {
		return this.zones;
	}

	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

	public Zone addZone(Zone zone) {
		getZones().add(zone);
		zone.setSistemPretplatnici(this);

		return zone;
	}

	public Zone removeZone(Zone zone) {
		getZones().remove(zone);
		zone.setSistemPretplatnici(null);

		return zone;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
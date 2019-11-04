package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="ac_organizacije")
@NamedQuery(name="Organizacije.findAll", query="SELECT o FROM Organizacije o")
public class Organizacije implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private int version;
	
	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private String naziv;

	private String opis;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;
	
	private boolean aktivan;
	
	private boolean izbrisan;
	/*
	//bi-directional many-to-one association to GrupeObjekti
	@OneToMany(mappedBy="organizacije")
	private List<GrupeObjekti> grupeObjektis;

	//bi-directional many-to-one association to GrupeZaposleni
	@OneToMany(mappedBy="organizacije")
	private List<GrupeKorisnici> grupeZaposlenis;

	//bi-directional many-to-one association to Korisnici
	@OneToMany(mappedBy="organizacije")
	private List<Korisnici> korisnicis;

	//bi-directional many-to-one association to Objekti
	@OneToMany(mappedBy="organizacije")
	private List<Objekti> objektis;

	//bi-directional many-to-one association to Zone
	@OneToMany(mappedBy="organizacije")
	private List<Zone> zones;**/

	public Organizacije() {
		
	}

	public Long getId() {
		return this.id;
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

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public SistemPretplatnici getSistemPretplatnici() {
		return sistemPretplatnici;
	}

	public void setSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		this.sistemPretplatnici = sistemPretplatnici;
	}
	
	/*
	public List<GrupeObjekti> getGrupeObjektis() {
		return this.grupeObjektis;
	}

	public void setGrupeObjektis(List<GrupeObjekti> grupeObjektis) {
		this.grupeObjektis = grupeObjektis;
	}
	
	public GrupeObjekti addGrupeObjekti(GrupeObjekti grupeObjekti) {
		getGrupeObjektis().add(grupeObjekti);
		grupeObjekti.setOrganizacija(this);

		return grupeObjekti;
	}

	public GrupeObjekti removeGrupeObjekti(GrupeObjekti grupeObjekti) {
		getGrupeObjektis().remove(grupeObjekti);
		grupeObjekti.setOrganizacija(null);

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
		grupeZaposleni.setOrganizacija(this);

		return grupeZaposleni;
	}

	public GrupeKorisnici removeGrupeZaposleni(GrupeKorisnici grupeZaposleni) {
		getGrupeZaposlenis().remove(grupeZaposleni);
		grupeZaposleni.setOrganizacija(null);

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
		korisnici.setOrganizacija(this);

		return korisnici;
	}

	public Korisnici removeKorisnici(Korisnici korisnici) {
		getKorisnicis().remove(korisnici);
		korisnici.setOrganizacija(null);

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
		objekti.setOrganizacija(this);

		return objekti;
	}

	public Objekti removeObjekti(Objekti objekti) {
		getObjektis().remove(objekti);
		objekti.setOrganizacija(null);

		return objekti;
	}

	public List<Zone> getZones() {
		return this.zones;
	}

	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

	public Zone addZone(Zone zone) {
		getZones().add(zone);
		zone.setOrganizacija(this);

		return zone;
	}

	public Zone removeZone(Zone zone) {
		getZones().remove(zone);
		zone.setOrganizacija(null);

		return zone;
	}
	**/
}
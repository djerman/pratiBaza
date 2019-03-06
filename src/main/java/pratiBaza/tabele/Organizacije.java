package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the organizacija database table.
 * 
 */
@Entity
@Table(name="organizacije")
@NamedQuery(name="Organizacije.findAll", query="SELECT o FROM Organizacije o")
public class Organizacije implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private String naziv;

	private String opis;

	private BigInteger pretplatnikId;
	
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

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public BigInteger getPretplatnikId() {
		return this.pretplatnikId;
	}

	public void setPretplatnikId(BigInteger pretplatnikId) {
		this.pretplatnikId = pretplatnikId;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
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
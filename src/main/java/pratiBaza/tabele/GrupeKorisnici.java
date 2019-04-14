package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="grupeKorisnici")
@NamedQuery(name="GrupeKorisnici.findAll", query="SELECT g FROM GrupeKorisnici g")
public class GrupeKorisnici implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GrupeKorisniciPK id;

	private Timestamp izmenjeno;

	private Timestamp kreirano;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="gkpretplatnikId")
	private SistemPretplatnici sistemPretplatnici;

	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="gkorganizacijaId")
	private Organizacije organizacija;

	//bi-directional many-to-one association to Grupe
	@ManyToOne
	@JoinColumn(name="gkgrupaId")
	private Grupe grupe;

	//bi-directional many-to-one association to Korisnici
	@ManyToOne
	@JoinColumn(name="gkkorisnikId")
	private Korisnici korisnici;

	public GrupeKorisnici() {
	}

	public GrupeKorisniciPK getId() {
		return this.id;
	}

	public void setId(GrupeKorisniciPK id) {
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

	public SistemPretplatnici getSistemPretplatnici() {
		return this.sistemPretplatnici;
	}

	public void setSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		this.sistemPretplatnici = sistemPretplatnici;
	}

	public Organizacije getOrganizacija() {
		return this.organizacija;
	}

	public void setOrganizacija(Organizacije organizacija) {
		this.organizacija = organizacija;
	}

	public Grupe getGrupe() {
		return this.grupe;
	}

	public void setGrupe(Grupe grupe) {
		this.grupe = grupe;
	}

	public Korisnici getKorisnici() {
		return this.korisnici;
	}

	public void setKorisnici(Korisnici korisnici) {
		this.korisnici = korisnici;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
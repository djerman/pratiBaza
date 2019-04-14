package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="grupeObjekti")
@NamedQuery(name="GrupeObjekti.findAll", query="SELECT g FROM GrupeObjekti g")
public class GrupeObjekti implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GrupeObjektiPK id;

	private int version;
	
	private Timestamp izmenjeno;

	private Timestamp kreirano;
	
	private boolean izbrisan;
	
	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="goorganizacijaId")
	private Organizacije organizacija;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="gopretplatnikId")
	private SistemPretplatnici sistemPretplatnici;

	//bi-directional many-to-one association to Grupe
	@ManyToOne
	@JoinColumn(name="gogrupaId")
	private Grupe grupe;

	//bi-directional many-to-one association to Objekti
	@ManyToOne
	@JoinColumn(name="goobjekatId")
	private Objekti objekti;

	public GrupeObjekti() {
		
	}

	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	public GrupeObjektiPK getId() {
		return this.id;
	}

	public void setId(GrupeObjektiPK id) {
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

	public Grupe getGrupe() {
		return this.grupe;
	}

	public void setGrupe(Grupe grupe) {
		this.grupe = grupe;
	}

	public Objekti getObjekti() {
		return this.objekti;
	}

	public void setObjekti(Objekti objekti) {
		this.objekti = objekti;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the grupeObjekti database table.
 * 
 */
@Entity
@Table(name="grupeObjekti")
@NamedQuery(name="GrupeObjekti.findAll", query="SELECT g FROM GrupeObjekti g")
public class GrupeObjekti implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GrupeObjektiPK id;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;

	//bi-directional many-to-one association to Grupe
	@ManyToOne
	@JoinColumn(name="grupaId")
	private Grupe grupe;

	//bi-directional many-to-one association to Objekti
	@ManyToOne
	@JoinColumn(name="objekatId")
	private Objekti objekti;

	public GrupeObjekti() {
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

}
package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the sistemUredjajiProizvodjac database table.
 * 
 */
@Entity
@Table(name="sistemUredjajiProizvodjac")
@NamedQuery(name="SistemUredjajiProizvodjac.findAll", query="SELECT s FROM SistemUredjajiProizvodjac s")
public class SistemUredjajiProizvodjac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String adresa;

	private String naziv;

	@Lob
	private String opis;

	private BigInteger version;

	//bi-directional many-to-one association to SistemUredjajiModeli
	@OneToMany(mappedBy="sistemUredjajiProizvodjac")
	private List<SistemUredjajiModeli> sistemUredjajiModelis;

	public SistemUredjajiProizvodjac() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
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

	public BigInteger getVersion() {
		return this.version;
	}

	public void setVersion(BigInteger version) {
		this.version = version;
	}

	public List<SistemUredjajiModeli> getSistemUredjajiModelis() {
		return this.sistemUredjajiModelis;
	}

	public void setSistemUredjajiModelis(List<SistemUredjajiModeli> sistemUredjajiModelis) {
		this.sistemUredjajiModelis = sistemUredjajiModelis;
	}

	public SistemUredjajiModeli addSistemUredjajiModeli(SistemUredjajiModeli sistemUredjajiModeli) {
		getSistemUredjajiModelis().add(sistemUredjajiModeli);
		sistemUredjajiModeli.setSistemUredjajiProizvodjac(this);

		return sistemUredjajiModeli;
	}

	public SistemUredjajiModeli removeSistemUredjajiModeli(SistemUredjajiModeli sistemUredjajiModeli) {
		getSistemUredjajiModelis().remove(sistemUredjajiModeli);
		sistemUredjajiModeli.setSistemUredjajiProizvodjac(null);

		return sistemUredjajiModeli;
	}

}
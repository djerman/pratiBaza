package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="sistemUredjajiModeli")
@NamedQuery(name="SistemUredjajiModeli.findAll", query="SELECT s FROM SistemUredjajiModeli s")
public class SistemUredjajiModeli implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String naziv;

	private boolean obd;

	private byte obracun;

	@Lob
	private String opis;

	private byte sim2;

	private int version;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to SistemUredjajiProizvodjac
	@ManyToOne
	@JoinColumn(name="proizvodjacId")
	private SistemUredjajiProizvodjac sistemUredjajiProizvodjac;

	/*//bi-directional many-to-one association to Uredjaji
	@OneToMany(mappedBy="sistemUredjajiModeli")
	private List<Uredjaji> uredjajis;**/

	public SistemUredjajiModeli() {
		
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public boolean isObd() {
		return obd;
	}

	public void setObd(boolean obd) {
		this.obd = obd;
	}

	public byte getObracun() {
		return this.obracun;
	}

	public void setObracun(byte obracun) {
		this.obracun = obracun;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public byte getSim2() {
		return this.sim2;
	}

	public void setSim2(byte sim2) {
		this.sim2 = sim2;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public SistemUredjajiProizvodjac getSistemUredjajiProizvodjac() {
		return this.sistemUredjajiProizvodjac;
	}

	public void setSistemUredjajiProizvodjac(SistemUredjajiProizvodjac sistemUredjajiProizvodjac) {
		this.sistemUredjajiProizvodjac = sistemUredjajiProizvodjac;
	}

	/*public List<Uredjaji> getUredjajis() {
		return this.uredjajis;
	}

	public void setUredjajis(List<Uredjaji> uredjajis) {
		this.uredjajis = uredjajis;
	}

	public Uredjaji addUredjaji(Uredjaji uredjaji) {
		getUredjajis().add(uredjaji);
		uredjaji.setSistemUredjajiModeli(this);

		return uredjaji;
	}

	public Uredjaji removeUredjaji(Uredjaji uredjaji) {
		getUredjajis().remove(uredjaji);
		uredjaji.setSistemUredjajiModeli(null);

		return uredjaji;
	}**/

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
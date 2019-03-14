package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the uredjaji database table.
 * 
 */
@Entity
@Table(name="uredjaji")
@NamedQuery(name="Uredjaji.findAll", query="SELECT u FROM Uredjaji u")
public class Uredjaji implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte aktivno;

	private Timestamp izmenjeno;

	private String kod;

	private Timestamp kreirano;

	private String opis;

	private String serijskiBr;

	//private BigInteger simId;
	@ManyToOne
	@JoinColumn(name="simId")
	private Sim sim;

	//private BigInteger simId2;
	@ManyToOne
	@JoinColumn(name="simId2")
	private Sim sim2;
	
	private int version;

	private byte zauzet;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to Objekti
	//@OneToMany(mappedBy="uredjaji")
	//private List<Objekti> objektis;

	//bi-directional many-to-one association to SistemUredjajiModeli
	@ManyToOne
	@JoinColumn(name="modelId")
	private SistemUredjajiModeli sistemUredjajiModeli;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;
	
	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="objekatId")
	private Objekti objekti;

	public Uredjaji() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte getAktivno() {
		return this.aktivno;
	}

	public void setAktivno(byte aktivno) {
		this.aktivno = aktivno;
	}

	public Timestamp getIzmenjeno() {
		return this.izmenjeno;
	}

	public void setIzmenjeno(Timestamp izmenjeno) {
		this.izmenjeno = izmenjeno;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public Timestamp getKreirano() {
		return this.kreirano;
	}

	public void setKreirano(Timestamp kreirano) {
		this.kreirano = kreirano;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSerijskiBr() {
		return this.serijskiBr;
	}

	public void setSerijskiBr(String serijskiBr) {
		this.serijskiBr = serijskiBr;
	}

	/*public BigInteger getSimId() {
		return this.simId;
	}

	public void setSimId(BigInteger simId) {
		this.simId = simId;
	}

	public BigInteger getSimId2() {
		return this.simId2;
	}

	public void setSimId2(BigInteger simId2) {
		this.simId2 = simId2;
	}**/

	public Sim getSim() {
		return sim;
	}

	public void setSim(Sim sim) {
		this.sim = sim;
	}

	public Sim getSim2() {
		return sim2;
	}

	public void setSim2(Sim sim2) {
		this.sim2 = sim2;
	}
	
	public int getVersion() {
		return this.version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}

	public byte getZauzet() {
		return this.zauzet;
	}

	public void setZauzet(byte zauzet) {
		this.zauzet = zauzet;
	}

	/*public List<Objekti> getObjektis() {
		return this.objektis;
	}

	public void setObjektis(List<Objekti> objektis) {
		this.objektis = objektis;
	}

	public Objekti addObjekti(Objekti objekti) {
		getObjektis().add(objekti);
		objekti.setUredjaji(this);

		return objekti;
	}

	public Objekti removeObjekti(Objekti objekti) {
		getObjektis().remove(objekti);
		objekti.setUredjaji(null);

		return objekti;
	}**/

	public SistemUredjajiModeli getSistemUredjajiModeli() {
		return this.sistemUredjajiModeli;
	}

	public void setSistemUredjajiModeli(SistemUredjajiModeli sistemUredjajiModeli) {
		this.sistemUredjajiModeli = sistemUredjajiModeli;
	}

	public SistemPretplatnici getSistemPretplatnici() {
		return this.sistemPretplatnici;
	}

	public void setSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		this.sistemPretplatnici = sistemPretplatnici;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

	public Objekti getObjekti() {
		return objekti;
	}

	public void setObjekti(Objekti objekti) {
		this.objekti = objekti;
	}

}
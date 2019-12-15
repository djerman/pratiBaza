package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="bc_uredjaji")
@NamedQuery(name="Uredjaji.findAll", query="SELECT u FROM Uredjaji u")
public class Uredjaji implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private boolean aktivan;

	private Timestamp izmenjeno;

	private String kod;

	private Timestamp kreirano;

	private String opis;

	private String serijskiBr;

	@OneToMany(mappedBy="uredjaji", fetch = FetchType.EAGER)
	private List<Sim> sims;
	
	//private Sim sim, sim2;
	
	private int version;

	private boolean zauzet;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to SistemUredjajiModeli
	@ManyToOne
	@JoinColumn(name="modelId")
	private SistemUredjajiModeli sistemUredjajiModeli;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;
	
	@OneToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name="objekatId")
	private Objekti objekti;
	
	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;

	public Uredjaji() {
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAktivno() {
		return aktivan;
	}

	public void setAktivno(boolean aktivno) {
		this.aktivan = aktivno;
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

	public List<Sim> getSims(){
		return sims;
	}
	
	public void setSims(List<Sim> sims) {
		this.sims = sims;
	}
	
	public Sim getSim() {
		if(getSims().size() > 0) {
			return getSims().get(0);
		}else {
			return null;
		}
		
	}
	
	public void setSim(Sim sim) {
		if(sim != null) {
			getSims().add(sim);
			sim.setUredjaji(this);
			sim.setZauzet(true);
		}
	}

	public Sim getSim2() {
		if(getSims().size() > 1) {
			return getSims().get(1);
		}else {
			return null;
		}
	}
	
	public void setSim2(Sim sim2) {
		if(sim2 != null) {
			getSims().add(sim2);
			sim2.setUredjaji(this);
			sim2.setZauzet(true);
		}
	}

	public Sim removeSim(Sim sim) {
		getSims().remove(sim);
		sim.setUredjaji(null);
		return sim;
	}
	
	public int getVersion() {
		return this.version;
	}
	
	public void setVersion(int version) {
		this.version = version;
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

	public boolean isZauzet() {
		return zauzet;
	}

	public void setZauzet(boolean zauzet) {
		this.zauzet = zauzet;
	}

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

	public Organizacije getOrganizacija() {
		return organizacija;
	}

	public void setOrganizacija(Organizacije organizacija) {
		this.organizacija = organizacija;
	}

}
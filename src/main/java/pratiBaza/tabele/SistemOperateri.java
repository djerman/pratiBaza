package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="af_sistemOperateri")
@NamedQuery(name="SistemOperateri.findAll", query="SELECT s FROM SistemOperateri s")
public class SistemOperateri implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String naziv;

	private int version;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to Sim
	/*@OneToMany(mappedBy="sistemOperateri")
	private List<Sim> sims;**/

	public SistemOperateri() {
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
/*
	public List<Sim> getSims() {
		return this.sims;
	}

	public void setSims(List<Sim> sims) {
		this.sims = sims;
	}

	public Sim addSim(Sim sim) {
		getSims().add(sim);
		sim.setSistemOperateri(this);

		return sim;
	}

	public Sim removeSim(Sim sim) {
		getSims().remove(sim);
		sim.setSistemOperateri(null);

		return sim;
	}
**/
	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
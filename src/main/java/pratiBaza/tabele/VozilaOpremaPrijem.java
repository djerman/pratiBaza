package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="voziloOpremaPrijem")
@NamedQuery(name="VozilaOpremaPrijem.findAll", query="SELECT vop FROM VozilaOpremaPrijem vop")
public class VozilaOpremaPrijem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;
	
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;
	
	@ManyToOne
	@JoinColumn(name="primoPredajaId")
	private VozilaPrimoPredaje primoPredaja;
	
	@ManyToOne
	@JoinColumn(name="opremaId")
	private VozilaOprema oprema;
	
	private int kolicina;
	
	public VozilaOpremaPrijem() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SistemPretplatnici getSistemPretplatnici() {
		return sistemPretplatnici;
	}

	public void setSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		this.sistemPretplatnici = sistemPretplatnici;
	}

	public Organizacije getOrganizacija() {
		return organizacija;
	}

	public void setOrganizacija(Organizacije organizacija) {
		this.organizacija = organizacija;
	}

	public VozilaPrimoPredaje getPrimoPredaja() {
		return primoPredaja;
	}

	public void setPrimoPredaja(VozilaPrimoPredaje primoPredaja) {
		this.primoPredaja = primoPredaja;
	}

	public VozilaOprema getOprema() {
		return oprema;
	}

	public void setOprema(VozilaOprema oprema) {
		this.oprema = oprema;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
}

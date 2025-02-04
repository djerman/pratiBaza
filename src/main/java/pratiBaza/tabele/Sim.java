package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="bd_sim")
@NamedQuery(name="Sim.findAll", query="SELECT s FROM Sim s")
public class Sim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private boolean aktivan;

	private String broj;

	private String iccid;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private String opis;

	//private BigInteger pretplatnikId;
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;
	
	private int version;

	private boolean zauzet;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to SistemOperateri
	@ManyToOne
	@JoinColumn(name="operaterId")
	private SistemOperateri sistemOperateri;
	
	//bi-directional many-to-one association to SistemOperateri
	@ManyToOne
	@JoinColumn(name="uredjajId")
	private Uredjaji uredjaji;
	
	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;

	public Sim() {
		
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

	public String getBroj() {
		return this.broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getIccid() {
		return this.iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
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

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	/*public BigInteger getPretplatnikId() {
		return this.pretplatnikId;
	}

	public void setPretplatnikId(BigInteger pretplatnikId) {
		this.pretplatnikId = pretplatnikId;
	}**/

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

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isZauzet() {
		return zauzet;
	}

	public void setZauzet(boolean zauzet) {
		this.zauzet = zauzet;
	}

	public SistemOperateri getSistemOperateri() {
		return this.sistemOperateri;
	}
	

	public Uredjaji getUredjaji() {
		return uredjaji;
	}

	public void setUredjaji(Uredjaji uredjaji) {
		this.uredjaji = uredjaji;
	}

	public void setSistemOperateri(SistemOperateri sistemOperateri) {
		this.sistemOperateri = sistemOperateri;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
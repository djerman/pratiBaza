package pratiBaza.tabele;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="db_vozaciDozvole")
@NamedQuery(name="VozaciDozvole.findAll", query="SELECT vd FROM VozaciDozvole vd")
public class VozaciDozvole implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int version;
	
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;
	
	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;
	
	//bi-directional many-to-one association to Korisnici
	@ManyToOne
	@JoinColumn(name="korisnikId")
	private Korisnici vozaci;
	
	private String brojDozvole;
	
	private String izdao;
	
	@Temporal(TemporalType.DATE)
	private Date vaziDo;
	
	private boolean AM, A1, A2, A, B1, B, BE, C1, C1E, C, CE, D1, D1E, D, DE, F, M;
	
	private Timestamp kreirano;
	
	private Timestamp izmenjeno;
	
	private boolean izbrisan;
	
	public VozaciDozvole() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public Korisnici getVozaci() {
		return vozaci;
	}

	public void setVozaci(Korisnici vozaci) {
		this.vozaci = vozaci;
	}

	public String getBrojDozvole() {
		return brojDozvole;
	}

	public void setBrojDozvole(String brojDozvole) {
		this.brojDozvole = brojDozvole;
	}

	public String getIzdao() {
		return izdao;
	}

	public void setIzdao(String izdao) {
		this.izdao = izdao;
	}

	public Date getVaziDo() {
		return vaziDo;
	}

	public void setVaziDo(Date vaziDo) {
		this.vaziDo = vaziDo;
	}

	public boolean isAM() {
		return AM;
	}

	public void setAM(boolean aM) {
		AM = aM;
	}

	public boolean isA1() {
		return A1;
	}

	public void setA1(boolean a1) {
		A1 = a1;
	}

	public boolean isA2() {
		return A2;
	}

	public void setA2(boolean a2) {
		A2 = a2;
	}

	public boolean isA() {
		return A;
	}

	public void setA(boolean a) {
		A = a;
	}

	public boolean isB1() {
		return B1;
	}

	public void setB1(boolean b1) {
		B1 = b1;
	}

	public boolean isB() {
		return B;
	}

	public void setB(boolean b) {
		B = b;
	}

	public boolean isBE() {
		return BE;
	}

	public void setBE(boolean bE) {
		BE = bE;
	}

	public boolean isC1() {
		return C1;
	}

	public void setC1(boolean c1) {
		C1 = c1;
	}

	public boolean isC1E() {
		return C1E;
	}

	public void setC1E(boolean c1e) {
		C1E = c1e;
	}

	public boolean isC() {
		return C;
	}

	public void setC(boolean c) {
		C = c;
	}

	public boolean isCE() {
		return CE;
	}

	public void setCE(boolean cE) {
		CE = cE;
	}

	public boolean isD1() {
		return D1;
	}

	public void setD1(boolean d1) {
		D1 = d1;
	}

	public boolean isD1E() {
		return D1E;
	}

	public void setD1E(boolean d1e) {
		D1E = d1e;
	}

	public boolean isD() {
		return D;
	}

	public void setD(boolean d) {
		D = d;
	}

	public boolean isDE() {
		return DE;
	}

	public void setDE(boolean dE) {
		DE = dE;
	}

	public boolean isF() {
		return F;
	}

	public void setF(boolean f) {
		F = f;
	}

	public boolean isM() {
		return M;
	}

	public void setM(boolean m) {
		M = m;
	}

	public Timestamp getKreirano() {
		return kreirano;
	}

	public void setKreirano(Timestamp kreirano) {
		this.kreirano = kreirano;
	}

	public Timestamp getIzmenjeno() {
		return izmenjeno;
	}

	public void setIzmenjeno(Timestamp izmenjeno) {
		this.izmenjeno = izmenjeno;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}
	
}

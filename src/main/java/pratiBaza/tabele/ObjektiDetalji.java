package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="objektiDetalji")
@NamedQuery(name="ObjektiDetalji.findAll", query="SELECT o FROM ObjektiDetalji o")
public class ObjektiDetalji implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private int brzina;

	private int godina;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private String model;

	private int neaktivan;

	private String opis;

	private float potrosnja;

	private int redovan;

	private String registracija;

	private int rezervoar;
	
	//bi-directional many-to-one association to Uredjaji
	@ManyToOne
	@JoinColumn(name="gorivo")
	private SistemGoriva sistemGoriva;

	private int veliki;

	private int version;
	
	private boolean izbrisan;
	
	private boolean teretno;

	public ObjektiDetalji() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrzina() {
		return this.brzina;
	}

	public void setBrzina(int brzina) {
		this.brzina = brzina;
	}

	public int getGodina() {
		return this.godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
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

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNeaktivan() {
		return this.neaktivan;
	}

	public void setNeaktivan(int neaktivan) {
		this.neaktivan = neaktivan;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public float getPotrosnja() {
		return this.potrosnja;
	}

	public void setPotrosnja(float potrosnja) {
		this.potrosnja = potrosnja;
	}

	public SistemGoriva getSistemGoriva() {
		return sistemGoriva;
	}

	public void setSistemGoriva(SistemGoriva sistemGoriva) {
		this.sistemGoriva = sistemGoriva;
	}

	public int getRedovan() {
		return this.redovan;
	}

	public void setRedovan(int redovan) {
		this.redovan = redovan;
	}

	public String getRegistracija() {
		return this.registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public int getRezervoar() {
		return this.rezervoar;
	}

	public void setRezervoar(int rezervoar) {
		this.rezervoar = rezervoar;
	}

	public int getVeliki() {
		return this.veliki;
	}

	public void setVeliki(int veliki) {
		this.veliki = veliki;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isTeretno() {
		return teretno;
	}

	public void setTeretno(boolean teretno) {
		this.teretno = teretno;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the sim database table.
 * 
 */
@Entity
@Table(name="sim")
@NamedQuery(name="Sim.findAll", query="SELECT s FROM Sim s")
public class Sim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte aktivno;

	private String broj;

	private String iccid;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	@Lob
	private String opis;

	private BigInteger pretplatnikId;

	private int version;

	private byte zauzet;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to SistemOperateri
	@ManyToOne
	@JoinColumn(name="operaterId")
	private SistemOperateri sistemOperateri;

	public Sim() {
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

	public BigInteger getPretplatnikId() {
		return this.pretplatnikId;
	}

	public void setPretplatnikId(BigInteger pretplatnikId) {
		this.pretplatnikId = pretplatnikId;
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

	public SistemOperateri getSistemOperateri() {
		return this.sistemOperateri;
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
package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the zoneObjekti database table.
 * 
 */
@Entity
@Table(name="zoneObjekti")
@NamedQuery(name="ZoneObjekti.findAll", query="SELECT z FROM ZoneObjekti z")
public class ZoneObjekti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte izlaz;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private byte ulaz;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to Zone
	@ManyToOne
	@JoinColumn(name="zonaId")
	private Zone zone;

	//bi-directional many-to-one association to Objekti
	@ManyToOne
	@JoinColumn(name="objekatId")
	private Objekti objekti;

	public ZoneObjekti() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte getIzlaz() {
		return this.izlaz;
	}

	public void setIzlaz(byte izlaz) {
		this.izlaz = izlaz;
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

	public byte getUlaz() {
		return this.ulaz;
	}

	public void setUlaz(byte ulaz) {
		this.ulaz = ulaz;
	}

	public Zone getZone() {
		return this.zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public Objekti getObjekti() {
		return this.objekti;
	}

	public void setObjekti(Objekti objekti) {
		this.objekti = objekti;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
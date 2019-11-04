package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="ch_zoneObjekti")
@NamedQuery(name="ObjekatZone.findAll", query="SELECT z FROM ObjekatZone z")
public class ObjekatZone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private boolean aktivan;
	
	private boolean izlaz;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private boolean ulaz;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to Zone
	@ManyToOne
	@JoinColumn(name="zonaId")
	private Zone zone;

	//bi-directional many-to-one association to Objekti zopretplatnikId
	@ManyToOne
	@JoinColumn(name="zopretplatnikId")
	private SistemPretplatnici sistemPretplatnici;
	
	@ManyToOne
	@JoinColumn(name="zoorganizacijaId")
	private Organizacije organizacija;
	
	@ManyToOne
	@JoinColumn(name="objekatId")
	private Objekti objekti;

	public ObjekatZone() {
		
	}

	public Long getId() {
		return this.id;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isIzlaz() {
		return izlaz;
	}

	public void setIzlaz(boolean izlaz) {
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

	public boolean isUlaz() {
		return this.ulaz;
	}

	public void setUlaz(boolean ulaz) {
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

}
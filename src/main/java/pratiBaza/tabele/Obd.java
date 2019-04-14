package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="obd")
@NamedQuery(name="Obd.findAll", query="SELECT o FROM Obd o")
public class Obd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private float akumulator;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datumVreme;

	private float gas;

	private String greske;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private float nivoGoriva;

	private float opterecenje;

	private float prosecnaPotrosnja;

	private int rpm;

	private int temperatura;

	private float tripGorivo;

	private float tripKm;

	private float ukupnoVreme;

	private float ukupnoGorivo;

	private int ukupnoKm;

	private int version;

	//bi-directional many-to-one association to Objekti
	@ManyToOne
	@JoinColumn(name="objekatId")
	private Objekti objekti;

	public Obd() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getAkumulator() {
		return this.akumulator;
	}

	public void setAkumulator(float akumulator) {
		this.akumulator = akumulator;
	}

	public Date getDatumVreme() {
		return this.datumVreme;
	}

	public void setDatumVreme(Date datumVreme) {
		this.datumVreme = datumVreme;
	}

	public float getGas() {
		return this.gas;
	}

	public void setGas(float gas) {
		this.gas = gas;
	}

	public String getGreske() {
		return this.greske;
	}

	public void setGreske(String greske) {
		this.greske = greske;
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

	public float getNivoGoriva() {
		return this.nivoGoriva;
	}

	public void setNivoGoriva(float nivoGoriva) {
		this.nivoGoriva = nivoGoriva;
	}

	public float getOpterecenje() {
		return this.opterecenje;
	}

	public void setOpterecenje(float opterecenje) {
		this.opterecenje = opterecenje;
	}

	public float getProsecnaPotrosnja() {
		return this.prosecnaPotrosnja;
	}

	public void setProsecnaPotrosnja(float prosecnaPotrosnja) {
		this.prosecnaPotrosnja = prosecnaPotrosnja;
	}

	public int getRpm() {
		return this.rpm;
	}

	public void setRpm(int rpm) {
		this.rpm = rpm;
	}

	public int getTemperatura() {
		return this.temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public float getTripGorivo() {
		return this.tripGorivo;
	}

	public void setTripGorivo(float tripGorivo) {
		this.tripGorivo = tripGorivo;
	}

	public float getTripKm() {
		return this.tripKm;
	}

	public void setTripKm(float tripKm) {
		this.tripKm = tripKm;
	}

	public float getUkupnoVreme() {
		return this.ukupnoVreme;
	}

	public void setUkupnoVreme(float ukupnoVreme) {
		this.ukupnoVreme = ukupnoVreme;
	}

	public float getUkupnoGorivo() {
		return this.ukupnoGorivo;
	}

	public void setUkupnoGorivo(float ukupnoGorivo) {
		this.ukupnoGorivo = ukupnoGorivo;
	}

	public int getUkupnoKm() {
		return this.ukupnoKm;
	}

	public void setUkupnoKm(int ukupnoKm) {
		this.ukupnoKm = ukupnoKm;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Objekti getObjekti() {
		return this.objekti;
	}

	public void setObjekti(Objekti objekti) {
		this.objekti = objekti;
	}

}
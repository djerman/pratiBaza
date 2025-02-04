package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="cj_vozilo")
@NamedQuery(name="Vozila.findAll", query="SELECT v FROM Vozila v")
public class Vozila implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;
	@OneToOne
	@JoinColumn(name="objekatId")
	private Objekti objekti;
	private int godina;
	private Timestamp izmenjeno;
	private Timestamp kreirano;
	private String model;
	private String marka;
	private String tip;
	private float potrosnja;
	private String registracija;
	private int rezervoar;
	@ManyToOne
	@JoinColumn(name="gorivo")
	private SistemGoriva sistemGoriva;
	private int version;
	private boolean izbrisan;
	private String brojSaobracajne;
    @ManyToOne
	@JoinColumn(name="saobracajnaId")
	private VozilaSaobracajne saobracajna;
	private String serijskiBroj;
	private Date datumRegistracije, datumPoslednjeRegistracije, maliPoslednjiDatum, velikiPoslednjiDatum;
	private int maliServisKm, velikiServisKm, maliServisMeseci, velikiServisMeseci, maliPoslednjiOBDkm, velikiPoslednjiOBDkm;
	private float maliPoslednjiGPSkm, velikiPoslednjiGPSkm;
	@Transient
	private float kmOdGpsMs, kmOdGpsVs;
	@Transient
	private int kmOdObdMs, kmOdObdVs, danaOdMs, danaOdVs, danaOdRegistracije;
	private boolean teretno;

	public Vozila() {
		
	}

	public Long getId() {
		return this.id;
	}

	public Organizacije getOrganizacija() {
		return organizacija;
	}

	public void setOrganizacija(Organizacije organizacija) {
		this.organizacija = organizacija;
	}

	public SistemPretplatnici getSistemPretplatnici() {
		return sistemPretplatnici;
	}

	public void setSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		this.sistemPretplatnici = sistemPretplatnici;
	}

	public Date getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(Date datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Objekti getObjekti() {
		return objekti;
	}

	public void setObjekti(Objekti objekti) {
		this.objekti = objekti;
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

	public String getRegistracija() {
		return this.registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public VozilaSaobracajne getSaobracajna() {
		return saobracajna;
	}

	public void setSaobracajna(VozilaSaobracajne saobracajna) {
		this.saobracajna = saobracajna;
	}

	public int getRezervoar() {
		return this.rezervoar;
	}

	public void setRezervoar(int rezervoar) {
		this.rezervoar = rezervoar;
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

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getBrojSaobracajne() {
		return brojSaobracajne;
	}

	public void setBrojSaobracajne(String brojSaobracajne) {
		this.brojSaobracajne = brojSaobracajne;
	}

	public String getSerijskiBroj() {
		return serijskiBroj;
	}

	public void setSerijskiBroj(String serijskiBroj) {
		this.serijskiBroj = serijskiBroj;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

	public int getMaliServisKm() {
		return maliServisKm;
	}

	public void setMaliServisKm(int maliServisKm) {
		this.maliServisKm = maliServisKm;
	}

	public int getVelikiServisKm() {
		return velikiServisKm;
	}

	public void setVelikiServisKm(int velikiServisKm) {
		this.velikiServisKm = velikiServisKm;
	}

	public int getMaliServisMeseci() {
		return maliServisMeseci;
	}

	public void setMaliServisMeseci(int maliServisMeseci) {
		this.maliServisMeseci = maliServisMeseci;
	}

	public int getVelikiServisMeseci() {
		return velikiServisMeseci;
	}

	public void setVelikiServisMeseci(int velikiServisMeseci) {
		this.velikiServisMeseci = velikiServisMeseci;
	}

	public Date getDatumPoslednjeRegistracije() {
		return datumPoslednjeRegistracije;
	}

	public void setDatumPoslednjeRegistracije(Date datumPoslednjeRegistracije) {
		this.datumPoslednjeRegistracije = datumPoslednjeRegistracije;
	}

	public Date getMaliPoslednjiDatum() {
		return maliPoslednjiDatum;
	}

	public void setMaliPoslednjiDatum(Date maliPoslednjiDatum) {
		this.maliPoslednjiDatum = maliPoslednjiDatum;
	}

	public Date getVelikiPoslednjiDatum() {
		return velikiPoslednjiDatum;
	}

	public void setVelikiPoslednjiDatum(Date velikiPoslednjiDatum) {
		this.velikiPoslednjiDatum = velikiPoslednjiDatum;
	}

	public int getMaliPoslednjiOBDkm() {
		return maliPoslednjiOBDkm;
	}

	public void setMaliPoslednjiOBDkm(int maliPoslednjiOBDkm) {
		this.maliPoslednjiOBDkm = maliPoslednjiOBDkm;
	}

	public int getVelikiPoslednjiOBDkm() {
		return velikiPoslednjiOBDkm;
	}

	public void setVelikiPoslednjiOBDkm(int velikiPoslednjiOBDkm) {
		this.velikiPoslednjiOBDkm = velikiPoslednjiOBDkm;
	}

	public float getMaliPoslednjiGPSkm() {
		return maliPoslednjiGPSkm;
	}

	public void setMaliPoslednjiGPSkm(float maliPoslednjiGPSkm) {
		this.maliPoslednjiGPSkm = maliPoslednjiGPSkm;
	}

	public float getVelikiPoslednjiGPSkm() {
		return velikiPoslednjiGPSkm;
	}

	public void setVelikiPoslednjiGPSkm(float velikiPoslednjiGPSkm) {
		this.velikiPoslednjiGPSkm = velikiPoslednjiGPSkm;
	}

	public float getKmOdGpsMs() {
		return kmOdGpsMs;
	}

	public void setKmOdGpsMs(float kmOdGpsMs) {
		this.kmOdGpsMs = kmOdGpsMs;
	}

	public float getKmOdGpsVs() {
		return kmOdGpsVs;
	}

	public void setKmOdGpsVs(float kmOdGpsVs) {
		this.kmOdGpsVs = kmOdGpsVs;
	}

	public int getKmOdObdMs() {
		return kmOdObdMs;
	}

	public void setKmOdObdMs(int kmOdObdMs) {
		this.kmOdObdMs = kmOdObdMs;
	}

	public int getKmOdObdVs() {
		return kmOdObdVs;
	}

	public void setKmOdObdVs(int kmOdObdVs) {
		this.kmOdObdVs = kmOdObdVs;
	}

	public int getDanaOdMs() {
		return danaOdMs;
	}

	public void setDanaOdMs(int danaOdMs) {
		this.danaOdMs = danaOdMs;
	}

	public int getDanaOdVs() {
		return danaOdVs;
	}

	public void setDanaOdVs(int danaOdVs) {
		this.danaOdVs = danaOdVs;
	}

	public int getDanaOdRegistracije() {
		return danaOdRegistracije;
	}

	public void setDanaOdRegistracije(int danaOdRegistracije) {
		this.danaOdRegistracije = danaOdRegistracije;
	}

}
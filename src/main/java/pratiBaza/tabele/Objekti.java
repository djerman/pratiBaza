package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="objekti")
@NamedQuery(name="Objekti.findAll", query="SELECT o FROM Objekti o")
public class Objekti implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private boolean aktivan;

	private int vremeStajanja;
	
	private int prekoracenjeBrzine;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private String oznaka;

	private boolean tip;

	private int version;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to GrupeObjekti
	/*@OneToMany(mappedBy="objekti")
	private List<GrupeObjekti> grupeObjektis;**/

	//bi-directional many-to-one association to Javljanja
	//@OneToMany(mappedBy="objekti")
	//private List<Javljanja> javljanjas;

	//bi-directional many-to-one association to JavljanjaPoslednja
	/*@OneToMany(mappedBy="objekti")
	private List<JavljanjaPoslednja> javljanjaPoslednjas;**/

	//bi-directional many-to-one association to Obd
	//@OneToMany(mappedBy="objekti")
	//private List<Obd> obds;

	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;

	//bi-directional many-to-one association to Uredjaji
	@ManyToOne
	@JoinColumn(name="uredjajId")
	private Uredjaji uredjaji;


	//bi-directional many-to-one association to Objekti
	@ManyToOne
	@JoinColumn(name="detaljiId")
	private ObjektiDetalji objektiDetalji;

	//bi-directional many-to-one association to ZoneObjekti
	/*@OneToMany(mappedBy="objekti")
	private List<ObjekatZone> zoneObjektis;**/
	
	//bi-directional many-to-one association to ZoneObjekti
	@OneToMany(mappedBy="objekti")
	private List<AlarmiKorisnik> alarmiKorisniks;

	public Objekti() {
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public byte getGorivo() {
		return this.gorivo;
	}

	public void setGorivo(byte gorivo) {
		this.gorivo = gorivo;
	}**/

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

	public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public boolean getTip() {
		return this.tip;
	}

	public void setTip(boolean tip) {
		this.tip = tip;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	/*public List<GrupeObjekti> getGrupeObjektis() {
		return this.grupeObjektis;
	}

	public void setGrupeObjektis(List<GrupeObjekti> grupeObjektis) {
		this.grupeObjektis = grupeObjektis;
	}

	public GrupeObjekti addGrupeObjekti(GrupeObjekti grupeObjekti) {
		getGrupeObjektis().add(grupeObjekti);
		grupeObjekti.setObjekti(this);

		return grupeObjekti;
	}

	public GrupeObjekti removeGrupeObjekti(GrupeObjekti grupeObjekti) {
		getGrupeObjektis().remove(grupeObjekti);
		grupeObjekti.setObjekti(null);

		return grupeObjekti;
	}**/

	/*public List<Javljanja> getJavljanjas() {
		return this.javljanjas;
	}

	public void setJavljanjas(List<Javljanja> javljanjas) {
		this.javljanjas = javljanjas;
	}

	public Javljanja addJavljanja(Javljanja javljanja) {
		getJavljanjas().add(javljanja);
		javljanja.setObjekti(this);

		return javljanja;
	}

	public Javljanja removeJavljanja(Javljanja javljanja) {
		getJavljanjas().remove(javljanja);
		javljanja.setObjekti(null);

		return javljanja;
	}**/

	/*public List<JavljanjaPoslednja> getJavljanjaPoslednjas() {
		return this.javljanjaPoslednjas;
	}
	
	public void setJavljanjaPoslednjas(List<JavljanjaPoslednja> javljanjaPoslednjas) {
		this.javljanjaPoslednjas = javljanjaPoslednjas;
	}

	public JavljanjaPoslednja addJavljanjaPoslednja(JavljanjaPoslednja javljanjaPoslednja) {
		getJavljanjaPoslednjas().add(javljanjaPoslednja);
		javljanjaPoslednja.setObjekti(this);

		return javljanjaPoslednja;
	}

	public JavljanjaPoslednja removeJavljanjaPoslednja(JavljanjaPoslednja javljanjaPoslednja) {
		getJavljanjaPoslednjas().remove(javljanjaPoslednja);
		javljanjaPoslednja.setObjekti(null);

		return javljanjaPoslednja;
	}**/

	/*public List<Obd> getObds() {
		return this.obds;
	}

	public void setObds(List<Obd> obds) {
		this.obds = obds;
	}

	public Obd addObd(Obd obd) {
		getObds().add(obd);
		obd.setObjekti(this);

		return obd;
	}

	public Obd removeObd(Obd obd) {
		getObds().remove(obd);
		obd.setObjekti(null);

		return obd;
	}**/

	public Organizacije getOrganizacija() {
		return this.organizacija;
	}

	public void setOrganizacija(Organizacije organizacija) {
		this.organizacija = organizacija;
	}

	public SistemPretplatnici getSistemPretplatnici() {
		return this.sistemPretplatnici;
	}

	public void setSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		this.sistemPretplatnici = sistemPretplatnici;
	}

	public Uredjaji getUredjaji() {
		return this.uredjaji;
	}

	public void setUredjaji(Uredjaji uredjaji) {
		this.uredjaji = uredjaji;
	}

	public ObjektiDetalji getObjektiDetalji() {
		return objektiDetalji;
	}

	public void setObjektiDetalji(ObjektiDetalji objektiDetalji) {
		this.objektiDetalji = objektiDetalji;
	}

	/*public List<ObjekatZone> getZoneObjektis() {
		return this.zoneObjektis;
	}

	public void setZoneObjektis(List<ObjekatZone> zoneObjektis) {
		this.zoneObjektis = zoneObjektis;
	}

	public ObjekatZone addZoneObjekti(ObjekatZone zoneObjekti) {
		getZoneObjektis().add(zoneObjekti);
		zoneObjekti.setObjekti(this);

		return zoneObjekti;
	}

	public ObjekatZone removeZoneObjekti(ObjekatZone zoneObjekti) {
		getZoneObjektis().remove(zoneObjekti);
		zoneObjekti.setObjekti(null);

		return zoneObjekti;
	}**/

	public List<AlarmiKorisnik> getAlarmiKorisniks() {
		return alarmiKorisniks;
	}

	public void setAlarmiKorisniks(List<AlarmiKorisnik> alarmiKorisniks) {
		this.alarmiKorisniks = alarmiKorisniks;
	}

	public AlarmiKorisnik addAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		getAlarmiKorisniks().add(alarmKorisnik);
		alarmKorisnik.setObjekti(this);
		
		return alarmKorisnik;
	}
	
	public AlarmiKorisnik removeAlarmiKorisnik(AlarmiKorisnik alarmKorisnik) {
		getAlarmiKorisniks().remove(alarmKorisnik);
		alarmKorisnik.setObjekti(null);
		
		return alarmKorisnik;
	}
	
	public int getVremeStajanja() {
		return vremeStajanja;
	}

	public void setVremeStajanja(int vremeStajanja) {
		this.vremeStajanja = vremeStajanja;
	}

	public int getPrekoracenjeBrzine() {
		return prekoracenjeBrzine;
	}

	public void setPrekoracenjeBrzine(int prekoracenjeBrzine) {
		this.prekoracenjeBrzine = prekoracenjeBrzine;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
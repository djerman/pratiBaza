package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the objekti database table.
 * 
 */
@Entity
@Table(name="objekti")
@NamedQuery(name="Objekti.findAll", query="SELECT o FROM Objekti o")
public class Objekti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte aktivan;

	private byte detalji;

	private byte gorivo;

	private float gpskm;

	private Timestamp izmenjeno;

	private Timestamp kreirano;

	private String oznaka;

	private byte teretno;

	private byte tip;

	private int version;

	private float virtualOdo;

	//bi-directional many-to-one association to GrupeObjekti
	@OneToMany(mappedBy="objekti")
	private List<GrupeObjekti> grupeObjektis;

	//bi-directional many-to-one association to Javljanja
	@OneToMany(mappedBy="objekti")
	private List<Javljanja> javljanjas;

	//bi-directional many-to-one association to JavljanjaPoslednja
	@OneToMany(mappedBy="objekti")
	private List<JavljanjaPoslednja> javljanjaPoslednjas;

	//bi-directional many-to-one association to Obd
	@OneToMany(mappedBy="objekti")
	private List<Obd> obds;

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

	//bi-directional many-to-one association to ObjektiDetalji
	@OneToMany(mappedBy="objekti")
	private List<ObjektiDetalji> objektiDetaljis;

	//bi-directional many-to-one association to ZoneObjekti
	@OneToMany(mappedBy="objekti")
	private List<ZoneObjekti> zoneObjektis;

	public Objekti() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte getAktivan() {
		return this.aktivan;
	}

	public void setAktivan(byte aktivan) {
		this.aktivan = aktivan;
	}

	public byte getDetalji() {
		return this.detalji;
	}

	public void setDetalji(byte detalji) {
		this.detalji = detalji;
	}

	public byte getGorivo() {
		return this.gorivo;
	}

	public void setGorivo(byte gorivo) {
		this.gorivo = gorivo;
	}

	public float getGpskm() {
		return this.gpskm;
	}

	public void setGpskm(float gpskm) {
		this.gpskm = gpskm;
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

	public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public byte getTeretno() {
		return this.teretno;
	}

	public void setTeretno(byte teretno) {
		this.teretno = teretno;
	}

	public byte getTip() {
		return this.tip;
	}

	public void setTip(byte tip) {
		this.tip = tip;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public float getVirtualOdo() {
		return this.virtualOdo;
	}

	public void setVirtualOdo(float virtualOdo) {
		this.virtualOdo = virtualOdo;
	}

	public List<GrupeObjekti> getGrupeObjektis() {
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
	}

	public List<Javljanja> getJavljanjas() {
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
	}

	public List<JavljanjaPoslednja> getJavljanjaPoslednjas() {
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
	}

	public List<Obd> getObds() {
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
	}

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

	public List<ObjektiDetalji> getObjektiDetaljis() {
		return this.objektiDetaljis;
	}

	public void setObjektiDetaljis(List<ObjektiDetalji> objektiDetaljis) {
		this.objektiDetaljis = objektiDetaljis;
	}

	public ObjektiDetalji addObjektiDetalji(ObjektiDetalji objektiDetalji) {
		getObjektiDetaljis().add(objektiDetalji);
		objektiDetalji.setObjekti(this);

		return objektiDetalji;
	}

	public ObjektiDetalji removeObjektiDetalji(ObjektiDetalji objektiDetalji) {
		getObjektiDetaljis().remove(objektiDetalji);
		objektiDetalji.setObjekti(null);

		return objektiDetalji;
	}

	public List<ZoneObjekti> getZoneObjektis() {
		return this.zoneObjektis;
	}

	public void setZoneObjektis(List<ZoneObjekti> zoneObjektis) {
		this.zoneObjektis = zoneObjektis;
	}

	public ZoneObjekti addZoneObjekti(ZoneObjekti zoneObjekti) {
		getZoneObjektis().add(zoneObjekti);
		zoneObjekti.setObjekti(this);

		return zoneObjekti;
	}

	public ZoneObjekti removeZoneObjekti(ZoneObjekti zoneObjekti) {
		getZoneObjektis().remove(zoneObjekti);
		zoneObjekti.setObjekti(null);

		return zoneObjekti;
	}

}
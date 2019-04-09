package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="zone")
@NamedQuery(name="Zone.findAll", query="SELECT z FROM Zone z")
public class Zone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Timestamp kreirano;
	
	private String naziv;

	private double lon;

	private String opis;

	private int precnik;

	private double lat;

	private Timestamp izmenjeno;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;

	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;

	//bi-directional many-to-one association to ZoneObjekti
	/*@OneToMany(mappedBy="zone")
	private List<ZoneObjekti> zoneObjektis;**/

	public Zone() {
		
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Timestamp getKreirano() {
		return this.kreirano;
	}

	public void setKreirano(Timestamp kreirano) {
		this.kreirano = kreirano;
	}

	public double getLon() {
		return this.lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getPrecnik() {
		return this.precnik;
	}

	public void setPrecnik(int precnik) {
		this.precnik = precnik;
	}

	public double getLat() {
		return this.lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public Timestamp getIzmenjen() {
		return this.izmenjeno;
	}

	public void setIzmenjen(Timestamp izmenjen) {
		this.izmenjeno = izmenjen;
	}

	public SistemPretplatnici getSistemPretplatnici() {
		return this.sistemPretplatnici;
	}

	public void setSistemPretplatnici(SistemPretplatnici sistemPretplatnici) {
		this.sistemPretplatnici = sistemPretplatnici;
	}

	public Organizacije getOrganizacija() {
		return this.organizacija;
	}

	public void setOrganizacija(Organizacije organizacija) {
		this.organizacija = organizacija;
	}

	/*public List<ZoneObjekti> getZoneObjektis() {
		return this.zoneObjektis;
	}

	public void setZoneObjektis(List<ZoneObjekti> zoneObjektis) {
		this.zoneObjektis = zoneObjektis;
	}

	public ZoneObjekti addZoneObjekti(ZoneObjekti zoneObjekti) {
		getZoneObjektis().add(zoneObjekti);
		zoneObjekti.setZone(this);

		return zoneObjekti;
	}

	public ZoneObjekti removeZoneObjekti(ZoneObjekti zoneObjekti) {
		getZoneObjektis().remove(zoneObjekti);
		zoneObjekti.setZone(null);

		return zoneObjekti;
	}**/

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
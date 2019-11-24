package pratiBaza.tabele;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="dj_troskovi")
@NamedQuery(name="Troskovi.findAll", query="SELECT t FROM Vozila t")
public class Troskovi implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int version;
	
	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="pretplatnikId")
	private SistemPretplatnici sistemPretplatnici;
	
	//bi-directional many-to-one association to Organizacija
	@ManyToOne
	@JoinColumn(name="organizacijaId")
	private Organizacije organizacija;
	
	private Timestamp datumVreme;
	
	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="partnerId")
	private Partneri partner;
	
	//bi-directional many-to-one association to SistemPretplatnici
	@ManyToOne
	@JoinColumn(name="objekatId")
	private Objekti objekti;
	
	private int tipServisa, pdvProcenat;//tip servisa ako je 0 onda je gorivo, 1 = mali servis, 2 = veliki servis, 3 = registracija 4 = servis
	
	//bi-directional many-to-one association to Uredjaji
	@ManyToOne
	@JoinColumn(name="tipGorivaId")
	private SistemGoriva sistemGoriva;
	
	private float kolicina, cena, pdvIznos, ukupno;
	
	private String opis, brojRacuna;
	
	@Transient
	private String partnerNaziv, objekatOznaka, registracija, tipServisaNaziv, marka, model, tip;
	
	private boolean izbrisan;
	
	private Timestamp izmenjeno;

	private Timestamp kreirano;
	
	public Troskovi() {
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

	public Timestamp getDatumVreme() {
		return datumVreme;
	}

	public void setDatumVreme(Timestamp datumVreme) {
		this.datumVreme = datumVreme;
	}

	public Partneri getPartner() {
		return partner;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public void setPartner(Partneri partner) {
		this.partner = partner;
	}

	public Objekti getObjekti() {
		return objekti;
	}

	public void setObjekti(Objekti objekti) {
		this.objekti = objekti;
	}

	public int getTipServisa() {
		return tipServisa;
	}

	public void setTipServisa(int tipServisa) {
		this.tipServisa = tipServisa;
	}

	public int getPdvProcenat() {
		return pdvProcenat;
	}

	public void setPdvProcenat(int pdvProcenat) {
		this.pdvProcenat = pdvProcenat;
	}

	public SistemGoriva getSistemGoriva() {
		return sistemGoriva;
	}

	public void setSistemGoriva(SistemGoriva sistemGoriva) {
		this.sistemGoriva = sistemGoriva;
	}

	public float getKolicina() {
		return kolicina;
	}

	public void setKolicina(float kolicina) {
		this.kolicina = kolicina;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public float getPdvIznos() {
		return pdvIznos;
	}

	public void setPdvIznos(float pdvIznos) {
		this.pdvIznos = pdvIznos;
	}

	public float getUkupno() {
		return ukupno;
	}

	public void setUkupno(float ukupno) {
		this.ukupno = ukupno;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

	public Timestamp getIzmenjeno() {
		return izmenjeno;
	}

	public void setIzmenjeno(Timestamp izmenjeno) {
		this.izmenjeno = izmenjeno;
	}

	public Timestamp getKreirano() {
		return kreirano;
	}

	public void setKreirano(Timestamp kreirano) {
		this.kreirano = kreirano;
	}

	public String getPartnerNaziv() {
		partnerNaziv = partner.getNaziv(); 
		return partnerNaziv;
	}

	public void setPartnerNaziv(String partnerNaziv) {
		this.partnerNaziv = partnerNaziv;
	}

	public String getObjekatOznaka() {
		objekatOznaka = objekti.getOznaka();
		return objekatOznaka;
	}

	public void setObjekatOznaka(String objekatOznaka) {
		this.objekatOznaka = objekatOznaka;
	}

	public String getRegistracija() {
		registracija = objekti.getVozilo() == null ? "" : objekti.getVozilo().getRegistracija();
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public String getTipServisaNaziv() {
		switch (tipServisa) {
		case 0: tipServisaNaziv = "гориво";
		    break;
		case 1: tipServisaNaziv = "мали сервис";
			break;
		case 2: tipServisaNaziv = "велики сервис";
		    break;
		case 3: tipServisaNaziv = "регистрација";
		    break;
		case 4: tipServisaNaziv = "сервис";
            break;
		default: tipServisaNaziv = "";
			break;
		}
		return tipServisaNaziv;
	}

	public void setTipServisaNaziv(String tipServisaNaziv) {
		this.tipServisaNaziv = tipServisaNaziv;
	}

	public String getMarka() {
		marka = objekti.getVozilo() == null ? "" : objekti.getVozilo().getMarka();
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		model = objekti.getVozilo() == null ? "" : objekti.getVozilo().getModel();
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTip() {
		tip = objekti.getVozilo() == null ? "" : objekti.getVozilo().getTip();
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
}

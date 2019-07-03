package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="sistemAlarmi")
@NamedQuery(name="SistemAlarmi.findAll", query="SELECT s FROM SistemAlarmi s")
public class SistemAlarmi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private boolean adresa;

	private boolean aktivan;

	private boolean alarmiranje;

	private String naziv;

	private String opis;

	private boolean pregled;
	
	private boolean email;

	private boolean prikaz;

	private String sifra;

	private int version;
	
	private boolean izbrisan;

	//bi-directional many-to-one association to AlarmiKorisnik
	/*@OneToMany(mappedBy="sistemAlarm")
	private List<AlarmiKorisnik> alarmiKorisniks;**/

	//bi-directional many-to-one association to Javljanja
	/*@OneToMany(mappedBy="sistemAlarmi")
	private List<Javljanja> javljanjas;

	//bi-directional many-to-one association to JavljanjaPoslednja
	@OneToMany(mappedBy="sistemAlarmi")
	private List<JavljanjaPoslednja> javljanjaPoslednjas;**/

	public SistemAlarmi() {
		
	}

	public String toString() {
		return this.naziv;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSifra() {
		return this.sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	/*public List<AlarmiKorisnik> getAlarmiKorisniks() {
		return this.alarmiKorisniks;
	}

	public void setAlarmiKorisniks(List<AlarmiKorisnik> alarmiKorisniks) {
		this.alarmiKorisniks = alarmiKorisniks;
	}

	public AlarmiKorisnik addAlarmiKorisnik(AlarmiKorisnik alarmiKorisnik) {
		getAlarmiKorisniks().add(alarmiKorisnik);
		alarmiKorisnik.setSistemAlarmi(this);

		return alarmiKorisnik;
	}

	public AlarmiKorisnik removeAlarmiKorisnik(AlarmiKorisnik alarmiKorisnik) {
		getAlarmiKorisniks().remove(alarmiKorisnik);
		alarmiKorisnik.setSistemAlarmi(null);

		return alarmiKorisnik;
	}**/

	/*public List<Javljanja> getJavljanjas() {
		return this.javljanjas;
	}

	public void setJavljanjas(List<Javljanja> javljanjas) {
		this.javljanjas = javljanjas;
	}

	public Javljanja addJavljanja(Javljanja javljanja) {
		getJavljanjas().add(javljanja);
		javljanja.setSistemAlarmi(this);

		return javljanja;
	}

	public Javljanja removeJavljanja(Javljanja javljanja) {
		getJavljanjas().remove(javljanja);
		javljanja.setSistemAlarmi(null);

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
		javljanjaPoslednja.setSistemAlarmi(this);

		return javljanjaPoslednja;
	}

	public JavljanjaPoslednja removeJavljanjaPoslednja(JavljanjaPoslednja javljanjaPoslednja) {
		getJavljanjaPoslednjas().remove(javljanjaPoslednja);
		javljanjaPoslednja.setSistemAlarmi(null);

		return javljanjaPoslednja;
	}**/

	public boolean isAdresa() {
		return adresa;
	}

	public void setAdresa(boolean adresa) {
		this.adresa = adresa;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public boolean isAlarmiranje() {
		return alarmiranje;
	}

	public void setAlarmiranje(boolean alarmiranje) {
		this.alarmiranje = alarmiranje;
	}

	public boolean isPregled() {
		return pregled;
	}

	public void setPregled(boolean pregled) {
		this.pregled = pregled;
	}

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}

	public boolean isPrikaz() {
		return prikaz;
	}

	public void setPrikaz(boolean prikaz) {
		this.prikaz = prikaz;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sistem database table.
 * 
 */
@Entity
@Table(name="sistem")
@NamedQuery(name="Sistem.findAll", query="SELECT s FROM Sistem s")
public class Sistem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String adresaServeraMape;

	private String adresaVlasnika;

	private String api;

	private String emailKorisnik;

	private String emailLozinka;

	private String emailServer;

	private int emailServerPort;

	private String emailVlasnika;

	private String nominatimAdresa;

	private String sajtVlasnika;

	private byte serverMape;

	private String telVlasnika;

	private String vlasnik;

	public Sistem() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresaServeraMape() {
		return this.adresaServeraMape;
	}

	public void setAdresaServeraMape(String adresaServeraMape) {
		this.adresaServeraMape = adresaServeraMape;
	}

	public String getAdresaVlasnika() {
		return this.adresaVlasnika;
	}

	public void setAdresaVlasnika(String adresaVlasnika) {
		this.adresaVlasnika = adresaVlasnika;
	}

	public String getApi() {
		return this.api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getEmailKorisnik() {
		return this.emailKorisnik;
	}

	public void setEmailKorisnik(String emailKorisnik) {
		this.emailKorisnik = emailKorisnik;
	}

	public String getEmailLozinka() {
		return this.emailLozinka;
	}

	public void setEmailLozinka(String emailLozinka) {
		this.emailLozinka = emailLozinka;
	}

	public String getEmailServer() {
		return this.emailServer;
	}

	public void setEmailServer(String emailServer) {
		this.emailServer = emailServer;
	}

	public int getEmailServerPort() {
		return this.emailServerPort;
	}

	public void setEmailServerPort(int emailServerPort) {
		this.emailServerPort = emailServerPort;
	}

	public String getEmailVlasnika() {
		return this.emailVlasnika;
	}

	public void setEmailVlasnika(String emailVlasnika) {
		this.emailVlasnika = emailVlasnika;
	}

	public String getNominatimAdresa() {
		return this.nominatimAdresa;
	}

	public void setNominatimAdresa(String nominatimAdresa) {
		this.nominatimAdresa = nominatimAdresa;
	}

	public String getSajtVlasnika() {
		return this.sajtVlasnika;
	}

	public void setSajtVlasnika(String sajtVlasnika) {
		this.sajtVlasnika = sajtVlasnika;
	}

	public byte getServerMape() {
		return this.serverMape;
	}

	public void setServerMape(byte serverMape) {
		this.serverMape = serverMape;
	}

	public String getTelVlasnika() {
		return this.telVlasnika;
	}

	public void setTelVlasnika(String telVlasnika) {
		this.telVlasnika = telVlasnika;
	}

	public String getVlasnik() {
		return this.vlasnik;
	}

	public void setVlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
	}

}
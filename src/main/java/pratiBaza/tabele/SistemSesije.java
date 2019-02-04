package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sistemSesije database table.
 * 
 */
@Entity
@Table(name="sistemSesije")
@NamedQuery(name="SistemSesije.findAll", query="SELECT s FROM SistemSesije s")
public class SistemSesije implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datumKraj;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datumPocetak;

	private String ipAdresa;

	private int version;

	//bi-directional many-to-one association to Korisnici
	@ManyToOne
	@JoinColumn(name="korisnikId")
	private Korisnici korisnici;

	public SistemSesije() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDatumKraj() {
		return this.datumKraj;
	}

	public void setDatumKraj(Date datumKraj) {
		this.datumKraj = datumKraj;
	}

	public Date getDatumPocetak() {
		return this.datumPocetak;
	}

	public void setDatumPocetak(Date datumPocetak) {
		this.datumPocetak = datumPocetak;
	}

	public String getIpAdresa() {
		return this.ipAdresa;
	}

	public void setIpAdresa(String ipAdresa) {
		this.ipAdresa = ipAdresa;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Korisnici getKorisnici() {
		return this.korisnici;
	}

	public void setKorisnici(Korisnici korisnici) {
		this.korisnici = korisnici;
	}

}
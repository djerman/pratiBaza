package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sistemGoriva database table.
 * 
 */
@Entity
@Table(name="sistemGoriva")
@NamedQuery(name="SistemGoriva.findAll", query="SELECT s FROM SistemGoriva s")
public class SistemGoriva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String naziv;

	private int version;

	public SistemGoriva() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="sistemGoriva")
@NamedQuery(name="SistemGoriva.findAll", query="SELECT s FROM SistemGoriva s")
public class SistemGoriva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String naziv;

	private int version;
	
	private boolean izbrisan;

	public SistemGoriva() {
		
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

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

}
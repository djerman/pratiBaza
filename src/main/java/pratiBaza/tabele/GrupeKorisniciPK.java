package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the grupeZaposleni database table.
 * 
 */
@Embeddable
public class GrupeKorisniciPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Long id;

	@Column(insertable=false, updatable=false)
	private String pretplatnikId;

	@Column(insertable=false, updatable=false)
	private String grupaId;

	@Column(insertable=false, updatable=false)
	private String korisnikId;

	public GrupeKorisniciPK() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPretplatnikId() {
		return this.pretplatnikId;
	}
	public void setPretplatnikId(String pretplatnikId) {
		this.pretplatnikId = pretplatnikId;
	}
	public String getGrupaId() {
		return this.grupaId;
	}
	public void setGrupaId(String grupaId) {
		this.grupaId = grupaId;
	}
	public String getKorisnikId() {
		return this.korisnikId;
	}
	public void setKorisnikId(String korisnikId) {
		this.korisnikId = korisnikId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GrupeKorisniciPK)) {
			return false;
		}
		GrupeKorisniciPK castOther = (GrupeKorisniciPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.pretplatnikId.equals(castOther.pretplatnikId)
			&& this.grupaId.equals(castOther.grupaId)
			&& this.korisnikId.equals(castOther.korisnikId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.pretplatnikId.hashCode();
		hash = hash * prime + this.grupaId.hashCode();
		hash = hash * prime + this.korisnikId.hashCode();
		
		return hash;
	}
}
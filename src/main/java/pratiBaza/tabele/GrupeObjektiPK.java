package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the grupeObjekti database table.
 * 
 */
@Embeddable
public class GrupeObjektiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	@Column(insertable=false, updatable=false)
	private String grupaId;

	@Column(insertable=false, updatable=false)
	private String objekatId;

	public GrupeObjektiPK() {
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGrupaId() {
		return this.grupaId;
	}
	public void setGrupaId(String grupaId) {
		this.grupaId = grupaId;
	}
	public String getObjekatId() {
		return this.objekatId;
	}
	public void setObjekatId(String objekatId) {
		this.objekatId = objekatId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GrupeObjektiPK)) {
			return false;
		}
		GrupeObjektiPK castOther = (GrupeObjektiPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.grupaId.equals(castOther.grupaId)
			&& this.objekatId.equals(castOther.objekatId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.grupaId.hashCode();
		hash = hash * prime + this.objekatId.hashCode();
		
		return hash;
	}
}
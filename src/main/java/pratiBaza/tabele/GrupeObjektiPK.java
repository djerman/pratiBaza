package pratiBaza.tabele;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class GrupeObjektiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Long id;

	@Column(insertable=false, updatable=false)
	private Long grupaId;

	@Column(insertable=false, updatable=false)
	private Long objekatId;

	public GrupeObjektiPK() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGrupaId() {
		return this.grupaId;
	}
	public void setGrupaId(Long grupaId) {
		this.grupaId = grupaId;
	}
	public long getObjekatId() {
		return this.objekatId;
	}
	public void setObjekatId(Long objekatId) {
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
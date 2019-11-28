package pratiBaza.pomocne;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class TipServisa implements Serializable{

	private static final long serialVersionUID = 1L;
	private String naziv;
	private int rb;
	
	public TipServisa(String naziv, int rb) {
		this.naziv = naziv;
		this.rb = rb;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getRb() {
		return rb;
	}

	public void setRb(int rb) {
		this.rb = rb;
	}
	
}

package pratiBaza.pomocne;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class IzvestajTip implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int rb;
	private String naziv;
	
	public IzvestajTip(int rb, String naziv) {
		this.rb = rb;
		this.naziv = naziv;
	}

	public int getRb() {
		return rb;
	}

	public void setRb(int rb) {
		this.rb = rb;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	
}

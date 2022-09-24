package pratiBaza.servis;

import java.util.ArrayList;

import pratiBaza.tabele.SistemGoriva;

public interface SistemGorivoServis {

	public void unesiGorivo(SistemGoriva gorivo);
	
	public void azurirajGorivo(SistemGoriva gorivo);
	
	public void izbrisiGorivo(SistemGoriva gorivo);
	
	public ArrayList<SistemGoriva> vratiSvaGoriva(boolean izbrisan);
	
	public SistemGoriva nadjiGorivoPoId(int id);
	
	public SistemGoriva nadjiGorivoPoNazivu(String gorivo);
	
	public SistemGoriva pretraziGorivoPoNazivu(String gorivo);
}

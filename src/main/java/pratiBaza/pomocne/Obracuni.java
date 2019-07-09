package pratiBaza.pomocne;

import pratiBaza.tabele.Javljanja;
import pratiBaza.tabele.JavljanjaPoslednja;

public class Obracuni {

	public Obracuni() {
		// TODO Auto-generated constructor stub
	}
	
	public double rastojanje(Javljanja javljanje, JavljanjaPoslednja javljanjePoslednje) {
		double dist = 0;
		if(javljanje.getDatumVreme().after(javljanjePoslednje.getDatumVreme())) {
			if(javljanje.isValid() && javljanje.getBrzina() < 200) {
			    double earthRadius = 6371000; //meters * 1000
			    double dLat = Math.toRadians(javljanje.getLat() - javljanjePoslednje.getLat());
			    double dLng = Math.toRadians(javljanje.getLon() - javljanjePoslednje.getLon());
			    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			               Math.cos(Math.toRadians(javljanjePoslednje.getLat())) * Math.cos(Math.toRadians(javljanje.getLon())) *
			               Math.sin(dLng/2) * Math.sin(dLng/2);
			    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			    if(earthRadius * c > 5) {
			    	dist = (earthRadius * c);
			    }
			}
		}
		return dist/1000;
	}
	
	public double rastojanje(Javljanja javljanje, Javljanja javljanjePoslednje) {
		double dist = 0;
		if(javljanje.getDatumVreme().after(javljanjePoslednje.getDatumVreme())) {
			if(javljanje.isValid() && javljanje.getBrzina() < 200) {
			    double earthRadius = 6371000; //meters * 1000
			    double dLat = Math.toRadians(javljanje.getLat() - javljanjePoslednje.getLat());
			    double dLng = Math.toRadians(javljanje.getLon() - javljanjePoslednje.getLon());
			    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			               Math.cos(Math.toRadians(javljanjePoslednje.getLat())) * Math.cos(Math.toRadians(javljanje.getLon())) *
			               Math.sin(dLng/2) * Math.sin(dLng/2);
			    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			    if(earthRadius * c > 5) {
			    	dist = (earthRadius * c);
			    }
			}
		}
		return dist/1000;
	}
	
	public double rastojanjeKoordinate(Javljanja javljanje, double lat, double lon) {
		double dist = 0;

		if(javljanje.isValid() && javljanje.getBrzina() < 200) {
		    double earthRadius = 6371000; //meters * 1000 - ovo je za metre
		    double dLat = Math.toRadians(javljanje.getLat() - lat);
		    double dLng = Math.toRadians(javljanje.getLon() - lon);
		    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		               Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(javljanje.getLon())) *
		               Math.sin(dLng/2) * Math.sin(dLng/2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		    if(earthRadius * c > 5) {
		    	dist = (earthRadius * c);
		    }
		}
	
		return dist;
	}
}

package pratiBaza.pomocne;

import java.util.Date;

import pratiBaza.tabele.Javljanja;
import pratiBaza.tabele.JavljanjaPoslednja;

public class Obracuni {

	public Obracuni() {
		// TODO Auto-generated constructor stub
	}
	
	public double rastojanje(Javljanja javljanje, JavljanjaPoslednja javljanjePoslednje) {
		return rastojanjeSaModifikacijom(javljanje.getLat(), javljanje.getLon(), javljanjePoslednje.getLat(), javljanjePoslednje.getLon());
		/*if(javljanje.getDatumVreme().after(javljanjePoslednje.getDatumVreme())) {
			
			//return rastojanjeSaVisinom(javljanje.getLat(), javljanje.getLon(), javljanjePoslednje.getLat(), javljanjePoslednje.getLon(),
					//javljanje.getVisina(), javljanjePoslednje.getVisina());
			if(javljanje.getObjekti().getId().equals(Long.valueOf(226)) || javljanje.getObjekti().getId().equals(Long.valueOf(178))
					|| javljanje.getObjekti().getId().equals(Long.valueOf(195))) {
				
				return rastojanjeSaVisinom(javljanje.getLat(), javljanje.getLon(), javljanjePoslednje.getLat(), javljanjePoslednje.getLon(),
						javljanje.getVisina(), javljanjePoslednje.getVisina());
			}else {
				return rastojanjeSaModifikacijom(javljanje.getLat(), javljanje.getLon(), javljanjePoslednje.getLat(), javljanjePoslednje.getLon());
				//return rastojanjeStandardno(javljanje.getLat(), javljanje.getLon(), javljanjePoslednje.getLat(), javljanjePoslednje.getLon());
			}
		}else {
			return 0;
		}	*/
	}
	
	public double rastojanje(Javljanja javljanje, Javljanja javljanjePoslednje) {
		return rastojanjeSaModifikacijom(javljanje.getLat(), javljanje.getLon(), javljanjePoslednje.getLat(), javljanjePoslednje.getLon());
		/*if(javljanje.getDatumVreme().after(javljanjePoslednje.getDatumVreme())) {
			//return rastojanjeSaVisinom(javljanje.getLat(), javljanje.getLon(), javljanjePoslednje.getLat(), javljanjePoslednje.getLon(),
					//javljanje.getVisina(), javljanjePoslednje.getVisina());
			
			if(javljanje.getObjekti().getId().equals(Long.valueOf(226)) || javljanje.getObjekti().getId().equals(Long.valueOf(178))
					|| javljanje.getObjekti().getId().equals(Long.valueOf(195))) {
				
			}else {
				return rastojanjeStandardno(javljanje.getLat(), javljanje.getLon(), javljanjePoslednje.getLat(), javljanjePoslednje.getLon());
			}
		}else {
			return 0;
		}*/
	}
	
	public double rastojanjeKoordinate(Javljanja javljanje, double lat, double lon) {
		if(javljanje.isValid() && javljanje.getBrzina() < 250 && !javljanje.getDatumVreme().after(new Date())) {
			//return rastojanjeSaVisinom(javljanje.getLat(), javljanje.getLon(), lat, lon, 0, 0);
			return rastojanjeSaModifikacijom(javljanje.getLat(), javljanje.getLon(), lat, lon);
		}
		return 0;
	}
	
	//različiti obračuni 
	
	@SuppressWarnings("unused")
	private double rastojanjeStandardno(double lat1, double lon1, double lat2, double lon2) {
		double dist = 0;
	    double R = 6371; //meters * 1000
	    double dLat = Math.toRadians(lat1 - lat2);
	    double dLng = Math.toRadians(lon1 - lon2);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(lat1)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    if(R * c > 5) {
	    	dist = (R * c);
	    }
		return dist;
	}
	
	private double rastojanjeSaModifikacijom(double lat1, double lon1, double lat2, double lon2) {
		double lon10 = Math.toRadians(lon1);
		double lon20 = Math.toRadians(lon2);
		double lat10 = Math.toRadians(lat1);
		double lat20 = Math.toRadians(lat2);
		double dlon = lon20 -lon10;
		double dlat = lat20 -lat10;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat10) * Math.cos(lat20) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		double r = 6371;
		return(c * r);
	}
	
	@SuppressWarnings("unused")
	private double rastojanjeSaVisinom(double lat1, double lon1, double lat2, double lon2, double visina1, double visina2) {
		double dist = 0;
	    double R = 6371000; //meters * 1000
	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    
	    double a = Math.sin(latDistance/2) * Math.sin(latDistance/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(lonDistance/2) * Math.sin(lonDistance/2);
	    
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    dist = R * c;
	    double height = visina1 - visina2;
	    dist = Math.pow(dist, 2) + Math.pow(height, 2);
	
		return Math.sqrt(dist);
	}
}

package pratiBaza.servis;

import java.sql.Timestamp;
import java.util.List;

import pratiBaza.pomocne.RadnoVremePutGPS;

public interface ProcedureServis {

	List<RadnoVremePutGPS> radnoVremePutGPS(int idObjekta, Timestamp pocetak, Timestamp kraj, int satiOd, int satiDo);
}

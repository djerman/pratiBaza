package pratiBaza.dao;

import java.sql.Timestamp;
import java.util.List;
import pratiBaza.pomocne.RadnoVremePutGPS;

public interface ProcedureDAO {

	List<RadnoVremePutGPS> radnoVremePutGPS(int idObjekta, Timestamp pocetak, Timestamp kraj, int satiOd, int satiDo);
}

package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.ProcedureDAO;
import pratiBaza.pomocne.RadnoVremePutGPS;

@Repository("proceduraDAO")
public class ProcedureDAOImpl implements ProcedureDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RadnoVremePutGPS> radnoVremePutGPS(int idObjekta, Timestamp pocetak, Timestamp kraj, int satiOd, int satiDo) {
		DateFormat datum = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat datumVreme = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("CALL radnoVremeGPS(:objekatId, :datumOd, :datumDo, :satiOd, :satiDo)");
		query.setParameter("objekatId", idObjekta);
		query.setParameter("datumOd", pocetak);
		query.setParameter("datumDo", kraj);
		query.setParameter("satiOd", satiOd);
		query.setParameter("satiDo", satiDo);
		List<RadnoVremePutGPS> lista = new ArrayList<RadnoVremePutGPS>();
		List<Object[]> redovi = query.list();
		for(Object[] red : redovi) {
			RadnoVremePutGPS rv = new RadnoVremePutGPS();
			try {
				rv.setDatum(datum.parse(red[0].toString()));
				rv.setPocetak(datumVreme.parse(red[1].toString()));
				rv.setKraj(datumVreme.parse(red[2].toString()));
				rv.setMaxBrzina(Float.parseFloat(red[3].toString()));
				rv.setPredjeniPut(Float.parseFloat(red[4].toString()));
				lista.add(rv);
			}catch (ParseException e) {
				System.out.println(e.toString());
			}
		}
		return lista;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
}

package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.SistemSesijeDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.SistemPretplatnici;
import pratiBaza.tabele.SistemSesije;

@Repository("sistemSesijaDAO")
public class SistemSesijeDAOImpl implements SistemSesijeDAO{
	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	public void unesiSesiju(SistemSesije sesija) {
		sesija.setVersion(0);
		sessionFactory.getCurrentSession().persist(sesija);
	}

	public void izmeniSesiju(SistemSesije sesija) {
		sesija.setVersion(sesija.getVersion() + 1);
		sessionFactory.getCurrentSession().update(sesija);
	}

	public void izbrisiSesiju(SistemSesije sesija) {
		sesija.setIzbrisan(true);
		sessionFactory.getCurrentSession().update(sesija);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SistemSesije> nadjiSveSesije(Korisnici korisnik) {
		ArrayList<SistemSesije> lista = new ArrayList<SistemSesije>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SistemSesije.class);
		if(!korisnik.getSistemPretplatnici().isSistem() || !korisnik.isSistem()) {
			criteria.add(Restrictions.eq("sistemPretplatnici", korisnik.getSistemPretplatnici()));
			criteria.add(Restrictions.eq("izbrisan", false));
			}
		if(korisnik.getOrganizacija() != null) {
			criteria.add(Restrictions.eq("organizacija", korisnik.getOrganizacija()));
			}
		criteria.addOrder(Order.asc("sistemPretplatnici"));
		criteria.addOrder(Order.asc("izbrisan"));
		criteria.addOrder(Order.desc("id"));
		ArrayList<SistemSesije> lista2 = (ArrayList<SistemSesije>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			return lista2;
		}else {
			return lista;
		}
	}

	public ArrayList<SistemSesije> nadjiSveSesijeKorisnika(Korisnici korisnik) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<SistemSesije> nadjiSveSesijeKorisnikaPoVremenu(Korisnici korisnik, Timestamp datumVremeOd,
			Timestamp datumVremeDo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<SistemSesije> nadjiSveSesijePretplatnika(SistemPretplatnici pretplatnik) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<SistemSesije> nadjiSveSesijePretplatnikaPoVremenu(SistemPretplatnici pratplatnika,
			Timestamp datumVremeOd, Timestamp datumVremDo) {
		// TODO Auto-generated method stub
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.EvidencijaVoznjiDAO;
import pratiBaza.tabele.EvidencijaVoznji;
import pratiBaza.tabele.Organizacije;
import pratiBaza.tabele.SistemPretplatnici;

@Repository("evidencijaDAO")
public class EvidencijaVoznjiDAOImpl implements EvidencijaVoznjiDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void unesiEvidenciju(EvidencijaVoznji evidencija) {
		evidencija.setVersion(0);
		evidencija.setIzmenjeno(new Timestamp((new Date()).getTime()));
		evidencija.setKreirano(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(evidencija);
	}

	@Override
	public void izmeniEvidenciju(EvidencijaVoznji evidencija) {
		evidencija.setVersion(evidencija.getVersion() + 1);
		evidencija.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(evidencija);
	}

	@Override
	public void izbrisiEvidenciju(EvidencijaVoznji evidencija) {
		sessionFactory.getCurrentSession().delete(evidencija);
	}

	@Override
	public EvidencijaVoznji nadjiEvidencijuPoId(int id) {
		String upit = "SELECT ev FROM EvidencijaVoznji ev WHERE ev.id = :id";
		TypedQuery<EvidencijaVoznji> query = sessionFactory.getCurrentSession().createQuery(upit, EvidencijaVoznji.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<EvidencijaVoznji> vratiEvidencije(SistemPretplatnici pretplatnik, Organizacije organizacija, String nalog,
			String registracija, String vozac, Timestamp datumVremeOd, Timestamp datumVremeDo) {
		ArrayList<EvidencijaVoznji> lista = new ArrayList<EvidencijaVoznji>();
		String upit = "SELECT fv FROM EvidencijaVoznji ev WHERE"
				+ " ev.sistemPretplatnici = :pretplatnik"
				+ " AND (:organizacija IS NULL OR ev.organizacija = :organizacija)"
				+ " AND (:nalog IS NULL OR ev.voziloNalog = :nalog)"
				+ " AND (:registracija IS NULL OR ev.registracijaVozila = :registracija)"
				+ " AND (:vozac IS NULL OR ev.vozac = :vozac)"
				+ " AND (:datumVremeOd IS NULL OR ev.datumVremePolaska >= :datumVremeOd)"
				+ " AND (:datumVremeDo IS NULL OR ev.datumVremeDolaska <= :datumVremeDo)"
				+ " ORDER BY ev.datumVremePolaska DESC";
		TypedQuery<EvidencijaVoznji> query = sessionFactory.getCurrentSession().createQuery(upit, EvidencijaVoznji.class);
		query.setParameter("pretplatnik", pretplatnik);
		query.setParameter("organizacija", organizacija);
		query.setParameter("nalog", nalog);
		query.setParameter("registracija", registracija);
		query.setParameter("vozac", vozac);
		query.setParameter("datumVremeOd", datumVremeOd);
		query.setParameter("datumVremeDo", datumVremeDo);
		
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			// TODO: handle exception
		}
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EvidencijaVoznji.class);
		criteria.add(Restrictions.eq("sistemPretplatnici", pretplatnik));
		if(organizacija != null) {
			criteria.add(Restrictions.eq("organizacija", organizacija));
		}
		if(nalog != null && !nalog.equals("")) {
			criteria.add(Restrictions.eq("voziloNalog", nalog));
		}
		if(registracija != null && !registracija.equals("")) {
			criteria.add(Restrictions.eq("registracijaVozila", registracija));
		}
		if(vozac != null && !vozac.equals("")) {
			criteria.add(Restrictions.eq("vozac", vozac));
		}
		if(datumVremeOd != null) {
			criteria.add(Restrictions.ge("datumVremePolaska", datumVremeOd));
		}
		if(datumVremeDo != null) {
			criteria.add(Restrictions.lt("datumVremeOdlaska", datumVremeDo));
		}
		criteria.addOrder(Order.desc("datumVremePolaska"));
		ArrayList<EvidencijaVoznji> lista2 = (ArrayList<EvidencijaVoznji>)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(lista2 != null) {
			lista.addAll(lista2);
		}*/
		return lista;
	}

}

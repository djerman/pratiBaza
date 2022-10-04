package pratiBaza.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pratiBaza.dao.VozaciDozvoleDAO;
import pratiBaza.tabele.Korisnici;
import pratiBaza.tabele.VozaciDozvole;

@Repository("vozacDozvolaDAO")
public class VozaciDozvoleDAOImpl implements VozaciDozvoleDAO{

	//mora da se doda i get i set metode za SessionFactory objekat
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void unesiVozacDozvola(VozaciDozvole dozvola) {
		dozvola.setVersion(0);
		dozvola.setKreirano(new Timestamp((new Date()).getTime()));
		dozvola.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().persist(dozvola);
	}

	@Override
	public void izmeniVozacDozvola(VozaciDozvole dozvola) {
		dozvola.setVersion(dozvola.getVersion() + 1);
		dozvola.setIzmenjeno(new Timestamp((new Date()).getTime()));
		sessionFactory.getCurrentSession().update(dozvola);
	}

	@Override
	public void izbrisiVozacDozvola(VozaciDozvole dozvola) {
		dozvola.setIzbrisan(true);
		izmeniVozacDozvola(dozvola);
	}

	@Override
	public VozaciDozvole nadjiVozacDozvolaPoId(int id) {
		String upit = "SELECT vd FROM VozaciDozvole vd WHERE vd.id = :id";
		TypedQuery<VozaciDozvole> query = sessionFactory.getCurrentSession().createQuery(upit, VozaciDozvole.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public VozaciDozvole nadjiVozacDozvoluPoVozacu(Korisnici vozac) {
		String upit = "SELECT vd FROM VozaciDozvole vd WHERE vd.vozaci = :vozac"
				+ " AND vd.izbrisan = false"
				+ " ORDER BY vd.id DESC";
		TypedQuery<VozaciDozvole> query = sessionFactory.getCurrentSession().createQuery(upit, VozaciDozvole.class);
		query.setParameter("vozaci", vozac);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<VozaciDozvole> nadjiSveVozacDozvolePoVozacu(Korisnici vozac) {
		ArrayList<VozaciDozvole> lista = new ArrayList<VozaciDozvole>();
		String upit = "SELECT vd FROM VozaciDozvole vd WHERE vd.vozaci = :vozac"
				+ " AND vd.izbrisan = false"
				+ " ORDER BY vd.id DESC";
		TypedQuery<VozaciDozvole> query = sessionFactory.getCurrentSession().createQuery(upit, VozaciDozvole.class);
		query.setParameter("vozaci", vozac);
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<VozaciDozvole> nadjiSveVozacDozvole(Korisnici korisnik) {
		ArrayList<VozaciDozvole> lista = new ArrayList<VozaciDozvole>();
		String upit = "SELECT vd FROM VozaciDozvole vd WHERE (:sistem = true OR vd.sistemPretplatnici = :pretplatnik)"
				+ " AND (:sistem = true OR vd.izbrisan = false)"
				+ " AND (:organizacija IS NULL OR vd.vozaci.organizacija = :organizacija"
				+ " ORDER BY vd.izbrisan ASC, vd.sistemPretplatnici.naziv ASC, vd.vaziDo DESC, vd.id DESC";
		TypedQuery<VozaciDozvole> query = sessionFactory.getCurrentSession().createQuery(upit, VozaciDozvole.class);
		query.setParameter("sistem", korisnik.isSistem());
		query.setParameter("pretplatnik", korisnik.getSistemPretplatnici());
		query.setParameter("organizacija", korisnik.getOrganizacija());
		try {
			lista.addAll(query.getResultList());
		}catch (Exception e) {
			e.printStackTrace();
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

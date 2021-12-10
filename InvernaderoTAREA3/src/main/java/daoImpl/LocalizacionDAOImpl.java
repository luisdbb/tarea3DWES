package daoImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import dao.LocalizacionDAO;
import modelo.Localizacion;
import util.Jpa;

public class LocalizacionDAOImpl implements LocalizacionDAO {

	public void insertarLocalizacion(Localizacion p) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void eliminarLocalizacion(Localizacion l) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			try {
				em.remove(l);
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			em.close();
		}
	}

	public void modificarLocalizacion(Localizacion l) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			l = em.merge(l);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public void verDetallesLocalizacion(Localizacion l) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			Localizacion loc = em.find(Localizacion.class, l.getId());
			System.out.println(loc);
		} finally {
			em.close();
		}
	}
	
	public Localizacion findById(int idloc) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			Localizacion l = em.find(Localizacion.class, idloc);
			return l;
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Localizacion> todasLocalizaciones() {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Localizacion.class));
			Query q = em.createQuery(cq);
			return (ArrayList<Localizacion>) q.getResultList();
		} finally {
			em.close();
		}
	}

	public ArrayList<Localizacion> filtrarParcelaPorLongitudLatitud(char La, char Lo) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Localizacion> cq = cb.createQuery(Localizacion.class);
			Root<Localizacion> c = cq.from(Localizacion.class);
			Predicate p1 = null, p2 = null;

			Path<String> companyCategoryNamePathLo = c.get("longitud");
			Path<String> companyCategoryNamePathLa = c.get("latitud");
			p1 = cb.equal(companyCategoryNamePathLa, La);
			p2 = cb.equal(companyCategoryNamePathLo, Lo);

			Predicate p3 = cb.and(p1, p2);
			cq.select(c).where(p3);
			return (ArrayList<Localizacion>) em.createQuery(cq).getResultList();
		} finally {
			em.close();
		}
	}



	public ArrayList<Localizacion> filtrarLocalizacionesPorLongitud(char l) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Localizacion> filtrarLocalizacionesPorLatitud(char l) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Localizacion> filtrarLocalizacionesPorLongitudLatitud(char latitud, double gradoslat,
			char longitud, double gradoslong) {
		// TODO Auto-generated method stub
		return null;
	}
}

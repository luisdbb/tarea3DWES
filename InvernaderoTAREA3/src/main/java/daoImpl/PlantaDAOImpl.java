package daoImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import dao.PlantaDAO;
import modelo.Planta;

import util.Jpa;

public class PlantaDAOImpl implements PlantaDAO {

	public void insertarPlanta(Planta p) {
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

	public void eliminarPlanta(Planta p) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			try {
				em.remove(p);
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {
			em.close();
		}
	}

	public void modificarPlanta(Planta planta) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			planta = em.merge(planta);
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

	public void verDetallesPlanta(Planta p) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			Planta planta = em.find(Planta.class, p.getId());
			System.out.println(planta);
		} finally {
			em.close();
		}
	}

	public Planta findById(int idplanta) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			Planta planta = em.find(Planta.class, idplanta);
			return planta;
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Planta> todasPlantas() {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Planta.class));
			Query q = em.createQuery(cq);
			return (ArrayList<Planta>) q.getResultList();
		} finally {
			em.close();
		}

	}

	public ArrayList<Planta> filtrarPlantaPorNombre(String nomb) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Planta> cq = cb.createQuery(Planta.class);
			Root<Planta> c = cq.from(Planta.class);
			Predicate p1 = null;
			if (nomb != null) {
				Path<String> companyCategoryNamePath = c.get("nombre");
				p1 = cb.like(companyCategoryNamePath, nomb + "%");
			}
			cq.select(c).where(p1);
			return (ArrayList<Planta>) em.createQuery(cq).getResultList();
		} finally {
			em.close();
		}
	}

}

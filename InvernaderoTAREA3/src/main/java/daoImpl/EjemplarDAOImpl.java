package daoImpl;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import dao.EjemplarDAO;
import modelo.Planta;
import modelo.Ejemplar;
import util.Jpa;

public class EjemplarDAOImpl implements EjemplarDAO {

	public void insertarEjemplar(Ejemplar ej) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(ej);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void eliminarEjemplar(Ejemplar ej) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			try {
				em.remove(ej);
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			em.close();
		}
	}
	
	public Ejemplar findById(int id) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			Ejemplar e = em.find(Ejemplar.class, id);
			return e;
		} finally {
			em.close();
		}
	}

	public void modificarEjemplar(Ejemplar eje) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			eje = em.merge(eje);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void verDetallesEjemplar(Ejemplar e) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			Ejemplar ej = em.find(Ejemplar.class, e.getId());
			System.out.println(ej);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Ejemplar> todosEjemplares() {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Ejemplar.class));
			Query q = em.createQuery(cq);
			return (ArrayList<Ejemplar>) q.getResultList();
		} finally {
			em.close();
		}
	}

	public ArrayList<Ejemplar> filtrarEjemplaresPorEdad(int edad) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Ejemplar> cq = cb.createQuery(Ejemplar.class);
			Root<Ejemplar> c = cq.from(Ejemplar.class);
			Predicate p1 = null;
			Path<String> companyCategoryNamePath = c.get("edad");
			p1 = cb.equal(companyCategoryNamePath, edad);
			cq.select(c).where(p1);
			return (ArrayList<Ejemplar>) em.createQuery(cq).getResultList();
		} finally {
			em.close();
		}
	}

	public ArrayList<Ejemplar> filtrarEjemplaresPorFechaCompra(Date fechaCompra) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Ejemplar> cq = cb.createQuery(Ejemplar.class);
			Root<Ejemplar> c = cq.from(Ejemplar.class);
			Predicate p1 = null;
			Path<String> companyCategoryNamePath = c.get("fechaCompra");
			p1 = cb.equal(companyCategoryNamePath, fechaCompra);
			cq.select(c).where(p1);
			return (ArrayList<Ejemplar>) em.createQuery(cq).getResultList();
		} finally {
			em.close();
		}
	}

	public ArrayList<Ejemplar> filtrarEjemplaresPorFechaPlantacion(Date fechaPlant) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Ejemplar> cq = cb.createQuery(Ejemplar.class);
			Root<Ejemplar> c = cq.from(Ejemplar.class);
			Predicate p1 = null;
			Path<String> companyCategoryNamePath = c.get("fechaPlantacion");
			p1 = cb.equal(companyCategoryNamePath, fechaPlant);
			cq.select(c).where(p1);
			return (ArrayList<Ejemplar>) em.createQuery(cq).getResultList();
		} finally {
			em.close();
		}
	}

	public void insertarEjemplarCompra(Ejemplar ej) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(ej);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void plantarEjemplar(Ejemplar e) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			e = em.merge(e);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void comprarEjemplar(Planta p) {
		// TODO Auto-generated method stub
		
	}


}

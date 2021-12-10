package daoImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import dao.ParcelaDAO;

import modelo.Parcela;
import modelo.PlantaParcela;
import util.Jpa;

public class ParcelaDAOImpl implements ParcelaDAO {

	public void insertarParcela(Parcela p) {
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

	public void eliminarParcela(Parcela p) {
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
	
	public void modificarParcela(Parcela p) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			p = em.merge(p);
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
	
	public void verDetallesParcela(Parcela p) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			Parcela parcela = em.find(Parcela.class, p.getId());
			System.out.println(parcela);

		} finally {
			em.close();
		}
	}


	public Parcela findById(int id) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			Parcela p = em.find(Parcela.class, id);
			return p;
		} finally {
			em.close();
		}
	}
	

	@SuppressWarnings("unchecked")
	public ArrayList<Parcela> todasParcelas() {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Parcela.class));
			Query q = em.createQuery(cq);
			return (ArrayList<Parcela>) q.getResultList();
		} finally {
			em.close();
		}
	}

	public ArrayList<Parcela> filtrarParcelasPorNombre(String nombre) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Parcela> cq = cb.createQuery(Parcela.class);
			Root<Parcela> c = cq.from(Parcela.class);
			Predicate p1 = null;
			if (nombre != null) {
				Path<String> companyCategoryNamePath = c.get("nombre");
				p1 = cb.like(companyCategoryNamePath, nombre + "%");
			}
			cq.select(c).where(p1);
			return (ArrayList<Parcela>) em.createQuery(cq).getResultList();
		} finally {
			em.close();
		}

	}

	public ArrayList<Parcela> filtrarParcelasPorArea(double area) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Parcela> cq = cb.createQuery(Parcela.class);
			Root<Parcela> c = cq.from(Parcela.class);
			Predicate p1 = null;
			Path<String> companyCategoryNamePath = c.get("area");
			p1 = cb.equal(companyCategoryNamePath, area);
			cq.select(c).where(p1);
			return (ArrayList<Parcela>) em.createQuery(cq).getResultList();
		} finally {
			em.close();
		}
	}

	public ArrayList<Parcela> filtrarParcelasPorPrivacidad(boolean privada) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Parcela> cq = cb.createQuery(Parcela.class);
			Root<Parcela> c = cq.from(Parcela.class);
			Predicate p1 = null;
			Path<String> companyCategoryNamePath = c.get("privada");
			p1 = cb.equal(companyCategoryNamePath, privada);
			cq.select(c).where(p1);
			return (ArrayList<Parcela>) em.createQuery(cq).getResultList();
		} finally {
			em.close();
		}
	}

	public void establecerAreaPlantacion(PlantaParcela pp) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(pp);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<PlantaParcela> todasPlantaParcela() {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(PlantaParcela.class));
			Query q = em.createQuery(cq);

			return (ArrayList<PlantaParcela>) q.getResultList();
		} finally {
			em.close();
		}
	}


}

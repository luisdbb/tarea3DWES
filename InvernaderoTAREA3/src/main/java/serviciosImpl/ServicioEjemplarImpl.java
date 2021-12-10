package serviciosImpl;

import java.sql.Date;
import java.util.ArrayList;

import modelo.Ejemplar;
import servicios.ServiciosEjemplar;

import util.*;

public class ServicioEjemplarImpl implements ServiciosEjemplar {
	public boolean crearEjemplar(Ejemplar ej) {
		if (Ejemplar.validarEjemplar(ej)) {
			DAOFactory.getDAOs().getEjemplarDAO().insertarEjemplar(ej);
			return true;
		} else {
			return false;
		}
	}

//	public boolean crearEjemplarCompra(Ejemplar ej) {
//		if (Ejemplar.validarEjemplar(ej)) {
//			DAOFactory.getDAOs().getEjemplarDAO().insertarEjemplarCompra(ej);
//			return true;
//		} else {
//			return false;
//		}
//	}

	public void eliminarEjemplar(Ejemplar e) {
		DAOFactory.getDAOs().getEjemplarDAO().eliminarEjemplar(e);
	}

	public boolean modificarEjemplar(Ejemplar eje) {
		if (Ejemplar.validarEjemplar(eje)) {
			DAOFactory.getDAOs().getEjemplarDAO().modificarEjemplar(eje);
			return true;
		} else {
			return false;
		}
	}

	public void verDetallesEjemplar(Ejemplar e) {
		DAOFactory.getDAOs().getEjemplarDAO().verDetallesEjemplar(e);
		return;
	}

	public Ejemplar findById(int id) {
		return DAOFactory.getDAOs().getEjemplarDAO().findById(id);
	}

	public ArrayList<Ejemplar> mostrarEjemplares() {
		return DAOFactory.getDAOs().getEjemplarDAO().todosEjemplares();
	}

	public ArrayList<Ejemplar> mostrarEjemplaresPorEdad(int edad) {
		return DAOFactory.getDAOs().getEjemplarDAO().filtrarEjemplaresPorEdad(edad);
	}

	public ArrayList<Ejemplar> mostrarEjemplaresPorFechaCompra(Date fechaCompra) {
		return DAOFactory.getDAOs().getEjemplarDAO().filtrarEjemplaresPorFechaCompra(fechaCompra);
	}

	public ArrayList<Ejemplar> mostrarEjemplaresPorFechaPlantacion(Date fechaPlant) {
		return DAOFactory.getDAOs().getEjemplarDAO().filtrarEjemplaresPorFechaPlantacion(fechaPlant);
	}

	public boolean plantarEjemplar(Ejemplar e) {
		if (Ejemplar.validarEjemplar(e)) {
			DAOFactory.getDAOs().getEjemplarDAO().plantarEjemplar(e);
			return true;
		} else {
			return false;
		}
	}

	public boolean comprarEjemplar(Ejemplar ej) {
		if (Ejemplar.validarEjemplar(ej)) {
			DAOFactory.getDAOs().getEjemplarDAO().insertarEjemplarCompra(ej);
			return true;
		} else {
			return false;
		}
	}

}

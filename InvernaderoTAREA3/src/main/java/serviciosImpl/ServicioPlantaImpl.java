package serviciosImpl;

import java.util.ArrayList;

import modelo.Planta;
import servicios.ServiciosPlanta;

import util.*;

public class ServicioPlantaImpl implements ServiciosPlanta {

	public boolean crearPlanta(Planta p) {
		if (Planta.validarPlanta(p)) {
			DAOFactory.getDAOs().getPlantaDAO().insertarPlanta(p);
			return true;
		} else {
			return false;
		}
	}

	public void eliminarPlanta(Planta p) {
		DAOFactory.getDAOs().getPlantaDAO().eliminarPlanta(p);
	}

	public boolean modificarPlanta(Planta p) {
		if (Planta.validarPlanta(p)) {
			DAOFactory.getDAOs().getPlantaDAO().modificarPlanta(p);
			return true;
		} else {
			return false;
		}
	}
	
	public void verDetallesPlanta(Planta p) {
		DAOFactory.getDAOs().getPlantaDAO().verDetallesPlanta(p);
		return;
	}

	public Planta findById(int id) {
		return DAOFactory.getDAOs().getPlantaDAO().findById(id);
	}

	public ArrayList<Planta> mostrarPlantas() {
		return DAOFactory.getDAOs().getPlantaDAO().todasPlantas();
	}

	public ArrayList<Planta> mostrarPlantasPorNombre(String nombre) {
		return DAOFactory.getDAOs().getPlantaDAO().filtrarPlantaPorNombre(nombre);
	}


}

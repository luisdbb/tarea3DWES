package serviciosImpl;

import java.util.ArrayList;

import modelo.Parcela;
import modelo.PlantaParcela;
import servicios.ServiciosParcela;

import util.*;

public class ServicioParcelaImpl implements ServiciosParcela {

	public boolean crearParcela(Parcela p) {
		if (Parcela.validarParcela(p)) {
			DAOFactory.getDAOs().getParcelaDAO().insertarParcela(p);
			return true;
		} else {
			return false;
		}
	}

	public void eliminarParcela(Parcela p) {
		DAOFactory.getDAOs().getParcelaDAO().eliminarParcela(p);
	}

	public boolean modificarParcela(Parcela p) {
		if (Parcela.validarParcela(p)) {
			DAOFactory.getDAOs().getParcelaDAO().modificarParcela(p);
			return true;
		} else {
			return false;
		}
	}

	public void verDetallesParcela(Parcela p) {
		DAOFactory.getDAOs().getParcelaDAO().verDetallesParcela(p);
		return;
	}

	public Parcela findById(int id) {
		return DAOFactory.getDAOs().getParcelaDAO().findById(id);
	}

	public ArrayList<Parcela> mostrarParcelas() {
		return DAOFactory.getDAOs().getParcelaDAO().todasParcelas();
	}

	public ArrayList<Parcela> mostrarParcelasPorNombre(String nombre) {
		return DAOFactory.getDAOs().getParcelaDAO().filtrarParcelasPorNombre(nombre);
	}

	public ArrayList<Parcela> mostrarParcelasPorArea(double area) {
		return DAOFactory.getDAOs().getParcelaDAO().filtrarParcelasPorArea(area);
	}

	public ArrayList<Parcela> mostrarParcelasPorPrivacidad(boolean privada) {
		return DAOFactory.getDAOs().getParcelaDAO().filtrarParcelasPorPrivacidad(privada);
	}

	public void establecerAreaPlantacion(PlantaParcela planpar) {
		DAOFactory.getDAOs().getParcelaDAO().establecerAreaPlantacion(planpar);
	}

}

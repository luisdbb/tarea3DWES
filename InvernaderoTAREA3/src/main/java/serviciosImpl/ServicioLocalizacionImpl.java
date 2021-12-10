package serviciosImpl;

import java.util.ArrayList;

import modelo.Localizacion;
import servicios.ServiciosLocalizacion;

import util.*;

public class ServicioLocalizacionImpl implements ServiciosLocalizacion {

	public boolean crearLocalizacion(Localizacion loc) {
		if (Localizacion.validarLocalizacion(loc)) {
			DAOFactory.getDAOs().getLocalizacionDAO().insertarLocalizacion(loc);
			return true;
		} else {
			return false;
		}
	}

	public void eliminarLocalizacion(Localizacion l) {
		DAOFactory.getDAOs().getLocalizacionDAO().eliminarLocalizacion(l);
	}

	public boolean modificarLocalizacion(Localizacion loc) {
		if (Localizacion.validarLocalizacion(loc)) {
			DAOFactory.getDAOs().getLocalizacionDAO().modificarLocalizacion(loc);
			return true;
		} else {
			return false;
		}
	}

	public Localizacion findById(int id) {
		return DAOFactory.getDAOs().getLocalizacionDAO().findById(id);
	}

	public void verDetallesLocalizacion(Localizacion l) {
		DAOFactory.getDAOs().getLocalizacionDAO().verDetallesLocalizacion(l);
		return;
	}

	public ArrayList<Localizacion> mostrarLocalizaciones() {
		return DAOFactory.getDAOs().getLocalizacionDAO().todasLocalizaciones();
	}

	public ArrayList<Localizacion> mostrarLocalizacionesPorLongitud(char l) {
		return DAOFactory.getDAOs().getLocalizacionDAO().filtrarLocalizacionesPorLongitud(l);
	}

	public ArrayList<Localizacion> mostrarLocalizacionesPorLatitud(char l) {
		return DAOFactory.getDAOs().getLocalizacionDAO().filtrarLocalizacionesPorLatitud(l);
	}

	public ArrayList<Localizacion> mostrarLocalizacionesPorLatitudLongitud(char latitud, double gradoslat,
			char longitud, double gradoslong) {
		return DAOFactory.getDAOs().getLocalizacionDAO().filtrarLocalizacionesPorLongitudLatitud(latitud, gradoslat,longitud, gradoslong);
	}

}

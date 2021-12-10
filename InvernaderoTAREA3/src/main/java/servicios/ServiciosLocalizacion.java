package servicios;

import java.util.ArrayList;

import modelo.Localizacion;

public interface ServiciosLocalizacion {

	public boolean crearLocalizacion(Localizacion loc);

	public void eliminarLocalizacion(Localizacion loc);

	public boolean modificarLocalizacion(Localizacion loc);

	public void verDetallesLocalizacion(Localizacion l);
	
	public Localizacion findById(int id);	

	public ArrayList<Localizacion> mostrarLocalizaciones();

	public ArrayList<Localizacion> mostrarLocalizacionesPorLongitud(char l);

	public ArrayList<Localizacion> mostrarLocalizacionesPorLatitud(char l);

	public ArrayList<Localizacion> mostrarLocalizacionesPorLatitudLongitud(char latitud, double gradoslat, char longitud, double gradoslong);
}

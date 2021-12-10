package dao;

import java.util.ArrayList;

import modelo.Localizacion;

public interface LocalizacionDAO {

	public void insertarLocalizacion(Localizacion l);

	public void eliminarLocalizacion(Localizacion l);

	public void modificarLocalizacion(Localizacion l);
	
	public void verDetallesLocalizacion(Localizacion l);

	public Localizacion findById(int idloc);
	
	public ArrayList<Localizacion> todasLocalizaciones();
	
	public ArrayList<Localizacion> filtrarLocalizacionesPorLongitud(char l);

	public ArrayList<Localizacion> filtrarLocalizacionesPorLatitud(char l);

	public ArrayList<Localizacion> filtrarLocalizacionesPorLongitudLatitud(char latitud, double gradoslat, char longitud, double gradoslong);


}

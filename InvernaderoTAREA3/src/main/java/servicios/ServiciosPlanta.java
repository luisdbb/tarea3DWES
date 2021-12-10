package servicios;

import java.util.ArrayList;

import modelo.Planta;

public interface ServiciosPlanta {

	public boolean crearPlanta(Planta p);

	public void eliminarPlanta(Planta p);

	public boolean modificarPlanta(Planta p);

	public void verDetallesPlanta(Planta p);
	
	public Planta findById(int id);

	public ArrayList<Planta> mostrarPlantas();

	public ArrayList<Planta> mostrarPlantasPorNombre(String nombre);

}

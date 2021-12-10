package dao;

import java.util.ArrayList;

import modelo.Planta;

public interface PlantaDAO {

	public void insertarPlanta(Planta p);

	public void eliminarPlanta(Planta p);

	public void modificarPlanta(Planta p);

	public void verDetallesPlanta(Planta p);
	
	public Planta findById(int id);

	public ArrayList<Planta> todasPlantas();

	public ArrayList<Planta> filtrarPlantaPorNombre(String nombre);
	
}

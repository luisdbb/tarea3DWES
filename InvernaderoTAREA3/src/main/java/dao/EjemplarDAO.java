package dao;

import java.sql.Date;
import java.util.ArrayList;

import modelo.Ejemplar;
import modelo.Planta;

public interface EjemplarDAO {

	public void insertarEjemplar(Ejemplar e);

	public void eliminarEjemplar(Ejemplar e);

	public void modificarEjemplar(Ejemplar e);

	public void verDetallesEjemplar(Ejemplar e);
	
	public Ejemplar findById(int id);

	public ArrayList<Ejemplar> todosEjemplares();

	public ArrayList<Ejemplar> filtrarEjemplaresPorEdad(int edad);

	public ArrayList<Ejemplar> filtrarEjemplaresPorFechaCompra(Date fechaCompra);

	public ArrayList<Ejemplar> filtrarEjemplaresPorFechaPlantacion(Date fechaPlant);

	public void insertarEjemplarCompra(Ejemplar ej);

	public void comprarEjemplar(Planta p);
	
	public void plantarEjemplar(Ejemplar e);
}

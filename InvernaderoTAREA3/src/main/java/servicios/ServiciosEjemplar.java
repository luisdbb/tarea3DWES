package servicios;

import java.sql.Date;
import java.util.ArrayList;

import modelo.Ejemplar;

public interface ServiciosEjemplar {

	public boolean crearEjemplar(Ejemplar e);

	public void eliminarEjemplar(Ejemplar e);

	public boolean modificarEjemplar(Ejemplar e);

	public void verDetallesEjemplar(Ejemplar e);

	public Ejemplar findById(int id);

	public ArrayList<Ejemplar> mostrarEjemplares();

	public ArrayList<Ejemplar> mostrarEjemplaresPorEdad(int edad);

	public ArrayList<Ejemplar> mostrarEjemplaresPorFechaCompra(Date fechaCompra);

	public ArrayList<Ejemplar> mostrarEjemplaresPorFechaPlantacion(Date fechaPlant);

	public boolean comprarEjemplar(Ejemplar e);

	public boolean plantarEjemplar(Ejemplar e);

}

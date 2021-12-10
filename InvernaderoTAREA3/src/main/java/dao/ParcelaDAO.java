package dao;

import java.util.ArrayList;

import modelo.Parcela;
import modelo.PlantaParcela;

public interface ParcelaDAO {

	public void insertarParcela(Parcela pa);

	public void eliminarParcela(Parcela p);

	public void modificarParcela(Parcela p);
	
	public void verDetallesParcela(Parcela p);

	public Parcela findById(int id);

	public ArrayList<Parcela> todasParcelas();

	public ArrayList<Parcela> filtrarParcelasPorNombre(String nombre);

	public ArrayList<Parcela> filtrarParcelasPorArea(double area);

	public ArrayList<Parcela> filtrarParcelasPorPrivacidad(boolean privada);

	public void establecerAreaPlantacion(PlantaParcela planpar);

}

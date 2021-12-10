package servicios;

import java.util.ArrayList;

import modelo.Parcela;
import modelo.PlantaParcela;

public interface ServiciosParcela {

	public boolean crearParcela(Parcela p);

	public void eliminarParcela(Parcela p);

	public boolean modificarParcela(Parcela p);
	
	public void verDetallesParcela(Parcela p);

	public Parcela findById(int id);	

	public ArrayList<Parcela> mostrarParcelas();

	public ArrayList<Parcela> mostrarParcelasPorNombre(String nombre);

	public ArrayList<Parcela> mostrarParcelasPorArea(double area);

	public ArrayList<Parcela> mostrarParcelasPorPrivacidad(boolean priv);

	public void establecerAreaPlantacion(PlantaParcela planpar);
}

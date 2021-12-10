package util;

import servicios.*;

import serviciosImpl.ServicioEjemplarImpl;
import serviciosImpl.ServicioLocalizacionImpl;
import serviciosImpl.ServicioParcelaImpl;
import serviciosImpl.ServicioPlantaImpl;

public class InvernaderoServiciosFactory {
	public static InvernaderoServiciosFactory servicios;
	
	public static InvernaderoServiciosFactory getServicios() {
		if (servicios ==null)
			servicios=new InvernaderoServiciosFactory();
		return servicios;
	}
	
	public ServiciosEjemplar getServiciosEjemplar() {
		return new ServicioEjemplarImpl();
	}
	
	public ServiciosPlanta getServiciosPlanta() {
		return new ServicioPlantaImpl();
	}
	
	public ServiciosParcela getServiciosParcela() {
		return new ServicioParcelaImpl();
	}
	
	public ServiciosLocalizacion getServiciosLocalizacion() {
		return new ServicioLocalizacionImpl();
	}
	
}

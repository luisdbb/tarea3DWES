package util;

import dao.*;
import daoImpl.*;

public class DAOFactory {

	private static DAOFactory f;

	// El patron factory proporciona metodos que devuelven los objetos complejos DAO
	public EjemplarDAO getEjemplarDAO() {
		return new EjemplarDAOImpl();
	}

	public LocalizacionDAO getLocalizacionDAO() {
		return new LocalizacionDAOImpl();
	}

	public PlantaDAO getPlantaDAO() {
		return new PlantaDAOImpl();
	}

	public ParcelaDAO getParcelaDAO() {
		return new ParcelaDAOImpl();
	}

	public static DAOFactory getDAOs() {
		if (f == null)
			f = new DAOFactory();
		return f;
	}
}

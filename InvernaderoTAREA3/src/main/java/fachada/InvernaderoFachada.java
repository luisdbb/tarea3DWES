package fachada;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.*;
import servicios.*;
import util.*;

public class InvernaderoFachada {
	private static InvernaderoFachada portal;
	DAOFactory factoriaDAO = DAOFactory.getDAOs();
	InvernaderoServiciosFactory factoriaServicios = InvernaderoServiciosFactory.getServicios();

	ServiciosEjemplar ejServ = factoriaServicios.getServiciosEjemplar();
	ServiciosPlanta plantaServ = factoriaServicios.getServiciosPlanta();
	ServiciosParcela parcelaServ = factoriaServicios.getServiciosParcela();
	ServiciosLocalizacion localServ = factoriaServicios.getServiciosLocalizacion();

	public static InvernaderoFachada getPortal() {
		if (portal == null)
			portal = new InvernaderoFachada();
		return portal;
	}

	public void mostrarMenuPrincipal() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Gestionar Plantas.");
		System.out.println("2.  Gestionar Ejemplares.");
		System.out.println("3.  Gestionar Parcelas.");
		System.out.println("4.  Gestionar Localizaciones.");
		System.out.println("5.  Comprar un ejemplar de una planta.");
		System.out.println("6.  Plantar un ejemplar.");
		System.out.println("7.  Establecer áreas de plantación.");
		System.out.println("8.  Salir");
	}

	public void mostrarMenuPrincipalPlantas() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Seleccionar Planta/s.");
		System.out.println("2.  Crear nueva Planta.");
		System.out.println("3.  Leer detalles de Planta.");
		System.out.println("4.  Modificar datos de Planta.");
		System.out.println("5.  Eliminar Planta.");
		System.out.println("6.  Volver al menu Principal");
	}

	private void mostrarMenuPrincipalEjemplares() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Seleccionar Ejemplar/es.");
		System.out.println("2.  Crear nuevo Ejemplar.");
		System.out.println("3.  Leer detalles de Ejemplar.");
		System.out.println("4.  Modificar datos de Ejemplar.");
		System.out.println("5.  Eliminar Ejemplar.");
		System.out.println("6.  Volver al menu Principal");
	}

	private void mostrarMenuPrincipalParcelas() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Seleccionar Parcela/s.");
		System.out.println("2.  Crear nueva Parcela.");
		System.out.println("3.  Leer detalles de Parcela.");
		System.out.println("4.  Modificar datos de Parcela.");
		System.out.println("5.  Eliminar Parcela.");
		System.out.println("6.  Volver al menu Principal");
	}

	private void mostrarMenuPrincipalLocalizaciones() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Seleccionar Localizacion/es.");
		System.out.println("2.  Crear nueva Localizacion.");
		System.out.println("3.  Leer detalles de Localizacion.");
		System.out.println("4.  Modificar datos de Localizacion.");
		System.out.println("5.  Eliminar Localizacion.");
		System.out.println("6.  Volver al menu Principal");
	}

	public void mostrarMenuGestionEjemplares() {
		Scanner in = new Scanner(System.in);
		System.out.println("Gestion de ejemplares");
		int opcion = 0;
		do {
			mostrarMenuPrincipalEjemplares();
			opcion = in.nextInt();
			if (opcion < 1 || opcion > 6) {
				System.out.println("Opcion incorrecta.");
				continue;
			}
			switch (opcion) {
			case 1:
				System.out.println("Seleccionar-filtrar ejemplar/es:");
				ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
				mostarMenuSeleccionarEjemplares();
				int filtroEjemplares = 0;
				filtroEjemplares = in.nextInt();
				switch (filtroEjemplares) {
				case 0:
					System.out.println("Seleccionados todos los ejemplares:");
					ejemplares = (ArrayList<Ejemplar>) ejServ.mostrarEjemplares();
					for (Ejemplar e : ejemplares) {
						System.out.println(e);
					}
					break;
				case 1:
					// TO-DO
					System.out.println("Introduzca la edad para filtrar:");
					int edadSel = in.nextInt();
					ejemplares = ejServ.mostrarEjemplaresPorEdad(edadSel);
					System.out.println("Seleccionados los ejemplares:");
					for (Ejemplar e : ejemplares) {
						System.out.println(e);
					}
					break;
				case 2:
					System.out.println("Introduzca la fecha de compra para filtrar:");
					Date fechaSel = Utilidades.leerFecha();
					ejemplares = ejServ.mostrarEjemplaresPorFechaCompra(fechaSel);
					System.out.println("Seleccionados los ejemplares:");
					for (Ejemplar e : ejemplares) {
						System.out.println(e);
					}
					break;
				case 3:
					System.out.println("Introduzca la fecha de plantacion para filtrar:");
					Date fechaSelec = Utilidades.leerFecha();
					ejemplares = ejServ.mostrarEjemplaresPorFechaPlantacion(fechaSelec);
					System.out.println("Seleccionados los ejemplares:");
					for (Ejemplar e : ejemplares) {
						System.out.println(e);
					}
					break;
				default:
					System.out.println("Error al seleccionar filtro.");
					break;
				}
				break;
			case 2:
				System.out.println("Crear nuevo Ejemplar:");
				try {
					Ejemplar e = Ejemplar.nuevoEjemplar();
					ejServ.crearEjemplar(e);
					System.out.println("El ejemplar " + e.toString() + " se ha creado en la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 3:
				System.out.println("Leer detalles de Ejemplar:");
				System.out.println("Seleccione el id del ejemplar:");
				ejemplares = ejServ.mostrarEjemplares();
				for (Ejemplar e : ejemplares) {
					System.out.println(e);
				}
				int idejemplar = 0;
				idejemplar = in.nextInt();
				Ejemplar ej = ejServ.findById(idejemplar);
				try {
					System.out.println("Los detalles del ejemplar con id=" + idejemplar + " son:");
					ejServ.verDetallesEjemplar(ej);
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 4:
				System.out.println("Modificar datos de Ejemplar");
				System.out.println("Seleccione el id del ejemplar:");
				ejServ.mostrarEjemplares();
				idejemplar = in.nextInt();
				Ejemplar ejemplar = ejServ.findById(idejemplar);
				///lll
				try {
					ejServ.modificarEjemplar(ejemplar);
					System.out.println("El ejemplar con id=" + idejemplar + " se ha modificado en la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 5:
				System.out.println("Eliminar Ejemplar:");
				System.out.println("Seleccione el id del ejemplar:");
				ejServ.mostrarEjemplares();
				idejemplar = 0;
				idejemplar = in.nextInt();
				Ejemplar eje = ejServ.findById(idejemplar);
				try {
					ejServ.eliminarEjemplar(eje);
					System.out.println("El ejemplar con id=" + idejemplar + " se ha eliminado de la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 6:
				break;
			}
		} while (opcion != 6);
	}

	public void mostrarMenuGestionPlantas() {
		Scanner in = new Scanner(System.in);
		System.out.println("Gestion de plantas");
		int opcion = 0;
		do {
			mostrarMenuPrincipalPlantas();
			opcion = in.nextInt();
			if (opcion < 1 || opcion > 6) {
				System.out.println("Opcion incorrecta.");
				continue;
			}
			switch (opcion) {
			case 1:
				System.out.println("Seleccionar-filtrar Planta/s:");
				ArrayList<Planta> plantas = new ArrayList<Planta>();
				mostarMenuSeleccionarPlantas();
				int filtroPlantas = 0;
				filtroPlantas = in.nextInt();
				switch (filtroPlantas) {
				case 0:
					System.out.println("Seleccionados todas las plantas:");
					plantas = plantaServ.mostrarPlantas();
					for (Planta p : plantas) {
						System.out.println(p);
					}
					break;
				case 1:
					System.out.println("Introduzca el nombre para filtrar:");
					String nombre = in.nextLine();
					plantas = plantaServ.mostrarPlantasPorNombre(nombre);
					System.out.println("Seleccionados las plantas:");
					for (Planta p : plantas) {
						System.out.println(p);
					}
					break;
				default:
					System.out.println("Error al seleccionar filtro.");
					break;
				}
				break;
			case 2:
				System.out.println("Crear nueva Planta:");
				try {
					Planta p = Planta.nuevaPlanta();
					plantaServ.crearPlanta(p);
					System.out.println("La planta " + p.toString() + " se ha creado en la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 3:
				System.out.println("Leer detalles de Planta:");
				System.out.println("Seleccione el id de la planta");
				plantas = plantaServ.mostrarPlantas();
				for (Planta p : plantas) {
					System.out.println(p);
				}
				int idplanta = 0;
				idplanta = in.nextInt();
				Planta p = plantaServ.findById(idplanta);
				try {
					System.out.println("Los detalles de la planta con id=" + idplanta + " son:");
					plantaServ.verDetallesPlanta(p);

				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 4:
				System.out.println("Modificar datos de Planta");
				System.out.println("Seleccione el id de la planta:");
				plantaServ.mostrarPlantas();
				idplanta = 0;
				idplanta = in.nextInt();
				Planta planta = plantaServ.findById(idplanta);
				
				//
				
				
				try {
					plantaServ.modificarPlanta(planta);
					System.out.println("La planta con id=" + idplanta + " se ha modificado en la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 5:
				System.out.println("Eliminar Planta:");
				System.out.println("Seleccione el id de la planta:");
				plantaServ.mostrarPlantas();
				idplanta = 0;
				idplanta = in.nextInt();
				planta = plantaServ.findById(idplanta);
				try {
					plantaServ.eliminarPlanta(planta);
					System.out.println("La planta con id=" + idplanta + " se ha eliminado de la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 6:
				break;
			}
		} while (opcion != 6);
	}

	public void mostrarMenuGestionLocalizaciones() {
		Scanner in = new Scanner(System.in);
		System.out.println("Gestion de localizaciones");
		int opcion = 0;
		do {
			mostrarMenuPrincipalLocalizaciones();
			opcion = in.nextInt();
			if (opcion < 1 || opcion > 6) {
				System.out.println("Opcion incorrecta.");
				continue;
			}
			switch (opcion) {
			case 1:
				System.out.println("Seleccionar-filtrar Localizacion/es:");
				ArrayList<Localizacion> localizaciones = new ArrayList<Localizacion>();
				mostarMenuSeleccionarLocalizaciones();
				int filtroLocalizaciones = 0;
				filtroLocalizaciones = in.nextInt();
				switch (filtroLocalizaciones) {
				case 0:
					System.out.println("Seleccionados todas las localizaciones:");
					localizaciones = localServ.mostrarLocalizaciones();
					for (Localizacion l : localizaciones) {
						System.out.println(l);
					}
					break;
				case 1:
					System.out.println("Introduzca los datos de la localizacion para filtrar:");
					System.out.println("Introduzca la longitud (E/O):");
					char longitud = in.nextLine().charAt(0);
					System.out.println("Introduzca la latitud (N/S):");
					char latitud = in.nextLine().charAt(0);
					System.out.println("Introduzca los grados para la longitud:");
					double glong = in.nextFloat();
					System.out.println("Introduzca los grados para la longitud:");
					double glat = in.nextFloat();
					localizaciones = localServ.mostrarLocalizacionesPorLatitudLongitud(latitud, glat, longitud, glong);
					System.out.println("Seleccionadas las localizaciones:");
					for (Localizacion l : localizaciones) {
						System.out.println(l);
					}
					break;
				default:
					System.out.println("Error al seleccionar filtro.");
					break;
				}
				break;
			case 2:
				System.out.println("Crear nueva Localizacion:");
				try {
					Localizacion l = Localizacion.nuevaLocalizacion();
					localServ.crearLocalizacion(l);
					System.out.println("La localizacion " + l.toString() + " se ha creado en la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 3:
				System.out.println("Leer detalles de Localización:");
				System.out.println("Seleccione el id de la localización");
				localizaciones =localServ.mostrarLocalizaciones();
				for (Localizacion l : localizaciones) {
					System.out.println(l);
				}
				int idlocalizacion = 0;
				idlocalizacion = in.nextInt();
				Localizacion localizacion = localServ.findById(idlocalizacion);
				try {
					System.out.println("Los detalles de la localizacion con id=" + idlocalizacion + " son:");
					localServ.verDetallesLocalizacion(localizacion);

				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 4:
				System.out.println("Modificar datos de Localización");
				System.out.println("Seleccione el id de la localización:");
				localServ.mostrarLocalizaciones();
				idlocalizacion = 0;
				idlocalizacion = in.nextInt();
				localizacion = localServ.findById(idlocalizacion);
				
				
				///sfdas
				try {
					localServ.modificarLocalizacion(localizacion);
					System.out.println("La localización con id=" + idlocalizacion + " se ha modificado en la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 5:
				System.out.println("Eliminar Localizacion:");
				System.out.println("Seleccione el id de la localizacion:");
				localServ.mostrarLocalizaciones();
				idlocalizacion = 0;
				idlocalizacion = in.nextInt();
				localizacion = localServ.findById(idlocalizacion);
				try {
					localServ.eliminarLocalizacion(localizacion);
					System.out.println("La localizacion con id=" + idlocalizacion + " se ha eliminado de la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 6:
				break;
			}
		} while (opcion != 6);
	}

	public void mostrarMenuGestionParcelas() {
		Scanner in = new Scanner(System.in);
		System.out.println("Gestion de parcelas");
		int opcion = 0;
		do {
			mostrarMenuPrincipalParcelas();
			opcion = in.nextInt();
			if (opcion < 1 || opcion > 6) {
				System.out.println("Opcion incorrecta.");
				continue;
			}
			switch (opcion) {
			case 1:
				System.out.println("Seleccionar-filtrar Parcela/s:");
				ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
				mostarMenuSeleccionarParcelas();
				int filtroParcelas = 0;
				filtroParcelas = in.nextInt();
				switch (filtroParcelas) {
				case 0:
					System.out.println("Seleccionadas todas las parcelas:");
					parcelas = (ArrayList<Parcela>) parcelaServ.mostrarParcelas();
					for (Parcela p : parcelas) {
						System.out.println(p);
					}
					break;
				case 1:
					System.out.println("Introduzca el nombre de la parcela para filtrar");
					String nombreSel = in.nextLine();
					parcelas = parcelaServ.mostrarParcelasPorNombre(nombreSel);
					System.out.println("Seleccionadas las parcelas:");
					for (Parcela p : parcelas) {
						System.out.println(p);
					}
					break;
				case 2:
					System.out.println("Introduzca el area de la parcela para filtrar");
					double areaSel = Utilidades.leerDouble();
					parcelas = parcelaServ.mostrarParcelasPorArea(areaSel);
					System.out.println("Seleccionadas las parcelas:");
					for (Parcela p : parcelas) {
						System.out.println(p);
					}
					break;
				case 3:
					System.out.println("Introduzca si desea solo parcelas privadas para filtrar");
					boolean selecc = Utilidades.leerBoolean();
					parcelas = parcelaServ.mostrarParcelasPorPrivacidad(selecc);
					System.out.println("Seleccionadas las parcelas:");
					for (Parcela p : parcelas) {
						System.out.println(p);
					}
					break;
				default:
					System.out.println("Error al seleccionar filtro.");
					break;
				}
				break;
			case 2:
				System.out.println("Crear nueva Parcela:");
				try {
					Parcela p = Parcela.nuevaParcela();
					parcelaServ.crearParcela(p);
					System.out.println("La parcela " + p.toString() + " se ha creado en la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 3:
				System.out.println("Leer detalles de Parcela:");
				System.out.println("Seleccione el id de la parcela");
				parcelas = parcelaServ.mostrarParcelas();
				for (Parcela p : parcelas) {
					System.out.println(p);
				}
				int idparcela = 0;
				idparcela = in.nextInt();
				Parcela parcela = parcelaServ.findById(idparcela);
				try {
					System.out.println("Los detalles de la parcela con id=" + idparcela + " son:");
					parcelaServ.verDetallesParcela(parcela);

				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 4:
				System.out.println("Modificar datos de Parcela");
				System.out.println("Seleccione el id de la parcela:");
				parcelaServ.mostrarParcelas();
				idparcela = 0;
				idparcela = in.nextInt();
				parcela = parcelaServ.findById(idparcela);
				
				
				///iii
				try {
					parcelaServ.modificarParcela(parcela);
					System.out.println("La parcela con id=" + idparcela + " se ha modificado en la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 5:
				System.out.println("Eliminar Parcela:");
				System.out.println("Seleccione el id de la parcela:");
				parcelaServ.mostrarParcelas();
				idparcela = 0;
				idparcela = in.nextInt();
				parcela = parcelaServ.findById(idparcela);
				try {
					parcelaServ.eliminarParcela(parcela);
					System.out.println("La parcela con id=" + idparcela + " se ha eliminado de la BD.");
				} catch (Exception e) {
					System.out.println("Se ha producido una excepcion: " + e.getMessage());
				}
				break;
			case 6:
				break;
			}
		} while (opcion != 6);
	}

	public void mostarMenuSeleccionarPlantas() {
		System.out.println("Ha seleccionado filtrar plantas.");
		System.out.println("Pulse 0 para todas las Plantas.");
		System.out.println("Pulse 1 para filtrar plantas por NOMBRE.");
	}

	public void mostarMenuSeleccionarEjemplares() {
		System.out.println("Ha seleccionado filtrar ejemplares.");
		System.out.println("Pulse 0 para todos los Ejemplares.");
		System.out.println("Pulse 1 para filtrar ejemplares por EDAD.");
		System.out.println("Pulse 2 para filtrar ejemplares por FECHA COMPRA.");
		System.out.println("Pulse 3 para filtrar ejemplares por FECHA PLANTACION.");
	}

	public void mostarMenuSeleccionarParcelas() {
		System.out.println("Ha seleccionado filtrar parcelas.");
		System.out.println("Pulse 0 para todas las Parcelas.");
		System.out.println("Pulse 1 para filtrar parcelas por NOMBRE.");
		System.out.println("Pulse 2 para filtrar parcelas por AREA.");
		System.out.println("Pulse 3 para filtrar parcelas por PRIVACIDAD.");
	}

	public void mostarMenuSeleccionarLocalizaciones() {
		System.out.println("Ha seleccionado filtrar localizaciones.");
		System.out.println("Pulse 0 para todas las Localizaciones.");
		System.out.println("Pulse 1 para filtrar localizaciones por LONGITUD/LATITUD.");
	}

	public void comprarEjemplarDePlanta() {
		System.out.println("Ha seleccionado comprar ejemplar de planta.");
	}

	public void plantarEjemplar() {
		System.out.println("Ha seleccionado establecer plantar ejemplar.");
	}

	public void establerAreasDePlantacion() {
		System.out.println("Ha seleccionado establecer areas de plantacion.");

	}

}

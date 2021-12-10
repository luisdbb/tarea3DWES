package principal;

import java.util.Scanner;
import fachada.InvernaderoFachada;

public class Principal {

	public static void main(String[] args) {
		InvernaderoFachada portal = InvernaderoFachada.getPortal();
		Scanner in = new Scanner(System.in);
		System.out.println("Programa de gestion de un invernadero");

		int opcion = 0;
		do {
			portal.mostrarMenuPrincipal();
			opcion = in.nextInt();
			if (opcion < 1 || opcion > 8) {
				System.out.println("Opcion incorrecta.");
				continue;
			}
			switch (opcion) {
			case 1:
				portal.mostrarMenuGestionPlantas();
				break;
			case 2:
				portal.mostrarMenuGestionEjemplares();
				break;
			case 3:
				portal.mostrarMenuGestionParcelas();
				break;
			case 4:
				portal.mostrarMenuGestionLocalizaciones();
				break;
			case 5:
				portal.comprarEjemplarDePlanta();
				break; // Comprar un ejemplar de una planta.
			case 6:
				portal.plantarEjemplar();
				break; // Plantar un ejemplar.
			case 7:
				portal.establerAreasDePlantacion();
				break; // Establecer áreas de plantación.
			case 8:
				break;
			default:
				System.out.println("Valor incorrecto!");
			}
		} while (opcion != 8);
		System.out.println("FIN");
	}

}

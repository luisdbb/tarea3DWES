package modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Ejemplar.findAll", query = "SELECT e FROM Ejemplar e"),
		@NamedQuery(name = "Ejemplar.findById", query = "SELECT e FROM Ejemplar e WHERE e.id = :id"),
		@NamedQuery(name = "Ejemplar.findByEdad", query = "SELECT e FROM Ejemplar e WHERE e.edad = :edad"),
		@NamedQuery(name = "Ejemplar.findByFechaCompra", query = "SELECT e FROM Ejemplar e WHERE e.fechaCompra = :fechaCompra"),
		@NamedQuery(name = "Ejemplar.findByFechaPlantacion", query = "SELECT e FROM Ejemplar e WHERE e.fechaPlantacion = :fechaPlantacion") })
@Table(name = "ejemplares")
public class Ejemplar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false)
	private int edad;
	@Column(nullable = false)
	private Date fechaCompra;
	private Date fechaPlantacion;

	@ManyToOne()
	@JoinColumn(name = "idPlanta")
	private Planta planta;

	@OneToOne
	@JoinColumn(name = "idLocalizacion", unique = true)
	private Localizacion localizacion;

	public Ejemplar() {
	}

	public Ejemplar(int id, int edad, Date fechaCompra) {
		this.id = id;
		this.edad = edad;
		this.fechaCompra = fechaCompra;
	}

	public Ejemplar(int id, int edad, Date fechaCompra, int idPlanta, Date fechaPlantacion) {
		this.id = id;
		this.edad = edad;
		this.fechaCompra = fechaCompra;
		this.fechaPlantacion = fechaPlantacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Date getFechaPlantacion() {
		return fechaPlantacion;
	}

	public void setFechaPlantacion(Date fechaPlantacion) {
		this.fechaPlantacion = fechaPlantacion;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + edad;
		result = prime * result + ((fechaCompra == null) ? 0 : fechaCompra.hashCode());
		result = prime * result + ((fechaPlantacion == null) ? 0 : fechaPlantacion.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ejemplar other = (Ejemplar) obj;
		if (edad != other.edad)
			return false;
		if (fechaCompra == null) {
			if (other.fechaCompra != null)
				return false;
		} else if (!fechaCompra.equals(other.fechaCompra))
			return false;
		if (fechaPlantacion == null) {
			if (other.fechaPlantacion != null)
				return false;
		} else if (!fechaPlantacion.equals(other.fechaPlantacion))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String ret = "Ejemplar [id=" + id + ", edad=" + edad + ", fechaCompra=" + fechaCompra + ", de la planta " + planta.getId();
		if (localizacion != null) {
			ret += " plantado el " + fechaPlantacion + " en idLocalizacion=" + localizacion.getId() + "]";
		}
		return ret;
	}

	public static boolean validarEjemplar(Ejemplar ej) {
		if (ej == null)
			return false;
		if (ej.getId() != 0) {
			if (ej.getId() < 0) {
				return false;
			}
		}
		if (ej.getEdad() < 0) {
			return false;
		}
		if (ej.getPlanta() == null)
			return false;
		if (ej.getFechaPlantacion() != null) {
			if (ej.getFechaCompra() != null) {
				if (ej.getFechaPlantacion().compareTo(ej.getFechaCompra()) < 0) {
					return false;
				} else if (ej.getLocalizacion() == null)
					return false;
			} else
				return false;
		}
		return true;
	}

	public static Ejemplar nuevoEjemplar() {
		Scanner in = new Scanner(System.in);
		Ejemplar ej;
		boolean correcto = false;
		do {
			ej = new Ejemplar();
			System.out.println("Introduzca los datos para un nuevo ejemplar:");
			System.out.println("fechaCompra (dd/mm/aaaa) Pulse intro para la fecha de hoy:");
			String fecha = in.nextLine();
			Date fechaCompra;
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			if (fecha.isEmpty())
				fechaCompra = java.sql.Date.valueOf(LocalDate.now());
			else
				fechaCompra = java.sql.Date.valueOf(LocalDate.parse(fecha, dateFormatter));
			ej.setFechaCompra(fechaCompra);
			System.out.println("Edad: ");
			int edad = in.nextInt();
			ej.setEdad(edad);
			correcto = Ejemplar.validarEjemplar(ej);
			if (!correcto) {
				System.out.println("Error de validaci�n: los datos introducidos no son correctos.");
			}
		} while (!correcto);
		return ej;
	}

}

package modelo;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.*;

@Entity
@NamedQueries({ @NamedQuery(name = "Planta.findAll", query = "SELECT p FROM Planta p"),
		@NamedQuery(name = "Planta.findById", query = "SELECT p FROM Planta p WHERE p.id = :id"),
		@NamedQuery(name = "Planta.findByNombre", query = "SELECT p FROM Planta p WHERE p.nombre = :nombre"), })
@Table(name = "plantas")
public class Planta {

	@Id
	@GeneratedValue
	private int id;
	@Column(nullable = false, unique = true)
	private String nombre;

	@OneToMany(mappedBy = "planta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Ejemplar> ejemplares;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "plantaparcela", joinColumns = { @JoinColumn(name = "idPlanta") }, inverseJoinColumns = {
			@JoinColumn(name = "idParcela") })
	private Set<Parcela> parcelas = new HashSet<Parcela>();

	public Planta() {
	}

	public Planta(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(Set<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public List<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(List<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

	public void addParcela(Parcela parcela) {
		this.getParcelas().add(parcela);
		parcela.getPlantas().add(this);
	}

	public void removeParcela(Parcela parcela) {
		this.getParcelas().remove(parcela);
		parcela.getPlantas().remove(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((parcelas == null) ? 0 : parcelas.hashCode());
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
		Planta other = (Planta) obj;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (parcelas == null) {
			if (other.parcelas != null)
				return false;
		} else if (!parcelas.equals(other.parcelas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String ret = "Planta [id=" + id + ", nombre=" + nombre;
		if (this.getParcelas().size() > 0) {
			ret += " aparece en las parcelas:";
			for (Parcela p : this.getParcelas())
				ret += "" + p.getId() + ", ";
		}
		ret += "]";
		return ret;
	}

	public static boolean validarPlanta(Planta p) {
		if (p == null)
			return false;
		if (p.getId() != 0) {
			if (p.getId() < 0) {
				return false;
			}
		}
		if (p.getNombre().isEmpty()) {
			return false;
		}
		return true;
	}

	public static Planta nuevaPlanta() {
		Scanner in = new Scanner(System.in);
		Planta pl;
		boolean correcto = false;
		do {
			pl = new Planta();
			System.out.println("Introduzca los datos para una nueva Planta:");
			System.out.println("nombre");
			String nombre = in.nextLine();
			pl.setNombre(nombre);
			correcto = Planta.validarPlanta(pl);
			if (!correcto) {
				System.out.println("Error de validaci�n: los datos introducidos no son correctos.");
			}
		} while (!correcto);
		return pl;
	}

}

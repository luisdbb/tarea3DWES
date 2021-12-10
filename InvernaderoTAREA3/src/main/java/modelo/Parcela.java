package modelo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	 @NamedQuery(name="Parcela.findAll",
	 query="SELECT p FROM Parcela p"),
	 @NamedQuery(name="Parcela.findById",
	 query="SELECT p FROM Parcela p WHERE p.id = :id"),
	 @NamedQuery(name="Parcela.findByNombre",
	 query="SELECT p FROM Parcela p WHERE p.nombre = :nombre"),
	 @NamedQuery(name="Parcela.findByArea",
	 query="SELECT p FROM Parcela p WHERE p.area = :area"),
	 @NamedQuery(name="Parcela.findByIsPrivada",
	 query="SELECT p FROM Parcela p WHERE p.privada = :fechaPlantacion")
	})
@Table(name = "parcelas")
public class Parcela {

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable=false, unique=true)
	private String nombre;
	@Column(nullable=false)
	private float area;
	@Column(nullable=false)
	private boolean privada;

	@ManyToMany(mappedBy = "parcelas")
	private Set<Planta> plantas = new HashSet<Planta>();

	public Parcela() {
	}

	public Parcela(int id, String nombre, float area, boolean privada) {
		this.id = id;
		this.nombre = nombre;
		this.area = area;
		this.privada = privada;
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

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public boolean isPrivada() {
		return privada;
	}

	public void setPrivada(boolean privada) {
		this.privada = privada;
	}

	public Set<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(Set<Planta> plantas) {
		this.plantas = plantas;
	}

	public void addPlanta(Planta planta) {
		planta.getParcelas().add(this);
		this.getPlantas().add(planta);
	}

	public void removePlanta(Planta planta) {
		planta.getParcelas().remove(this);
		this.getPlantas().remove(planta);
	}
	

	@Override
	public String toString() {
		String ret = "Parcela [id=" + id + ", nombre=" + nombre + ", area=" + area + ", ¿privada?" + privada ;
		if(this.getPlantas().size()>0) {
			ret+=" contiene plantas de los tipos";
			for(Planta p: this.getPlantas()) {
				ret += "" + p.getNombre() +", ";
			}
		}
		ret+="]";
		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(area);
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + (privada ? 1231 : 1237);
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
		Parcela other = (Parcela) obj;
		if (Float.floatToIntBits(area) != Float.floatToIntBits(other.area))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (privada != other.privada)
			return false;
		return true;
	}

	
	public static boolean validarParcela(Parcela p) {
		if(p==null) return false;
		if (p.getId() != 0) {
			if (p.getId() < 0) {
				return false;
			}
		}
		if (p.getNombre().isEmpty()) {
			return false;
		}
		if (p.getArea() < 0.0) {
			return false;
		}
		return true;
	}
	
	public static Parcela nuevaParcela() {
        Scanner in = new Scanner(System.in);
        Parcela p;
        boolean correcto = false;
        do {
            p = new Parcela();
            System.out.println("Introduzca los datos para una nueva Parcela:");
            System.out.println("Nombre:");
            String nombre = in.nextLine();
            p.setNombre(nombre);
            System.out.println("Area (xx.xx)");
            float area = Float.valueOf(in.nextLine().replace(",", "."));
            p.setArea(area);
            System.out.println("?La parcela es privada? (s/n");
            char resp = in.nextLine().charAt(0);
            boolean privada;
            if (resp == 'n' || resp == 'N') {
                privada = false;
            } else {
                privada = true;
            }
            p.setPrivada(privada);
            correcto = Parcela.validarParcela(p);
            if (!correcto) {
                System.out.println("Error de validaci�n: los datos introducidos no son correctos.");
            }
        } while (!correcto);
        return p;
    }

}

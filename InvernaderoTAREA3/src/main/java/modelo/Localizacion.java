package modelo;

import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQueries({
	 @NamedQuery(name="Localizacion.findAll",
	 query="SELECT l FROM Localizacion l"),
	 @NamedQuery(name="Localizacion.findById",
	 query="SELECT l FROM Localizacion l WHERE l.id = :id"),
	 @NamedQuery(name="Localizacion.findByLongitud",
	 query="SELECT l FROM Localizacion l WHERE l.longitud = :longitud"),
	 @NamedQuery(name="Localizacion.findByLatitud",
	 query="SELECT l FROM Localizacion l WHERE l.latitud = :latitud"),
	 @NamedQuery(name="LocalizacionVO.findByGradosLongitud",
	 query="SELECT l FROM Localizacion l WHERE l.gradosLongitud = :gradosLongitud"),
	 @NamedQuery(name="Localizacion.findByGradosLatitud",
	 query="SELECT l FROM Localizacion l WHERE l.gradosLatitud = :gradosLatitud")
	})
@Table(name = "localizaciones")
public class Localizacion {
	@Id
	@GeneratedValue
	private int id;

	@Column(nullable=false)
	private char latitud;
	@Column(nullable=false)
	private char longitud;
	@Column(nullable=false)
	private float gradosLatitud;
	@Column(nullable=false)
	private float gradosLongitud;

//	@OneToOne(mappedBy="localizacion")
//    private Ejemplar ejemplar;
	
	public Localizacion() {
	}

	public Localizacion(int id, char latitud, char longitud, float gradosLatitud, float gradosLongitud) {
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.gradosLatitud = gradosLatitud;
		this.gradosLongitud = gradosLongitud;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getLatitud() {
		return latitud;
	}

	public void setLatitud(char latitud) {
		this.latitud = latitud;
	}

	public char getLongitud() {
		return longitud;
	}

	public void setLongitud(char longitud) {
		this.longitud = longitud;
	}

	public float getGradosLatitud() {
		return gradosLatitud;
	}

	public void setGradosLatitud(float gradosLatitud) {
		this.gradosLatitud = gradosLatitud;
	}

	public float getGradosLongitud() {
		return gradosLongitud;
	}

	public void setGradosLongitud(float gradosLongitud) {
		this.gradosLongitud = gradosLongitud;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(gradosLatitud);
		result = prime * result + Float.floatToIntBits(gradosLongitud);
		result = prime * result + id;
		result = prime * result + latitud;
		result = prime * result + longitud;
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
		Localizacion other = (Localizacion) obj;
		if (Float.floatToIntBits(gradosLatitud) != Float.floatToIntBits(other.gradosLatitud))
			return false;
		if (Float.floatToIntBits(gradosLongitud) != Float.floatToIntBits(other.gradosLongitud))
			return false;
		if (id != other.id)
			return false;
		if (latitud != other.latitud)
			return false;
		if (longitud != other.longitud)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Localizacion [id=" + id + ", latitud=" + latitud + ", longitud=" + longitud + ", gradosLatitud="
				+ gradosLatitud + ", gradosLongitud=" + gradosLongitud + "]";
	}
	
	public static boolean validarLocalizacion(Localizacion l) {
		if (l == null)
			return false;
		if (l.getId() != 0) {
			if (l.getId() < 0) {
				return false;
			}
		}
		if (l.getLatitud() != 0) {
			if (l.getLatitud() != 'n' && l.getLatitud() != 'N' && l.getLatitud() != 's' && l.getLatitud() != 'S') {
				return false;
			}
		}
		if (l.getLongitud() != 0) {
			if (l.getLongitud() != 'e' && l.getLongitud() != 'o' && l.getLongitud() != 'E' && l.getLongitud() != 'O') {
				return false;
			}
		}
		return true;
	}
	
	public static Localizacion nuevaLocalizacion(){
        Scanner in = new Scanner(System.in);
        Localizacion loc;
        boolean correcto = false;
        do {
            loc = new Localizacion();
            System.out.println("Introduzca los datos para una nueva Localizacion:");
            System.out.println("Introduzca valor para la latitud (N/S):");
            char latitud = in.nextLine().charAt(0);
            loc.setLatitud(latitud);
            System.out.println("Introduzca valor para los grados de latitud (xx.xx)");
            float gradosLatitud = Float.valueOf(in.nextLine().replace(",", "."));
            loc.setGradosLatitud(gradosLatitud);
            System.out.println("Introduzca valor para la longitud (E/O):");
            char longitud = in.nextLine().charAt(0);
            loc.setLongitud(longitud);
            System.out.println("Introduzca valor para los grados de longitud (xx.xx)");
            float gradosLongitud = Float.valueOf(in.nextLine().replace(",", "."));;
            loc.setGradosLongitud(gradosLongitud);
            correcto = Localizacion.validarLocalizacion(loc);
            if (!correcto) {
                System.out.println("Error de validacion: los datos introducidos no son correctos.");
            }
        } while (!correcto);
        return loc;
    }

}

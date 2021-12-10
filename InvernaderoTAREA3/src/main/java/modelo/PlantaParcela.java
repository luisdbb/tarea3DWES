package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plantaparcela")
public class PlantaParcela {

	@Embeddable
	public static class Id implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Column(name = "idPlanta")
		private int idPlanta;

		@Column(name = "idParcela")
		private int idParcela;

		public Id() {
		}

		public Id(int idplanta, int idparcela) {
			this.idPlanta = idplanta;
			this.idParcela = idparcela;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + idParcela;
			result = prime * result + idPlanta;
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
			Id other = (Id) obj;
			if (idParcela != other.idParcela)
				return false;
			if (idPlanta != other.idPlanta)
				return false;
			return true;
		}

	}

	@EmbeddedId
	private Id id = new Id();

	@Column(nullable=false)
	private double porcentaje;

	@ManyToOne
	@JoinColumn(name = "idPlanta", insertable = false, updatable = false)
	private Planta planta;

	@ManyToOne
	@JoinColumn(name = "idParcela", insertable = false, updatable = false)
	private Parcela parcela;

	public PlantaParcela() {

	}

	public PlantaParcela(Planta planta, Parcela parcela, double porcentaje) {
		this.planta = planta;
		this.parcela = parcela;
		this.porcentaje = porcentaje;
		this.id.idPlanta = planta.getId();
		this.id.idParcela = parcela.getId();
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parcela == null) ? 0 : parcela.hashCode());
		result = prime * result + ((planta == null) ? 0 : planta.hashCode());
		long temp;
		temp = Double.doubleToLongBits(porcentaje);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		PlantaParcela other = (PlantaParcela) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parcela == null) {
			if (other.parcela != null)
				return false;
		} else if (!parcela.equals(other.parcela))
			return false;
		if (planta == null) {
			if (other.planta != null)
				return false;
		} else if (!planta.equals(other.planta))
			return false;
		if (Double.doubleToLongBits(porcentaje) != Double.doubleToLongBits(other.porcentaje))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return porcentaje+"% de idPlanta=" + this.id.idPlanta + "en idParcela=" + this.id.idParcela;
	}

}

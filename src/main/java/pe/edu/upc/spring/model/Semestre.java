package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Semestre")
public class Semestre implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSemestre;

	@Column(name="anioSemestre", nullable=false)
	private int anio;
	
	@Column(name="periodoSemestre", nullable=false)
	private int periodo;

	public Semestre() {
		super();
	}

	public Semestre(int idSemestre, int anio, int periodo) {
		super();
		this.idSemestre = idSemestre;
		this.anio = anio;
		this.periodo = periodo;
	}
	
	public String getCompleto() {
		return Integer.toString(anio) + "-" + Integer.toString(periodo);
	}

	public int getIdSemestre() {
		return idSemestre;
	}

	public void setIdSemestre(int idSemestre) {
		this.idSemestre = idSemestre;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CursoCarrera")
public class CursoCarrera implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCursoCarrera;
	
	@ManyToOne
	@JoinColumn(name="codigoCurso",nullable=true)
    private Curso curso;
	
	@ManyToOne
	@JoinColumn(name="idCarrera",nullable=true)
    private Carrera carrera;

	@Column(name="ciclo",nullable=false)
	private int ciclo;

	public CursoCarrera() {
		super();
	}

	public CursoCarrera(int idCursoCarrera, Curso curso, Carrera carrera, int ciclo) {
		super();
		this.idCursoCarrera = idCursoCarrera;
		this.curso = curso;
		this.carrera = carrera;
		this.ciclo = ciclo;
	}

	public int getIdCursoCarrera() {
		return idCursoCarrera;
	}

	public void setIdCursoCarrera(int idCursoCarrera) {
		this.idCursoCarrera = idCursoCarrera;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public int getCiclo() {
		return ciclo;
	}

	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}
}

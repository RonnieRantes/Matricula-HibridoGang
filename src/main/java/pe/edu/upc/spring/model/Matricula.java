package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Matricula")
public class Matricula implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMatricula;

    @ManyToOne
	@JoinColumn(name="seccionMatricula",nullable=true)
    private Seccion seccion;
    
    @ManyToOne
	@JoinColumn(name="estudianteMatricula",nullable=true)
    private Estudiante estudiante;
    
    @ManyToOne
	@JoinColumn(name="semestreMatricula",nullable=true)
    private Semestre semestre;

	public Matricula() {
		super();
	}

	public Matricula(int idMatricula, Seccion seccion, Estudiante estudiante, Semestre semestre) {
		super();
		this.idMatricula = idMatricula;
		this.seccion = seccion;
		this.estudiante = estudiante;
		this.semestre = semestre;
	}

	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
}

package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Estudiante")
public class Estudiante implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigoEstudiante",length=10,unique=true,nullable=false)
	private String codigo;
		
	@ManyToOne
	@JoinColumn(name="ingresoEstudiante",nullable=false)
	private Semestre ingreso;
	
	@ManyToOne
	@JoinColumn(name="carreraEstudiante",nullable=false)
	private Carrera carrera;
	
	@Column(name="nombresEstudiante",length=50,nullable=false)
	private String nombres;
	
	@Column(name="apellidosEstudiante",length=50,nullable=false)
	private String apellidos;

	@Column(name="correoeduEstudiante",length=50,nullable=false)
	private String correoedu;

	@Column(name="correoperEstudiante",length=50,nullable=true)
	private String correoper;

	@Column(name="telefonoEstudiante",length=9,nullable=false)
	private String telefono;

	public Estudiante() {
		super();
	}

	public Estudiante(String codigo, Semestre ingreso, Carrera carrera, String nombres, String apellidos,
			String correoedu, String correoper, String telefono) {
		super();
		this.codigo = codigo;
		this.ingreso = ingreso;
		this.carrera = carrera;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correoedu = correoedu;
		this.correoper = correoper;
		this.telefono = telefono;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Semestre getIngreso() {
		return ingreso;
	}

	public void setIngreso(Semestre ingreso) {
		this.ingreso = ingreso;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreoedu() {
		return correoedu;
	}

	public void setCorreoedu(String correoedu) {
		this.correoedu = correoedu;
	}

	public String getCorreoper() {
		return correoper;
	}

	public void setCorreoper(String correoper) {
		this.correoper = correoper;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}

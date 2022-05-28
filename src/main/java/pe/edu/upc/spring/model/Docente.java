package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Docente")
public class Docente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigoDocente",length=10,unique=true,nullable=false)
	private String codigo;
		
	@Column(name="nombresDocente",length=50,nullable=false)
	private String nombres;
	
	@Column(name="apellidosDocente",length=50,nullable=false)
	private String apellidos;

	@Column(name="correoeduDocente",length=50,nullable=false)
	private String correoedu;

	@Column(name="correoperDocente",length=50,nullable=true)
	private String correoper;

	@Column(name="telefonoDocente",length=9,nullable=false)
	private String telefono;

	public Docente() {
		super();
	}

	public Docente(String codigo, String nombres, String apellidos, String correoedu, String correoper,
			String telefono) {
		super();
		this.codigo = codigo;
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

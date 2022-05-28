package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigoUsuario",length=10,unique=true,nullable=false)
	private String codigoUsuario;

	@ManyToOne
	@JoinColumn(name="idRol", nullable=true)
	private Rol rol;
			
	@Column(name="contraseniaUsuario",length=100,nullable=false)
	private String contrasenia;
			
	private Boolean enabled;

	public Usuario() {
		super();
	}

	public Usuario(String codigoUsuario, Rol rol, String contrasenia, Boolean enabled) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.rol = rol;
		this.contrasenia = contrasenia;
		this.enabled = enabled;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}

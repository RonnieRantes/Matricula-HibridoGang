package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Curso")
public class Curso implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="codigoCurso",length=10,unique=true,nullable=false)
    private String codigo;

    @Column(name="nombreCurso",length=50,nullable=false)
    private String nombre;
    
    @Column(name="creditosCurso",nullable=false)
    private int creditos;

    @Column(name="obligatorioCurso",length=4,nullable=false)
    private String obligatorio;
    
    @Transient
    private boolean matriculado;

    public Curso() {
        super();
    }

	public Curso(String codigo, String nombre, int creditos, String obligatorio) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.creditos = creditos;
		this.obligatorio = obligatorio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public String getObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(String obligatorio) {
		this.obligatorio = obligatorio;
	}

	public boolean isMatriculado() {
		return matriculado;
	}

	public void setMatriculado(boolean matriculado) {
		this.matriculado = matriculado;
	}
}

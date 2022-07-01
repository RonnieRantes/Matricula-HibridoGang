package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Seccion")
public class Seccion implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="codigoSeccion",length=10,unique=true,nullable=false)
    private String codigo;

    @ManyToOne
	@JoinColumn(name="CursoSeccion",nullable=true)
    private Curso curso;

    @ManyToOne
	@JoinColumn(name="DocenteSeccion",nullable=true)
    private Docente docente;
    
    @Transient
    private int vacantes;
    
    @Transient
    private String horario;
    
    @Transient
    private boolean matriculado;

    public Seccion() {
        super();
    }

    public Seccion(String codigo, Curso curso, Docente docente) {
        super();
        this.codigo = codigo;
        this.curso = curso;
        this.docente = docente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

	public int getVacantes() {
		return vacantes;
	}

	public void setVacantes(int vacantes) {
		this.vacantes = vacantes;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public boolean isMatriculado() {
		return matriculado;
	}

	public void setMatriculado(boolean matriculado) {
		this.matriculado = matriculado;
	}
}

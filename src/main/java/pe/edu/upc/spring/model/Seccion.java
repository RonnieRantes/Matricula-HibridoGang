package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Seccion")
public class Seccion implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="codigoSeccion",length=10,unique=true,nullable=false)
    private String codigo;

    @ManyToOne
	@JoinColumn(name="CursoSeccion",nullable=false)
    private Curso curso;

    @ManyToOne
	@JoinColumn(name="DocenteSeccion",nullable=false)
    private Docente docente;

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

}

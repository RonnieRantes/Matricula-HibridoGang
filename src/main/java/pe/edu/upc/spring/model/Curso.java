package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Curso")
public class Curso implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="codigoCurso",length=10,unique=true,nullable=false)
    private String codigo;

    @Column(name="nombreCurso",length=50,nullable=false)
    private String nombre;

    @Column(name="obligatorioCurso",length=4,nullable=false)
    private String obligatorio;

    public Curso() {
        super();
    }

    public Curso(String codigo, String nombre, String obligatorio) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
        this.obligatorio = obligatorio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombres;
    }

    public void setNombre(String nombres) {
        this.nombres = nombres;
    }

    public String getObligatorio() {
        return apellidos;
    }

    public void setObligatorio(String obligatorio) {
        this.obligatorio = obligatorio;
    }

}

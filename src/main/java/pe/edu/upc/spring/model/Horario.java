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
import javax.persistence.Transient;

@Entity
@Table(name="Horario")
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idHorario;

    @ManyToOne
	@JoinColumn(name="seccionHorario",nullable=true)
    private Seccion seccion;
    
    @Column(name="diaHorario",nullable=false)
    private int dia;

    @Column(name="inicioHorario",nullable=false)
    private int inicio;

    @Column(name="finHorario",nullable=false)
    private int fin;
    
    @Transient
    private int inicioHoras;
    
    @Transient
    private int inicioMinutos;
    
    @Transient
    private int finHoras;

    @Transient
    private int finMinutos;

	public Horario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Horario(int idHorario, Seccion seccion, int dia, int inicio, int fin) {
		super();
		this.idHorario = idHorario;
		this.seccion = seccion;
		this.dia = dia;
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public String dia() {
		switch(this.dia) {
			case 1: return "Lun";
			case 2: return "Mar";
			case 3: return "Mie";
			case 4: return "Jue";
			case 5: return "Vie";
			case 6: return "Sáb";
			case 7: return "Dom";
		}
		return null;
	}
	
	public String horaInicio() {
		int minutos = this.inicio % 100;
		int horas = (this.inicio - minutos)/100;
		String str_minutos, str_horas;
		
		if(minutos < 10) str_minutos = "0" + minutos;
		else str_minutos = Integer.toString(minutos);
		
		if(horas < 10) str_horas = "0" + horas;
		else str_horas = Integer.toString(horas);
		
		return str_horas + ":" + str_minutos;
	}
	
	public String horaFin() {
		int minutos = this.fin % 100;
		int horas = (this.fin - minutos)/100;
		String str_minutos, str_horas;
		
		if(minutos < 10) str_minutos = "0" + minutos;
		else str_minutos = Integer.toString(minutos);
		
		if(horas < 10) str_horas = "0" + horas;
		else str_horas = Integer.toString(horas);
		
		return str_horas + ":" + str_minutos;
	}
	
	public String completo() {
		return dia() + " " + horaInicio() + "-" + horaFin();
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public int getInicioHoras() {
		return inicioHoras;
	}

	public void setInicioHoras(int inicioHoras) {
		this.inicioHoras = inicioHoras;
	}

	public int getInicioMinutos() {
		return inicioMinutos;
	}

	public void setInicioMinutos(int inicioMinutos) {
		this.inicioMinutos = inicioMinutos;
	}

	public int getFinHoras() {
		return finHoras;
	}

	public void setFinHoras(int finHoras) {
		this.finHoras = finHoras;
	}

	public int getFinMinutos() {
		return finMinutos;
	}

	public void setFinMinutos(int finMinutos) {
		this.finMinutos = finMinutos;
	}

	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	
	
}

package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Estudiante;

public interface IEstudianteService {
	public String Codigo(int anio, int periodo);
	public boolean registrar(Estudiante estudiante);
	public void eliminar(String codigoEstudiante);
	public Estudiante buscarId(String codigoEstudiante);
	public List<Estudiante> listar();
}

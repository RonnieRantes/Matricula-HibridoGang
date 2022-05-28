package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Carrera;

public interface ICarreraService {
	public boolean registrar(Carrera carrera);
	public void eliminar(int idCarrera);
	public Carrera buscarId(int idCarrera);
	public List<Carrera> listar();
}

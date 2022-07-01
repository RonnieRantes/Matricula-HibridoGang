package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.CursoCarrera;
import pe.edu.upc.spring.model.Curso;

public interface ICursoCarreraService {
	public boolean registrar(CursoCarrera cursoCarrera);
	public void eliminar(int id);
	public CursoCarrera buscarId(int id);
	public List<CursoCarrera> listar();
	public List<CursoCarrera> listarCursosDeCarrera(int idCarrera);
}

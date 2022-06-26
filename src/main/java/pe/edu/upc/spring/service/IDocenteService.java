package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Docente;

public interface IDocenteService {
	public boolean registrar(Docente docente);
	public void eliminar(String codigoDocente);
	public Docente buscarId(String codigoDocente);
	public List<Docente> listar();
}

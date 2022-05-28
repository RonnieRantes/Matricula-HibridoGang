package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Semestre;

public interface ISemestreService {
	public boolean registrar(Semestre semestre);
	public void eliminar(int idSemestre);
	public Semestre buscarId(int idSemestre);
	public List<Semestre> listar();
}

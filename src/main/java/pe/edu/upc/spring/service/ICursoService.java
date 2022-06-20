package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Curso;

public interface ICursoService {
    public boolean registrar(Curso curso);
    public void eliminar(int idCurso);
    public Curso buscarId(int idCurso);
    public List<Curso> listar();
}

package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Seccion;

public interface ISeccionService {
    public boolean registrar(Seccion seccion);
    public void eliminar(String idSeccion);
    public Seccion buscarId(String idSeccion);
    public List<Seccion> listar();
    public List<Seccion> listarSeccionesCurso(String codigoCurso);
}
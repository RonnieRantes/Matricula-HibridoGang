package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Seccion;

public interface ISeccionService {
    public boolean registrar(Seccion seccion);
    public void eliminar(int idSeccion);
    public Seccion buscarId(int idSeccion);
    public List<Seccion> listar();
}

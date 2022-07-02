package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Horario;

public interface IHorarioService {
    public boolean registrar(Horario Horario);
    public void eliminar(int idHorario);
    public Horario buscarId(int idHorario);
    public List<Horario> listar();
    public String horariosSeccion(String codigoSeccion);
}

package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Horario;

public interface IHorarioService {
    public boolean registrar(Horario Horario);
    public void eliminar(int idHorario);
    public Horario buscarId(int idHorario);
    public List<Horario> listar();
    public String horariosSeccion(String codigoSeccion);
	public List<Horario> horariosEstudiante(String codigoEstudiante);
	public List<Horario> horariosDocente(String codigoDocente);
}

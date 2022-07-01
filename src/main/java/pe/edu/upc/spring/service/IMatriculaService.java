package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Matricula;

public interface IMatriculaService {
    public boolean registrar(Matricula matricula);
    public void eliminar(int idMatricula);
    public Matricula buscarId(int idMatricula);
    public Matricula buscarCurso(String codigoCurso, String codigoUsuario, int idSemestre);
    public boolean comprobarSeccion(String codigoSeccion, int idSemestre, String codigoEstudiante, String codigoCurso);
    public int vacantesSeccion(String codigoSeccion, int idSemestre);
    public boolean comprobarCurso(String codigoCurso, String codigoEstudiante, int idSemestre);
    public int creditosAlumnoSemestre(String codigoEstudiante, int idSemestre);
    public List<Matricula> listar();
    public List<Matricula> listarMatriculasEstudianteSemestre(String codigoEstudiante, int idSemestre);
    public List<Matricula> listarMatriculasSemestre(int idSemestre);
}

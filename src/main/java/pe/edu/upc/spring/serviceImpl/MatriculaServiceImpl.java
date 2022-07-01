package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Matricula;
import pe.edu.upc.spring.repository.IMatriculaRepository;
import pe.edu.upc.spring.service.IMatriculaService;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

    @Autowired
    private IMatriculaRepository dMatricula;

    @Override
    @Transactional
    public boolean registrar(Matricula Matricula) {
        Matricula objMatricula = dMatricula.save(Matricula);
        return objMatricula!=null;
    }

    @Override
    @Transactional
    public void eliminar(int idMatricula) {
        dMatricula.deleteById(idMatricula);
    }

    @Override
    @Transactional(readOnly=true)
    public Matricula buscarId(int idMatricula) {
        Optional<Matricula> opt = dMatricula.findById(idMatricula);
        if(opt.isPresent()) {
            Matricula objMatricula = opt.get();
            return objMatricula;
        }
        return null;
    }
    
    @Override
    @Transactional(readOnly=true)
    public boolean comprobarSeccion(String codigoSeccion, int idSemestre, String codigoEstudiante, String codigoCurso) {
    	List<Matricula> lst = dMatricula.MatriculaEstudianteSemestreCurso(codigoEstudiante, idSemestre, codigoCurso);
    	if(lst.size() == 0) return false;
    	else return lst.get(0).getSeccion().getCodigo().equals(codigoSeccion);
    }
    
    @Override
    @Transactional(readOnly=true)
    public Matricula buscarCurso(String codigoCurso, String codigoUsuario, int idSemestre) {
    	return dMatricula.MatriculaEstudianteSemestreCurso(codigoUsuario, idSemestre, codigoCurso).get(0);
    }

    
    @Override
    @Transactional(readOnly=true)
    public int vacantesSeccion(String codigoSeccion, int idSemestre) {
    	return dMatricula.MatriculaSeccion(idSemestre, codigoSeccion).size();
    }
    
    @Override
    @Transactional(readOnly=true)
    public boolean comprobarCurso(String codigoEstudiante, String codigoCurso, int idSemestre) {
    	return dMatricula.MatriculaEstudianteSemestreCurso(codigoEstudiante, idSemestre, codigoCurso).size() > 0;
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Matricula> listar() {
        return dMatricula.findAll();
    }
    
    @Override
    @Transactional(readOnly=true)
    public int creditosAlumnoSemestre (String codigoEstudiante, int idSemestre) {
        List<Matricula> lst = dMatricula.MatriculaEstudianteSemestre(codigoEstudiante, idSemestre);
    	int creditos = 0;
        for(Matricula m : lst) {
    		creditos+=m.getSeccion().getCurso().getCreditos();
    	}
    	return creditos;
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Matricula> listarMatriculasSemestre(int idSemestre) {
        return dMatricula.MatriculaSemestre(idSemestre);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Matricula> listarMatriculasEstudianteSemestre(String codigoEstudiante, int idSemestre) {
        return dMatricula.MatriculaEstudianteSemestre(codigoEstudiante, idSemestre);
    }
}

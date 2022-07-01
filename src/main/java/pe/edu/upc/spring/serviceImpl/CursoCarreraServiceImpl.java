package pe.edu.upc.spring.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.CursoCarrera;
import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.repository.ICursoCarreraRepository;
import pe.edu.upc.spring.service.ICursoCarreraService;
import pe.edu.upc.spring.service.ICursoService;

@Service
public class CursoCarreraServiceImpl implements ICursoCarreraService {

    @Autowired
    private ICursoCarreraRepository dCurso;
    
    @Autowired
    private ICursoService cService;

    @Override
    @Transactional
    public boolean registrar(CursoCarrera cursoCarrera) {
        CursoCarrera objCurso = dCurso.save(cursoCarrera);
        return objCurso!=null;
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        dCurso.deleteById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public CursoCarrera buscarId(int id) {
        Optional<CursoCarrera> opt = dCurso.findById(id);
        if(opt.isPresent()) {
            CursoCarrera objCurso = opt.get();
            return objCurso;
        }
        return null;
    }

    @Override
    @Transactional(readOnly=true)
    public List<CursoCarrera> listar() {
        return dCurso.findAll();
    }
    
    @Override
    @Transactional(readOnly=true)
	public List<CursoCarrera> listarCursosDeCarrera(int idCarrera){
    	return dCurso.CursosEnCarrera(idCarrera);
    }
}

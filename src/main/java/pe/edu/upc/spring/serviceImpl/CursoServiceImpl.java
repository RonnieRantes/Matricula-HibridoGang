package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.repository.ICursoRepository;
import pe.edu.upc.spring.service.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    private ICursoRepository dCurso;

    @Override
    @Transactional
    public boolean registrar(Curso curso) {
        Curso objCurso = dCurso.save(curso);
        return objCurso!=null;
    }

    @Override
    @Transactional
    public void eliminar(int idCurso) {
        dCurso.deleteById(idCurso);
    }

    @Override
    @Transactional(readOnly=true)
    public Curso buscarId(int idCurso) {
        Optional<Curso> opt = dCurso.findById(idCurso);
        if(opt.isPresent()) {
            Curso objCurso = opt.get();
            return objCurso;
        }
        return null;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Curso> listar() {
        return dCurso.findAll();
    }
}

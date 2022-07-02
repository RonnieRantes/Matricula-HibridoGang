package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Seccion;
import pe.edu.upc.spring.repository.ISeccionRepository;
import pe.edu.upc.spring.service.ISeccionService;

@Service
public class SeccionServiceImpl implements ISeccionService {

    @Autowired
    private ISeccionRepository dSeccion;

    @Override
    @Transactional
    public boolean registrar(Seccion seccion) {
        Seccion objSeccion = dSeccion.save(seccion);
        return objSeccion!=null;
    }

    @Override
    @Transactional
    public void eliminar(String idSeccion) {
        dSeccion.deleteById(idSeccion);
    }

    @Override
    @Transactional(readOnly=true)
    public Seccion buscarId(String idSeccion) {
        Optional<Seccion> opt = dSeccion.findById(idSeccion);
        if(opt.isPresent()) {
            Seccion objSeccion = opt.get();
            return objSeccion;
        }
        return null;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Seccion> listar() {
        return dSeccion.findAll();
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Seccion> listarSeccionesCurso(String codigoCurso) {
        return dSeccion.SeccionesCurso(codigoCurso);
    }
	
	@Override
    @Transactional(readOnly=true)
    public List<Seccion> listarDocente(String codigoDocente) {
        return dSeccion.DocentesCurso(codigoDocente);
    }
}
package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Docente;
import pe.edu.upc.spring.repository.IDocenteRepository;
import pe.edu.upc.spring.service.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService {

	@Autowired
	private IDocenteRepository dDocente;

	@Override
	@Transactional
	public boolean registrar(Docente docente) {
		Docente objDocente = dDocente.save(docente);
		return objDocente!=null;
	}
	
	@Override
	@Transactional
	public void eliminar(String codigoDocente) {
		dDocente.deleteById(codigoDocente);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Docente buscarId(String codigoDocente) {
		Optional<Docente> opt = dDocente.findById(codigoDocente);
		if(opt.isPresent()) {
			Docente objDocente = opt.get();
			return objDocente;
		}
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Docente> listar() {
		return dDocente.findAll();
	}
}

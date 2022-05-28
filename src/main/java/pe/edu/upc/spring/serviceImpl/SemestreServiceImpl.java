package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Semestre;
import pe.edu.upc.spring.repository.ISemestreRepository;
import pe.edu.upc.spring.service.ISemestreService;

@Service
public class SemestreServiceImpl implements ISemestreService {

	@Autowired
	private ISemestreRepository dSemestre;

	@Override
	@Transactional
	public boolean registrar(Semestre semestre) {
		Semestre objSemestre = dSemestre.save(semestre);
		return objSemestre!=null;
	}
	
	@Override
	@Transactional
	public void eliminar(int idSemestre) {
		dSemestre.deleteById(idSemestre);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Semestre buscarId(int idSemestre) {
		Optional<Semestre> opt = dSemestre.findById(idSemestre);
		if(opt.isPresent()) {
			Semestre objSemestre = opt.get();
			return objSemestre;
		}
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Semestre> listar() {
		return dSemestre.findAll();
	}
}

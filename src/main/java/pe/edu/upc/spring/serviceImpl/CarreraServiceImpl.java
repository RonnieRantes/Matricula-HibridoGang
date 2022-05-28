package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Carrera;
import pe.edu.upc.spring.repository.ICarreraRepository;
import pe.edu.upc.spring.service.ICarreraService;

@Service
public class CarreraServiceImpl implements ICarreraService {

	@Autowired
	private ICarreraRepository dCarrera;

	@Override
	@Transactional
	public boolean registrar(Carrera carrera) {
		Carrera objCarrera = dCarrera.save(carrera);
		return objCarrera!=null;
	}
	
	@Override
	@Transactional
	public void eliminar(int idCarrera) {
		dCarrera.deleteById(idCarrera);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Carrera buscarId(int idCarrera) {
		Optional<Carrera> opt = dCarrera.findById(idCarrera);
		if(opt.isPresent()) {
			Carrera objCarrera = opt.get();
			return objCarrera;
		}
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Carrera> listar() {
		return dCarrera.findAll();
	}
}

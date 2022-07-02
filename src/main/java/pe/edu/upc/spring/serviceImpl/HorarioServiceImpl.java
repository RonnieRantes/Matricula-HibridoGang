package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Horario;
import pe.edu.upc.spring.repository.IHorarioRepository;
import pe.edu.upc.spring.service.IHorarioService;

@Service
public class HorarioServiceImpl implements IHorarioService {

	@Autowired
	private IHorarioRepository dHorario;

	@Override
	@Transactional
	public boolean registrar(Horario Horario) {
		Horario objHorario = dHorario.save(Horario);
		return objHorario!=null;
	}
	
	@Override
	@Transactional
	public void eliminar(int idHorario) {
		dHorario.deleteById(idHorario);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Horario buscarId(int idHorario) {
		Optional<Horario> opt = dHorario.findById(idHorario);
		if(opt.isPresent()) {
			Horario objHorario = opt.get();
			return objHorario;
		}
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Horario> listar() {
		return dHorario.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
    public String horariosSeccion(String codigoSeccion) {
		List<Horario> lst = dHorario.HorariosSeccion(codigoSeccion);
		String horarios = "";
		for(Horario h : lst) {
			horarios += h.completo() + "\n";
		}
		return horarios;
	}

}

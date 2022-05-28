package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Estudiante;
import pe.edu.upc.spring.repository.IEstudianteRepository;
import pe.edu.upc.spring.service.IEstudianteService;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private IEstudianteRepository dEstudiante;

	@Override
	public String Codigo(int anio, int periodo) {
		List<Estudiante> lst = dEstudiante.EstudiantesEnSemestre(anio, periodo);
		System.out.println("1");
		if(lst.size()>0) {
			Estudiante objEstudiante = lst.get(lst.size()-1);
			int cnt = 0, cod = Integer.parseInt(objEstudiante.getCodigo().substring(6))+ 1, aux = cod;
			String ceros = "";
			while (aux != 0) {
				aux /= 10;
				cnt++;
			}
			for(int i = 4; i > cnt; i--)ceros = ceros + '0';
			return "U" + anio + periodo + ceros + cod;
		}
		else return "U" + anio + periodo + "0001";
	}
	
	@Override
	@Transactional
	public boolean registrar(Estudiante docente) {
		Estudiante objEstudiante = dEstudiante.save(docente);
		return objEstudiante!=null;
	}
	
	@Override
	@Transactional
	public void eliminar(String codigoEstudiante) {
		dEstudiante.deleteById(codigoEstudiante);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Estudiante buscarId(String codigoEstudiante) {
		Optional<Estudiante> opt = dEstudiante.findById(codigoEstudiante);
		if(opt.isPresent()) {
			Estudiante objEstudiante = opt.get();
			return objEstudiante;
		}
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Estudiante> listar() {
		return dEstudiante.findAll();
	}
}

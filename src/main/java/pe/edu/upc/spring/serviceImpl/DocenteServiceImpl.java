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
	public String Codigo(int anio, int periodo) {
		List<Docente> lst = dDocente.DocentesContratadosEnSemestre(anio, periodo);
		System.out.println("1");
		if(lst.size()>0) {
			Docente objDocente = lst.get(lst.size()-1);
			int cnt = 0, cod = Integer.parseInt(objDocente.getCodigo().substring(6))+ 1, aux = cod;
			String ceros = "";
			while (aux != 0) {
				aux /= 10;
				cnt++;
			}
			for(int i = 4; i > cnt; i--)ceros = ceros + '0';
			return "P" + anio + periodo + ceros + cod;
		}
		else return "P" + anio + periodo + "0001";
	}

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

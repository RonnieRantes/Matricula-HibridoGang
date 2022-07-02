package pe.edu.upc.spring.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.model.CursoCarrera;
import pe.edu.upc.spring.model.Estudiante;
import pe.edu.upc.spring.model.Matricula;
import pe.edu.upc.spring.model.Seccion;
import pe.edu.upc.spring.service.ICarreraService;
import pe.edu.upc.spring.service.ICursoCarreraService;
import pe.edu.upc.spring.service.ICursoService;
import pe.edu.upc.spring.service.IDocenteService;
import pe.edu.upc.spring.service.IEstudianteService;
import pe.edu.upc.spring.service.IMatriculaService;
import pe.edu.upc.spring.service.ISeccionService;
import pe.edu.upc.spring.service.ISemestreService;
import pe.edu.upc.spring.service.IHorarioService;

@Controller
@RequestMapping("/uEstudiante")
public class PanelEstudiante {
	@Autowired
	private IEstudianteService eService;
	@Autowired
	private ICarreraService cService;
	@Autowired
	private ISemestreService sService;
	@Autowired
	private ICursoService cuService;
	@Autowired
	private IDocenteService dService;
	@Autowired
	private ISeccionService seService;
	@Autowired
	private ICursoCarreraService ccService;
	@Autowired
	private IMatriculaService mService;
	@Autowired
	private IHorarioService hService;

	@RequestMapping("/horario/")
	public String irPaginaHorario(Model model, Principal logeado) {
		model.addAttribute("listaHorarios", hService.horariosEstudiante(logeado.getName()));
		return "Admin/lstHorario2";
	}
	
	@RequestMapping("/matricula/")
	public String irPaginaMatricula(Principal logeado, Model model) {
		//Estudiante
		int semestre = 1;
		Estudiante usr = eService.buscarId(logeado.getName());
		List<CursoCarrera> lst = ccService.listarCursosDeCarrera(usr.getCarrera().getIdCarrera());
		List<Matricula> lst2 = mService.listarMatriculasEstudianteSemestre(usr.getCodigo(), semestre);
		for(CursoCarrera cc : lst) {
			cc.setMatriculado(mService.comprobarCurso(logeado.getName(), cc.getCurso().getCodigo(), semestre));
		}
    	for(Matricula m : lst2) {
    		m.getSeccion().setVacantes(mService.vacantesSeccion(m.getSeccion().getCodigo(), semestre));
    	}
		model.addAttribute("creditos", mService.creditosAlumnoSemestre(logeado.getName(), semestre));
		model.addAttribute("listaHabiles", lst);
		model.addAttribute("listaMatriculados", lst2);
		return "cursoshabiles";
	}

	@RequestMapping("/matricula/{idCurso}/secciones")
	public String irPaginaSeccionesCurso(@PathVariable String idCurso, Principal logeado, Model model, RedirectAttributes objRedir) {
		try {
            if (idCurso != null) {
            	int semestre = 1;
            	Curso curso = cuService.buscarId(idCurso);
            	Matricula objM = new Matricula(); 
            	if(mService.comprobarCurso(logeado.getName(), idCurso , semestre)) {
            		objM.setIdMatricula(mService.buscarCurso(idCurso, logeado.getName(), semestre).getIdMatricula());
            	}
            	else objM.setIdMatricula(0);
            	List<Seccion> lst = seService.listarSeccionesCurso(idCurso);
            	for(Seccion s : lst) {
            		s.setVacantes(mService.vacantesSeccion(s.getCodigo(), semestre));
            		s.setHorario(hService.horariosSeccion(s.getCodigo()));
            		s.setMatriculado(mService.comprobarSeccion(s.getCodigo(), semestre, logeado.getName(), idCurso));
            	}
            	model.addAttribute("listaSecciones", lst);
        		model.addAttribute("matricula", objM);
        		model.addAttribute("titulo", "Selección de sección");
        		model.addAttribute("btn", "Registrar");
        		model.addAttribute("asignatura", "Asignatura: " + curso.getCodigo() + " - " + curso.getNombre());
        		return "consultaseccion";
            }
        }
        catch(Exception ex) {
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
        }
        return "redirect:/uEstudiante/matricula/";
	}
}

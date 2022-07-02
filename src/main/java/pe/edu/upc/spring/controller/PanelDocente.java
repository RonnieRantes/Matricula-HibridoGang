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
@RequestMapping("/uDocente")
public class PanelDocente {
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
	
	@RequestMapping("/secciones/")
	public String irPaginaSecciones(Principal logeado, Model model, RedirectAttributes objRedir) {
		List<Seccion> lst = seService.listarDocente(logeado.getName());
		int semestre = 1;
		for(Seccion s : lst) {
			s.setVacantes(mService.vacantesSeccion(s.getCodigo(), semestre));
			s.setHorario(hService.horariosSeccion(s.getCodigo()));
		}
		model.addAttribute("listaSecciones", lst);
		model.addAttribute("titulo", "Secciones");
		model.addAttribute("btn", "Registrar");
		return "consultasecciondocente";
	}
	
	@RequestMapping("/seccion/{idSeccion}")
	public String irPaginaAlumnosSeccion(@PathVariable String idSeccion, Principal logeado, Model model, RedirectAttributes objRedir) {
		try {
            if (idSeccion != null) {
				int semestre = 1;
				Seccion objS = seService.buscarId(idSeccion);
            	model.addAttribute("listaEstudiantes", eService.listarSeccion(idSeccion, semestre));
        		model.addAttribute("titulo", "Alumnos");
        		model.addAttribute("btn", "Registrar");
        		model.addAttribute("asignatura", "Sección: " + objS.getCurso().getCodigo() + " - " + objS.getCurso().getNombre() + "-" + objS.getCodigo());
        		return "consultaseccionalumnos";
            }
        }
        catch(Exception ex) {
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
        }
        return "redirect:/uDocente/secciones/";
	}
}

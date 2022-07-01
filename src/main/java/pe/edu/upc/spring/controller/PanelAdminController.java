package pe.edu.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.CursoCarrera;
import pe.edu.upc.spring.service.ICarreraService;
import pe.edu.upc.spring.service.ICursoCarreraService;
import pe.edu.upc.spring.service.ICursoService;
import pe.edu.upc.spring.service.IDocenteService;
import pe.edu.upc.spring.service.IEstudianteService;
import pe.edu.upc.spring.service.ISeccionService;
import pe.edu.upc.spring.service.ISemestreService;

@Controller
@RequestMapping("/admin")
public class PanelAdminController {
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

	@RequestMapping("/estudiantes/")
	public String irPaginaEstudiantes(Model model) {
		model.addAttribute("listaEstudiantes", eService.listar());
		return "Admin/lstEstudiante";
	}

	@RequestMapping("/carreras/")
	public String irPaginaCarreras(Model model) {
		model.addAttribute("listaCarreras", cService.listar());
		return "Admin/lstCarrera";
	}

	@RequestMapping("/semestres/")
	public String irPaginaSemestres(Model model) {
		model.addAttribute("listaSemestres", sService.listar());
		return "Admin/lstSemestre";
	}
	
	@RequestMapping("/cursos/")
	public String irPaginaCursos(Model model) {
		model.addAttribute("listaCursos", cuService.listar());
		return "Admin/lstCurso";
	}
	
	@RequestMapping("/secciones/")
	public String irPaginaSecciones(Model model) {
		model.addAttribute("listaSecciones", seService.listar());
		return "Admin/lstSeccion";
	}

	@RequestMapping("/docentes/")
	public String irPaginaDocentes(Model model) {
		model.addAttribute("listaDocentes", dService.listar());
		return "Admin/lstDocente";
	}
	
	@RequestMapping("/carreras/{idCarrera}")
	public String irPaginaCursosCarrera(@PathVariable int idCarrera, Model model, RedirectAttributes objRedir) {
		try {
            if (idCarrera > 0) {
            	System.out.println("SIZE: " + ccService.listarCursosDeCarrera(idCarrera).size());
        		model.addAttribute("listaCursos", ccService.listarCursosDeCarrera(idCarrera));
        		model.addAttribute("carrera", cService.buscarId(idCarrera));
        		return "Admin/lstCursosCarrera";
            }
        }
        catch(Exception ex) {
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
        }
        return "redirect:/admin/carreras/";
	}
	
	@RequestMapping("/carreras/{idCarrera}/nuevo-curso")
	public String irPaginaAgregarCursosCarrera(@PathVariable int idCarrera, Model model, RedirectAttributes objRedir) {
		try {
            if (idCarrera > 0) {
            	CursoCarrera cursoC = new CursoCarrera();
            	cursoC.setCarrera(cService.buscarId(idCarrera));
                cursoC.setIdCursoCarrera(0);
            	model.addAttribute("listaCursos", cuService.listar());
        		model.addAttribute("curso", cursoC);
        		model.addAttribute("titulo", cursoC.getCarrera().getNombre());
        		model.addAttribute("btn", "Registrar");
        		return "Entidad/cursoCarrera";
            }
        }
        catch(Exception ex) {
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
        }
        return "redirect:/admin/carreras/";
	}
	
	@RequestMapping("/carreras/{idCarrera}/{idCursoCarrera}")
	public String irPaginaAgregarCursosCarrera(@PathVariable int idCarrera, @PathVariable int idCursoCarrera, Model model, RedirectAttributes objRedir) {
		try {
            if (idCarrera > 0) {
            	CursoCarrera cursoC = ccService.buscarId(idCursoCarrera);
            	model.addAttribute("listaCursos", cuService.listar());
        		model.addAttribute("curso", cursoC);
        		model.addAttribute("titulo", cursoC.getCarrera().getNombre());
        		model.addAttribute("btn", "Actualizar");
        		return "Entidad/cursoCarrera";
            }
        }
        catch(Exception ex) {
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
        }
        return "redirect:/admin/carreras/";
	}





}

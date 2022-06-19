package pe.edu.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.service.ICarreraService;
import pe.edu.upc.spring.service.IEstudianteService;
import pe.edu.upc.spring.service.IRolService;
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
}

package pe.edu.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.service.ICarreraService;
import pe.edu.upc.spring.service.ICursoService;
import pe.edu.upc.spring.service.IDocenteService;
import pe.edu.upc.spring.service.IEstudianteService;
import pe.edu.upc.spring.service.ISeccionService;
import pe.edu.upc.spring.service.ISemestreService;

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

	@RequestMapping("/horario/")
	public String irPaginaHorario(Model model) {
		model.addAttribute("listaEstudiantes", eService.listar());
		return "consultahorario";
	}
	@RequestMapping("/matricula/")
	public String irPaginaMatricula(Model model) {
		model.addAttribute("listaEstudiantes", eService.listar());
		return "cursoshabiles";
	}
}

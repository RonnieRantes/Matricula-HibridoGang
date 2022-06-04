package pe.edu.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.service.IEstudianteService;

@Controller
@RequestMapping("/admin")
public class PanelAdminController {
	@Autowired
	private IEstudianteService eService;
	
	@RequestMapping("/estudiantes/")
	public String irPaginaEstudiantes(Model model) {
		model.addAttribute("listaEstudiantes", eService.listar());
		return "lstEstudiante";
	}
}

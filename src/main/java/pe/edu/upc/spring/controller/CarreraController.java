package pe.edu.upc.spring.controller;

import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Carrera;
import pe.edu.upc.spring.service.ICarreraService;

@Controller
@RequestMapping("/carrera")
public class CarreraController {
	@Autowired
	private ICarreraService cService;
	
	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		model.addAttribute("carrera", new Carrera());
		model.addAttribute("btn", "Registrar");
		model.addAttribute("titulo", "NUEVA CARRRERA");
		return "Entidad/carrera";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Carrera objCarrera, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
		if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
		else {
			model.addAttribute("titulo", "Registrar carrera");
			boolean flag = cService.registrar(objCarrera);
			if (flag) return "redirect:/admin/carreras/";
			else model.addAttribute("mensaje", "Ocurrio un error");
		}
		return "Entidad/carrera";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	{
		Carrera objCarrera = cService.buscarId(id);
		if (objCarrera == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/admin/carreras/";
		}
		else {
			model.addAttribute("titulo", "Modificar carrera");
			model.addAttribute("carrera", objCarrera);
			model.addAttribute("btn", "Actualizar");
			return "Entidad/carrera";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam(value="id") Integer id, RedirectAttributes objRedir) {
		try {
			if (id!=null && id>0) cService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:/admin/carreras/"; 
	}
}
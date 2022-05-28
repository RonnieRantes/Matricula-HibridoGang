package pe.edu.upc.spring.controller;

import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Semestre;
import pe.edu.upc.spring.service.ISemestreService;

public class SemestreController {
	@Autowired
	private ISemestreService sService;
	
	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		model.addAttribute("semestre", new Semestre());
		return "/Entidad/semestre";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Semestre objSemestre, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
		if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
		else {
			boolean flag = sService.registrar(objSemestre);
			model.addAttribute("titulo", "REGISTRAR SEMESTRE");
			if (flag) return "redirect:/admin/semestres/";
			else model.addAttribute("mensaje", "Ocurrio un error");
		}
		return "/Entidad/semestre";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	{
		Semestre objSemestre = sService.buscarId(id);
		if (objSemestre == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/admin/semestres/";
		}
		else {
			model.addAttribute("titulo", "MODIFICAR SEMESTRE");
			model.addAttribute("semestre", objSemestre);
			return "/Entidad/semestre";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam(value="id") Integer id, RedirectAttributes objRedir) {
		try {
			if (id!=null && id>0) sService.eliminar(id);
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:/admin/semestres/"; 
	}
}
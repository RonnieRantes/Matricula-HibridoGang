package pe.edu.upc.spring.controller;

import org.apache.commons.lang3.text.WordUtils;
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

import pe.edu.upc.spring.model.Estudiante;
import pe.edu.upc.spring.model.Semestre;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.ISemestreService;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/semestre")
public class SemestreController {
	@Autowired
	private ISemestreService sService;
	
	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		model.addAttribute("semestre", new Semestre());
		model.addAttribute("titulo", "REGISTRAR SEMESTRE");
		model.addAttribute("btn", "Registrar");
		String[] opciones= new String[21];
		for (int i = 2010; i <= 2030; i++) {
			opciones[i-2010]= String.valueOf(i);
		}
		model.addAttribute("opciones", opciones);
		model.addAttribute("periodos", new String[]{"1","2"});
		return "/Entidad/semestre";
	}
	@SuppressWarnings("deprecation")
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
			String[] opciones= new String[21];
			for (int i = 2010; i <= 2030; i++) {
				opciones[i-2010]= String.valueOf(i);
			}
			model.addAttribute("opciones", opciones);
			model.addAttribute("periodos", new String[]{"1","2"});
			model.addAttribute("btn", "Actualizar");
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
package pe.edu.upc.spring.controller;

import org.apache.el.parser.ParseException;
import org.apache.commons.lang3.text.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import pe.edu.upc.spring.service.ICarreraService;
import pe.edu.upc.spring.service.IEstudianteService;
import pe.edu.upc.spring.service.IRolService;
import pe.edu.upc.spring.service.ISemestreService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

	@Autowired
	private ICarreraService cService;
	@Autowired
	private IEstudianteService eService;
	@Autowired
	private IRolService rService;
	@Autowired
	private ISemestreService sService;
	@Autowired
	private IUsuarioService uService;
	@Autowired 
	private PasswordEncoder encoder;
		
	@RequestMapping("/")
	public String irPaginaEntidad(Model model) {
		model.addAttribute("estudiante", new Estudiante());
		model.addAttribute("listaSemestres", sService.listar());
		model.addAttribute("listaCarreras", cService.listar());
		model.addAttribute("titulo", "Registrar estudiante");
		model.addAttribute("btn", "Registrar");
		return "/Entidad/estudiante";
	}
	@SuppressWarnings("deprecation")
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Estudiante objEstudiante, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
		if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
		else {
			Estudiante objE = eService.buscarId(objEstudiante.getCodigo());
			boolean flag = false;
			objEstudiante.setNombres(WordUtils.capitalizeFully(objEstudiante.getNombres()));
			objEstudiante.setApellidos(WordUtils.capitalizeFully(objEstudiante.getApellidos()));
			model.addAttribute("titulo", "Registrar estudiante");
			model.addAttribute("btn", "Registrar");
			//REGISTRAR
			if(objE == null) {
				Semestre objS = sService.buscarId(objEstudiante.getIngreso().getIdSemestre());
				objEstudiante.setCodigo(eService.Codigo(objS.getAnio(), objS.getPeriodo()));
				objEstudiante.setCorreoedu(objEstudiante.getCodigo() + "@upc.edu.pe");
				flag = eService.registrar(objEstudiante);
				if (flag) {
					String contrasenia = objEstudiante.getApellidos().substring(0,1) + objEstudiante.getNombres().substring(0,1) + objEstudiante.getCodigo().substring(1);
					Usuario objUsuario = new Usuario(objEstudiante.getCodigo(), rService.buscarId(3), encoder.encode(contrasenia), true);
					flag = uService.registrar(objUsuario);
					if(flag) return "redirect:/admin/estudiantes/";
				}
				model.addAttribute("mensaje", "Ocurrio un error");
			}
			//ACTUALIZAR
			else {
				objEstudiante.setCodigo(objE.getCodigo());
				objEstudiante.setCorreoedu(objE.getCorreoedu());
				flag = eService.registrar(objEstudiante);
				if(flag) return "redirect:/admin/estudiantes/";
				model.addAttribute("mensaje", "Ocurrio un error");
			}
		}
		return "/Entidad/estudiante";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable String id, Model model, RedirectAttributes objRedir)
	{
		Estudiante objEstudiante = eService.buscarId(id);
		if (objEstudiante == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/admin/estudiantes/";
		}
		else {
			model.addAttribute("listaSemestres", sService.listar());
			model.addAttribute("listaCarreras", cService.listar());
			model.addAttribute("titulo", "Modificar estudiante");
			model.addAttribute("btn", "Actualizar");
			model.addAttribute("estudiante", objEstudiante);
			return "/Entidad/estudiante";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam(value="id") String id, RedirectAttributes objRedir) {
		try {
			if (id!=null) {
				eService.eliminar(id);
				uService.eliminar(id);
			}
		}
		catch(Exception ex) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
		}
		return "redirect:/admin/estudiantes/"; 
	}

}

package pe.edu.upc.spring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/inicio")
public class InicioController {
	@Autowired
	private IUsuarioService uService;
	
	@RequestMapping("/")
	public String irInicio(Principal logeado, RedirectAttributes objRedir) {
		Usuario objUsuario = uService.buscarId(logeado.getName());
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/login/";
		}
		else {
			if (objUsuario.getRol().getNombre().equals("ROLE_ADMINISTRADOR")) return "inicio-admin";
			else if (objUsuario.getRol().getNombre().equals("ROLE_DOCENTE"))return "inicio-docente";
			else return "inicio-estudiante";
		}
	}
}

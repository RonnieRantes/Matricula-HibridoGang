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

import pe.edu.upc.spring.model.CursoCarrera;
import pe.edu.upc.spring.service.ICursoCarreraService;
import pe.edu.upc.spring.service.ICursoService;
import pe.edu.upc.spring.service.ICarreraService;

@Controller
@RequestMapping("/cursocarrera")
public class CursoCarreraController {
    @Autowired
    private ICursoCarreraService cService;
    @Autowired
    private ICursoService cuService;
    @Autowired
    private ICarreraService caService;

    @RequestMapping("/registrar")
    public String registrar(@ModelAttribute CursoCarrera objCurso, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
        if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
        else {
            model.addAttribute("titulo", "Registrar curso para carrera");
            model.addAttribute("btn", "Registrar");
			boolean flag = cService.registrar(objCurso);
            if (flag) return "redirect:/admin/carreras/" + objCurso.getCarrera().getIdCarrera();
			else model.addAttribute("mensaje", "Ocurrio un error");
        }
        return "Entidad/cursoCarrera";
    }
    @RequestMapping("/eliminar")
    public String eliminar(@RequestParam(value="id") int id, RedirectAttributes objRedir) {
        int aux = cService.buscarId(id).getIdCursoCarrera();
    	try {
            if (id > 0) {
                cService.eliminar(id);
            }
        }
        catch(Exception ex) {
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
        }
        return "redirect:/admin/carreras/" + aux;
    }
}
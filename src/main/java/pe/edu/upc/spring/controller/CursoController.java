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


import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.service.ICursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private ICursoService cService;

    @RequestMapping("/")
    public String irPaginaEntidad(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("titulo", "Registrar curso");
        model.addAttribute("btn", "Registrar");
        return "Entidad/curso";
    }
    @RequestMapping("/registrar")
    public String registrar(@ModelAttribute Curso objCurso, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
        if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
        else {
            model.addAttribute("titulo", "Registrar curso");
            model.addAttribute("btn", "Registrar");
			boolean flag = cService.registrar(objCurso);
            if (flag) return "redirect:/admin/cursos/";
			else model.addAttribute("mensaje", "Ocurrio un error");
        }
        return "Entidad/curso";
    }
    @RequestMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, Model model, RedirectAttributes objRedir)
    {
        Curso objCurso = cService.buscarId(id);
        if (objCurso == null) {
            objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
            return "redirect:/admin/cursos/";
        }
        else {
            model.addAttribute("titulo", "Modificar curso");
            model.addAttribute("btn", "Actualizar");
            model.addAttribute("curso", objCurso);
            return "Entidad/curso";
        }
    }
    @RequestMapping("/eliminar")
    public String eliminar(@RequestParam(value="id") String id, RedirectAttributes objRedir) {
        try {
            if (id!=null) {
                cService.eliminar(id);
            }
        }
        catch(Exception ex) {
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
        }
        return "redirect:/admin/cursos/";
    }
}
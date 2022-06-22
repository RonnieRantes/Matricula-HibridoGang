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
        return "curso";
    }
    @RequestMapping("/registrar")
    public String registrar(@ModelAttribute Curso objCurso, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
        if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
        else {
            objCurso.setCodigo(objCurso.getCodigo());
            objCurso.setNombre(objCurso.getNombre());
            objCurso.setObligatorio(objCurso.getObligatorio());
            model.addAttribute("titulo", "Registrar curso");
            model.addAttribute("btn", "Registrar");
        }
        return "curso";
    }
    @RequestMapping("/modificar/{id}")
    public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
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
            return "curso";
        }
    }
    @RequestMapping("/eliminar")
    public String eliminar(@RequestParam(value="id") Integer id, RedirectAttributes objRedir) {
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
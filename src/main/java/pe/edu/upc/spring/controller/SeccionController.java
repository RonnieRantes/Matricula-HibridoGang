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


import pe.edu.upc.spring.model.Seccion;
import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.model.Docente;
import pe.edu.upc.spring.service.ISeccionService;
import pe.edu.upc.spring.service.ICursoService;
import pe.edu.upc.spring.service.IDocenteService;

@Controller
@RequestMapping("/seccion")

public class SeccionController {
    @Autowired
    private ISeccionService sService;
    @Autowired
    private ICursoService cService;
    @Autowired
    private IDocenteService dService;

    @RequestMapping("/")
    public String irPaginaEntidad(Model model) {
        model.addAttribute("seccion", new Seccion());
        model.addAttribute("listaCursos", cService.listar());
        model.addAttribute("listaDocentes", dService.listar());
        model.addAttribute("titulo", "Registrar seccion");
        model.addAttribute("btn", "Registrar");
        return "seccion";
    }
    @SuppressWarnings("deprecation")
    @RequestMapping("/registrar")
    public String registrar(@ModelAttribute Seccion objSeccion, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
        if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
        else {
            Seccion objs = sService.buscarId(objSeccion.getCodigo());
            objSeccion.setCodigo(WordUtils.capitalizeFully(objSeccion.getCodigo()));
            objSeccion.setCurso(WordUtils.capitalizeFully(objSeccion.getCurso().getNombre()));
            objSeccion.setDocente(WordUtils.capitalizeFully(objSeccion.getDocente().getNombre()));
            model.addAttribute("titulo", "Registrar seccion");
            model.addAttribute("btn", "Registrar");
        }
        return "seccion";
    }
    @RequestMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, Model model, RedirectAttributes objRedir)
    {
        Seccion objSeccion = sService.buscarId(id);
        if (objSeccion == null) {
            objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
            return "redirect:/admin/secciones/";
        }
        else {
            model.addAttribute("listaCursos", cService.listar());
            model.addAttribute("listaDocentes", dService.listar());
            model.addAttribute("titulo", "Modificar seccion");
            model.addAttribute("btn", "Actualizar");
            model.addAttribute("seccion", objSeccion);
            return "seccion";
        }
    }
    @RequestMapping("/eliminar")
    public String eliminar(@RequestParam(value="id") String id, RedirectAttributes objRedir) {
        try {
            if (id!=null) {
                sService.eliminar(id);
            }
        }
        catch(Exception ex) {
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
        }
        return "redirect:/admin/secciones/";
    }
}
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


import pe.edu.upc.spring.model.Docente;
import pe.edu.upc.spring.model.Semestre;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.model.Rol;
import pe.edu.upc.spring.service.IDocenteService;
import pe.edu.upc.spring.service.IRolService;
import pe.edu.upc.spring.service.ISemestreService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/docente")

public class DocenteController {
    @Autowired
    private IDocenteService dService;
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
        model.addAttribute("docente", new Docente());
        model.addAttribute("titulo", "Registrar docente");
        model.addAttribute("btn", "Registrar");
        return "docente";
    }
    @SuppressWarnings("deprecation")
    @RequestMapping("/registrar")
    public String registrar(@ModelAttribute Docente objDocente, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
        if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
        else {
            Docente objD = dService.buscarId(objDocente.getCodigo());
            boolean flag = false;
            objDocente.setNombres(WordUtils.capitalizeFully(objDocente.getNombres()));
            objDocente.setApellidos(WordUtils.capitalizeFully(objDocente.getApellidos()));
            objDocente.setCorreoPer(WordUtils.capitalizeFully(objDocente.getCorreoper()));
            objDocente.setTelefono(objDocente.getTelefono())
            model.addAttribute("titulo", "Registrar docente");
            model.addAttribute("btn", "Registrar");
            //REGISTRAR
            if(objD == null) {
                Semestre objS;
                objDocente.setCodigo(dService.Codigo(objS.getAnio() + objS.getPeriodo()));
                objDocente.setCorreoedu(objDocente.getCodigo() + "@upc.edu.pe");
                flag = dService.registrar(objDocente);
                if (flag) {
                    String contrasenia = objDocente.getApellidos().substring(0,1) + objDocente.getNombres().substring(0,1) + objDocente.getCodigo().substring(1);
                    Usuario objUsuario = new Usuario(objDocente.getCodigo(), rService.buscarId(3), encoder.encode(contrasenia), true);
                    flag = uService.registrar(objUsuario);
                    if(flag) return "redirect:/admin/docentes/";
                }
                model.addAttribute("mensaje", "Ocurrio un error");
            }
            //ACTUALIZAR
            else {
                objDocente.setCodigo(objD.getCodigo());
                objDocente.setCorreoedu(objD.getCorreoedu());
                flag = dService.registrar(objDocente);
                if(flag) return "redirect:/admin/docentes/";
                model.addAttribute("mensaje", "Ocurrio un error");
            }
        }
        return "docente";
    }
    @RequestMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, Model model, RedirectAttributes objRedir)
    {
        Docente objDocente = dService.buscarId(id);
        if (objDocente == null) {
            objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
            return "redirect:/admin/docentes/";
        }
        else {
            model.addAttribute("titulo", "Modificar docente");
            model.addAttribute("btn", "Actualizar");
            model.addAttribute("docente", objDocente);
            return "docente";
        }
    }
    @RequestMapping("/eliminar")
    public String eliminar(@RequestParam(value="id") String id, RedirectAttributes objRedir) {
        try {
            if (id!=null) {
                dService.eliminar(id);
                uService.eliminar(id);
            }
        }
        catch(Exception ex) {
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
        }
        return "redirect:/admin/docentes/";
    }
}
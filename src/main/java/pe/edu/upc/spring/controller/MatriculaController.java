package pe.edu.upc.spring.controller;

import java.security.Principal;

import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Matricula;
import pe.edu.upc.spring.service.ICursoService;
import pe.edu.upc.spring.service.IEstudianteService;
import pe.edu.upc.spring.service.IMatriculaService;
import pe.edu.upc.spring.service.ISeccionService;
import pe.edu.upc.spring.service.ISemestreService;

@Controller
@RequestMapping("/matricula")
public class MatriculaController {
    @Autowired
    private IMatriculaService mService;
    @Autowired
    private IEstudianteService eService;
    @Autowired
    private ICursoService cService;
    @Autowired
    private ISemestreService seService;
    @Autowired
    private ISeccionService sService;

    @RequestMapping("/registrar")
    public String registrar(Model model, @ModelAttribute Matricula objM, Principal logeado, @RequestParam("seleccion") String seccion, RedirectAttributes objRedir) throws ParseException{
    	objM.setEstudiante(eService.buscarId(logeado.getName()));
    	objM.setSemestre(seService.buscarId(1));
    	objM.setSeccion(sService.buscarId(seccion));
		mService.registrar(objM);
        return "redirect:/uEstudiante/matricula/";
    }
    @RequestMapping("/eliminar")
    public String eliminar(@RequestParam(value="id") int id, Principal logeado, RedirectAttributes objRedir) {
        try {
            if (id>0) {
                mService.eliminar(id);
            }
        }
        catch(Exception ex) {
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
        }
        if(logeado.getName().equals("U000000000")) return "redirect:/admin/cursos/";
        else return "redirect:/uEstudiante/matricula/";
    }
}

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

import pe.edu.upc.spring.model.Horario;
import pe.edu.upc.spring.model.Seccion;
import pe.edu.upc.spring.service.IHorarioService;
import pe.edu.upc.spring.service.ISeccionService;

@Controller
@RequestMapping("/horario")
public class HorarioController {
    @Autowired
    private IHorarioService hService;
    @Autowired
    private ISeccionService sService;

    @RequestMapping("/")
    public String irPaginaEntidad(Model model) {
        model.addAttribute("horario", new Horario());
        model.addAttribute("listaSecciones", sService.listar());
        model.addAttribute("titulo", "Nuevo horario");
        model.addAttribute("btn", "Registrar");
        return "Entidad/horario";
    }
    @RequestMapping("/registrar")
    public String registrar(@ModelAttribute Horario objHorario, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException{
        if(binRes.hasErrors()) model.addAttribute("mensaje", "Ocurrio un error");
        else {
        	objHorario.setInicio(objHorario.getInicioHoras() * 100 + objHorario.getInicioMinutos());
        	objHorario.setFin(objHorario.getFinHoras() * 100 + objHorario.getFinMinutos());
        	model.addAttribute("titulo", "Nuevo horario");
            model.addAttribute("btn", "Registrar");
			boolean flag = hService.registrar(objHorario);
            if (flag) return "redirect:/admin/horarios/";
			else model.addAttribute("mensaje", "Ocurrio un error");

        }
        return "Entidad/seccion";
    }
    @RequestMapping("/modificar/{id}")
    public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
    {
        Horario objH = hService.buscarId(id);
        if (objH == null) {
            objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
            return "redirect:/admin/horarios/";
        }
        else {
            model.addAttribute("titulo", "Modificar horario");
            model.addAttribute("btn", "Actualizar");
			int iMinutos = objH.getInicio() % 100;
			int iHoras = (objH.getInicio() - iMinutos)/100;
			int fMinutos = objH.getFin() % 100;
			int fHoras = (objH.getFin() - fMinutos)/100;

			objH.setInicioHoras(iHoras);
			objH.setInicioMinutos(iMinutos);
			objH.setFinHoras(fHoras);
			objH.setFinMinutos(fMinuto);
            model.addAttribute("horario", objH);
            return "Entidad/horario";
        }
    }
    @RequestMapping("/eliminar")
    public String eliminar(@RequestParam(value="id") int id, RedirectAttributes objRedir) {
        try {
            if (id > 0) {
                hService.eliminar(id);
            }
        }
        catch(Exception ex) {
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
        }
        return "redirect:/admin/horarios/";
    }

}

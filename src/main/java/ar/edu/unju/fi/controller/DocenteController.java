package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.DocenteService;

@Controller
public class DocenteController {

    @Autowired
    Docente nuevoDocente;

    @Autowired
    DocenteService docenteService;

    @GetMapping("/formularioDocente")
    public ModelAndView getFormDocente() {
        ModelAndView modelView = new ModelAndView("formDocente");
        modelView.addObject("nuevoDocente", nuevoDocente);
        return modelView;
    }

    @GetMapping("/ListadoDocente")
    public ModelAndView getListaDocente() {
        ModelAndView modelView = new ModelAndView("listaDocente");
        modelView.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
        return modelView;
    }

    @PostMapping("/guardarDocente")
    public ModelAndView saveDocente(@ModelAttribute("nuevoDocente") Docente docenteParaGuardar) {
        docenteService.guardarDocente(docenteParaGuardar);
        ModelAndView modelView = new ModelAndView("listaDocente");
        modelView.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
        return modelView;
    }

    @GetMapping("/borrarDocente/{legajo}")
    public ModelAndView deleteDocente(@PathVariable(name = "legajo") String legajo) {
        docenteService.borrarDocente(legajo);
        ModelAndView modelView = new ModelAndView("listaDocente");
        modelView.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
        return modelView;
    }

    @GetMapping("/modificarDocente/{legajo}")
    public ModelAndView editDocente(@PathVariable(name = "legajo") String legajo) {
        Docente docenteParaModificar = docenteService.buscarDocente(legajo);
        ModelAndView modelView = new ModelAndView("formDocente");
        modelView.addObject("nuevoDocente", docenteParaModificar);
        return modelView;
    }

    @PostMapping("/modificarDocente")
    public ModelAndView updateDocente(@ModelAttribute("nuevoDocente") Docente docenteModificado) {
        docenteService.modificarDocente(docenteModificado);
        ModelAndView modelView = new ModelAndView("listaDocente");
        modelView.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
        return modelView;
    }
}

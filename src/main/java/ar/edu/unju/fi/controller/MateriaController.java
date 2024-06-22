package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.service.MateriaService;
import ar.edu.unju.fi.model.Materia;

@Controller
public class MateriaController {
    
    @Autowired
    MateriaService materiaService;
    
    @Autowired
    MateriaDTO nuevaMateriaDTO;

    @GetMapping("/formularioMateria")
    public ModelAndView getFormMateria() {
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", nuevaMateriaDTO);
        return modelView;
    }
    
    @GetMapping("/ListaMaterias")
    public ModelAndView getListaMateria() {
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMateria", materiaService.mostrarMaterias());
        return modelView;
    }

    @PostMapping("/guardarMateria")
    public ModelAndView saveMateria(@ModelAttribute("nuevaMateria") MateriaDTO materiaParaGuardar) {
        materiaService.guardarMateria(materiaParaGuardar);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMateria", materiaService.mostrarMaterias());
        return modelView;
    }

    @GetMapping("/quitarMateria/{codigo}")
    public ModelAndView deleteMateriaListado(@PathVariable(name="codigo") String codigo) {
        materiaService.borrarMateria(codigo);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMateria", materiaService.mostrarMaterias());
        return modelView;
    }

    @GetMapping("/modificarMateria/{codigo}")
    public ModelAndView getModificarMateria(@PathVariable(name="codigo") String codigo) {
        Materia materiaParaModificar = materiaService.buscarMateria(codigo);
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", materiaParaModificar);
        return modelView;
    }

    @PostMapping("/modificarMateria")
    public ModelAndView updateMateria(@ModelAttribute("nuevaMateria") MateriaDTO materiaModificada) {
        materiaService.modificarMateria(materiaModificada);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMateria", materiaService.mostrarMaterias());
        return modelView;
    }
}


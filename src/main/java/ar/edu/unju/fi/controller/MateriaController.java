package ar.edu.unju.fi.controller;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.MateriaService;
import ch.qos.logback.classic.Logger;
import ar.edu.unju.fi.model.Materia;

@Controller
public class MateriaController {
    
    @Autowired
    MateriaService materiaService;
    
    @Autowired
    DocenteService docenteService;
    
    @Autowired
    MateriaDTO nuevaMateriaDTO;
    
    @Autowired
    Materia nuevaMateria;

    //private static final Logger logger = LoggerFactory.getLogger(MateriaController.class);
    private static final Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(MateriaController.class);
    
    @GetMapping("/formularioMateria")
    public ModelAndView getFormMateria() {
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
        modelView.addObject("nuevaMateria", nuevaMateria);
        return modelView;
    }
    
    @GetMapping("/ListaMaterias")
    public ModelAndView getListaMateria() {
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMateria", materiaService.mostrarMaterias());
        return modelView;
    }
	


    @PostMapping("/guardarMateria")
    public ModelAndView saveMateria(@ModelAttribute("nuevaMateria") Materia materiaParaGuardar) {
        ModelAndView modelView = new ModelAndView();

        logger.info("Inicio del método saveMateria");

        try {
            logger.debug("Intentando guardar la materia: {}", materiaParaGuardar);
            materiaService.guardarMateria(materiaParaGuardar);
            modelView.setViewName("listaDeMaterias");
            modelView.addObject("listadoMateria", materiaService.mostrarMaterias());
            logger.info("Materia guardada exitosamente");
        } catch (Exception e) {
            logger.error("Error al guardar la materia", e);
            modelView.setViewName("formMateria");
            boolean errors = true;
            modelView.addObject("errors", errors);
            modelView.addObject("cargarMateriaErrorMensaje", e.getMessage());
        }
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


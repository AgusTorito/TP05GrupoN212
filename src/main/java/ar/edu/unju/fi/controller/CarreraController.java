package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.CarreraService;
import jakarta.validation.Valid;

@Controller
public class CarreraController {

    @Autowired
    CarreraService carreraService;

    @GetMapping("/formularioCarrera")
    public ModelAndView getFormCarrera() {
        ModelAndView modelView = new ModelAndView("formCarrera");
        modelView.addObject("nuevaCarrera", new Carrera());
        return modelView;
    }

    @GetMapping("/ListaCarreras")
    public ModelAndView getListaCarrera() {
        ModelAndView modelView = new ModelAndView("listaDeCarrera");
        modelView.addObject("listadoCarrera", carreraService.mostrarCarreras());
        return modelView;
    }

    @PostMapping("/guardarCarrera")
    public ModelAndView saveCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraParaGuardar, BindingResult resultado) {
        ModelAndView modelView = new ModelAndView();
        if (resultado.hasErrors()) {
            modelView.setViewName("formCarrera");
            modelView.addObject("nuevaCarrera", carreraParaGuardar);
        } else {
            carreraService.guardarCarrera(carreraParaGuardar);
            modelView.setViewName("listaDeCarrera");
            modelView.addObject("listadoCarrera", carreraService.mostrarCarreras());
        }
        return modelView;
    }

    @GetMapping("/quitarCarrera/{codigo}")
    public ModelAndView deleteCarreraListado(@PathVariable(name = "codigo") Integer codigo) {
        carreraService.borrarCarrera(codigo);
        ModelAndView modelView = new ModelAndView("listaDeCarrera");
        modelView.addObject("listadoCarrera", carreraService.mostrarCarreras());
        return modelView;
    }

    @GetMapping("/modificarCarrera/{codigo}")
    public ModelAndView getModificarCarrera(@PathVariable(name = "codigo") Integer codigo) {
        Carrera carreraParaModificar = carreraService.buscarCarrera(codigo);
        ModelAndView modelView = new ModelAndView("formCarrera");
        modelView.addObject("nuevaCarrera", carreraParaModificar);
        return modelView;
    }

    @PostMapping("/modificarCarrera")
    public ModelAndView updateCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraModificada, BindingResult resultado) {
        ModelAndView modelView = new ModelAndView();
        if (resultado.hasErrors()) {
            modelView.setViewName("formCarrera");
            modelView.addObject("nuevaCarrera", carreraModificada);
        } else {
            carreraService.modificarCarrera(carreraModificada);
            modelView.setViewName("listaDeCarrera");
            modelView.addObject("listadoCarrera", carreraService.mostrarCarreras());
        }
        return modelView;
    }
}



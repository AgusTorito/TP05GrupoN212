package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.service.CarreraService;

@Controller
public class CarreraController {
	
	@Autowired
	 CarreraDTO nuevaCarreraDTO;
	
	@Autowired
	CarreraService carreraService;
	
	@GetMapping("/formularioCarrera")
	public ModelAndView getFormCarrera()
	{
		 ModelAndView modelView = new ModelAndView("formCarrera");
		 modelView.addObject("nuevaCarrera", nuevaCarreraDTO);
		 return modelView;
	}	
	
	@GetMapping("/ListaCarreras")
    public ModelAndView getlistaCarrera() {
        ModelAndView modelView = new ModelAndView("listaDeCarrera");
        modelView.addObject("listadoCarrera", carreraService.mostrarCarreras());
        return modelView;
    }

	@PostMapping("/guardarCarrera")
	public ModelAndView saveCarrera(@ModelAttribute("nuevaCarrera") CarreraDTO carreraParaGuardar) {
		carreraService.guardarCarrera(carreraParaGuardar);
		ModelAndView modelView = new ModelAndView("listaDeCarrera");
		modelView.addObject("listadoCarrera", carreraService.mostrarCarreras());	 
	return modelView;
}


	@GetMapping("/quitarCarrera/{codigo}")
	public ModelAndView deleteCarreraListado(@PathVariable(name="codigo") String codigo) {	
		carreraService.borrarCarrera(codigo);
    	ModelAndView modelView = new ModelAndView("listaDeCarrera");
    	modelView.addObject("listadoCarrera", carreraService.mostrarCarreras());
    	return modelView;
	}

	@GetMapping("/modificarCarrera/{codigo}")
	public ModelAndView getModificarCarrera(@PathVariable(name="codigo") String codigo) {
		CarreraDTO carreraDTOParaModificar = carreraService.buscarCarrera(codigo);
		ModelAndView modelView = new ModelAndView("formCarrera");
		modelView.addObject("nuevaCarrera", carreraDTOParaModificar);
		return modelView;
	}

	@PostMapping("/modificarCarrera")
	public ModelAndView updateCarrera(@ModelAttribute("nuevaCarrera") CarreraDTO carreraDTOModificada) {
		carreraService.modificarCarrera(carreraDTOModificada);
		ModelAndView modelView = new ModelAndView("listaDeCarrera");
		modelView.addObject("listadoCarrera", carreraService.mostrarCarreras());
		return modelView;
	}
	
}

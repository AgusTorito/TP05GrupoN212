package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.service.DocenteService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DocenteController {
	
	@Autowired
	DocenteDTO nuevoDocenteDTO;
	
	@Autowired
	DocenteService docenteService;
	
	@GetMapping("/formularioDocente")
	public ModelAndView getFormDocente() {
		ModelAndView MV = new ModelAndView("formDocente");
		MV.addObject("nuevoDocente", nuevoDocenteDTO);
		return MV;
	}

	@PostMapping("/guardarDocente")
	public ModelAndView saveDocente(@ModelAttribute("nuevoDocente") DocenteDTO docenteParaGuardar) {
		docenteService.guardarDocente(docenteParaGuardar);
		ModelAndView MV = new ModelAndView("listaDocente");
		MV.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
		return MV;
	}
	
	@GetMapping("/borrarDocente/{legajo}")
	public ModelAndView deleteDocente(@PathVariable(name="legajo")String legajo) {
		docenteService.borrarDocente(legajo);
		ModelAndView MV=new ModelAndView("listaDocente");
		MV.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
		return MV;
	}
	
	@GetMapping("/modificarDocente/{legajo}")
	public ModelAndView editDocente(@PathVariable(name="legajo") String legajo) {
		DocenteDTO docenteDTOParaModificar = docenteService.buscarDocente(legajo);
		ModelAndView MV = new ModelAndView("formDocente");
		MV.addObject("nuevoDocente", docenteDTOParaModificar);
		return MV;
	}
	
	@PostMapping("/modificarDocente")
	public ModelAndView updateDocente(@ModelAttribute("nuevoDocente") DocenteDTO docenteDTOModificado) {
		docenteService.modificarDocente(docenteDTOModificado);
		ModelAndView MV = new ModelAndView("listaDocente");
		MV.addObject("listadoDeDocentes",docenteService.mostrarDocentes() );	
		return MV;		
	}
	
	
}

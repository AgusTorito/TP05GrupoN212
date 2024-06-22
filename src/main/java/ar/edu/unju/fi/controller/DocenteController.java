package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.DocenteService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class DocenteController {
	
	@Autowired
	DocenteDTO nuevoDocenteDTO;
	
	@Autowired
	Docente nuevoDocente;
	
	@Autowired
	DocenteService docenteService;
	
	@GetMapping("/formularioDocente")
	public ModelAndView getFormDocente() {
		//vista del formulario
		ModelAndView MV = new ModelAndView("formDocente");
		
		//agrega el objeto
		MV.addObject("nuevoDocente", nuevoDocenteDTO);
		MV.addObject("band", false);
		return MV;
	}
	
	@GetMapping("/listadoTDocentes")
	public ModelAndView getlist() {
		// mostrar el listado
		ModelAndView MV = new ModelAndView("listaDocente");
		
		
		MV.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
		return MV;
	}
	
	@PostMapping("/guardarDocente")
	public ModelAndView saveDocente(@ModelAttribute("nuevoDocente") DocenteDTO docenteParaGuardar) {
		//guardar
		docenteService.guardarDocente(docenteParaGuardar);
		//mostrar el listado
		ModelAndView MV = new ModelAndView("listaDocente");
		MV.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
		
		return MV;
	}
	
	@GetMapping("/borrarDocente/{legajo}")
	public ModelAndView deleteDocente(@PathVariable(name="legajo")String legajo) {
		//borrar
		docenteService.borrarDocente(legajo);
		
		//mostrar el nuevo listado
		ModelAndView MV=new ModelAndView("listaDocente");
		MV.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
		
		return MV;
	}
	
	@GetMapping("/modificarDocente/{legajo}")
	public ModelAndView editDocente(@PathVariable(name="legajo") String legajo) {
		//buscar
		
		DocenteDTO docenteDTOParaModificar = docenteService.buscarDocente(legajo);
		
		//mostrar el nuevo formulario
		ModelAndView MV = new ModelAndView("formDocente");
		MV.addObject("nuevoDocente", docenteDTOParaModificar);
		MV.addObject("band", true);
		return MV;
	}
	
	@PostMapping("/modificarDocente")
	public ModelAndView updateDocente(@ModelAttribute("nuevoDocente") DocenteDTO docenteDTOModificado) {
					
		//guardar
		docenteService.modificarDocente(docenteDTOModificado);
		
		//mostrar el listado
		ModelAndView MV = new ModelAndView("listaDocente");
		MV.addObject("listadoDeDocentes",docenteService.mostrarDocentes() );	
		
		return MV;		
	}
	
	
}

package ar.edu.unju.fi.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.DocenteService;
import ch.qos.logback.classic.Logger;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DocenteController {
	
	@Autowired
	DocenteDTO nuevoDocenteDTO;
	
	@Autowired
	DocenteService docenteService;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(DocenteController.class);
	
	@GetMapping("/formularioDocente")
	public ModelAndView getFormDocente() {
		ModelAndView MV = new ModelAndView("formDocente");
		MV.addObject("nuevoDocente", nuevoDocenteDTO);
		return MV;
	}
	
	@GetMapping("/ListadoDocente")
	   public ModelAndView getlistaDocente() {
	       ModelAndView modelView = new ModelAndView("listaDocente");
	       modelView.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
	       return modelView;
	}

	
	@PostMapping("/guardarDocente")
    public ModelAndView saveDocente(@Validated @ModelAttribute("nuevoDocente") Docente docenteParaGuardar, BindingResult resultado) {
        ModelAndView MV = new ModelAndView();

        logger.info("Inicio del método saveDocente");

        if (resultado.hasErrors()) {
            logger.warn("Errores de validación encontrados: {}", resultado.getAllErrors());
            MV.setViewName("formDocente");
            MV.addObject("errors", resultado.getAllErrors());
        } else {
            try {
                docenteService.guardarDocente(docenteParaGuardar);
                MV.setViewName("listaDocente");
                MV.addObject("listadoDeDocentes", docenteService.mostrarDocentes());
                logger.info("Docente guardado exitosamente");
            } catch (Exception e) {
                logger.error("Error al guardar el docente", e);
                MV.setViewName("formDocente");
                MV.addObject("errors", "Error al intentar guardar el docente. Por favor, inténtelo nuevamente.");
            }
        }

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

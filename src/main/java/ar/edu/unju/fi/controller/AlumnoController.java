package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.service.AlumnoService;

@Controller
public class AlumnoController {
	@Autowired
	AlumnoDTO nuevoAlumnoDTO;
	
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno()
	{
		 ModelAndView modelView = new ModelAndView("formAlumno");
		 modelView.addObject("nuevoAlumno", nuevoAlumnoDTO);
		 return modelView;
	}	
	
	@GetMapping("/ListadoAlumno")
   public ModelAndView getlistaAlumno() {
       ModelAndView modelView = new ModelAndView("listaAlumno");
       return modelView;
   }

	@PostMapping("/guardarAlumno")
	public ModelAndView saveAlumno(@ModelAttribute("nuevoAlumno") AlumnoDTO alumnoParaGuardar) {
		
	alumnoService.guardarAlumno(alumnoParaGuardar);
	
	ModelAndView modelView = new ModelAndView("listaAlumno");
	modelView.addObject("listadoAlumno", alumnoService.mostrarAlumnos());
		 
	 return modelView;
}


	@GetMapping("/quitarAlumno/{dni}")
	public ModelAndView deleteAlumnoListado(@PathVariable(name="dni") String dni) {
		
		alumnoService.borrarAlumno(dni);

   	ModelAndView modelView = new ModelAndView("listaAlumno");
   	modelView.addObject("listadoAlumno", alumnoService.mostrarAlumnos());
   	
   	return modelView;
	}

	@GetMapping("/modificarAlumno/{dni}")
	public ModelAndView getModificarAlumno(@PathVariable(name="dni") String dni) {
	
		AlumnoDTO alumnoDTOParaModificar = alumnoService.buscarAlumno(dni);

		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("nuevoAlumno", alumnoDTOParaModificar);

		return modelView;
	}

	@PostMapping("/modificarAlumno")
	public ModelAndView updateAlumno(@ModelAttribute("nuevoAlumno") AlumnoDTO alumnoDTOModificada) {
		alumnoService.modificarAlumno(alumnoDTOModificada);

		ModelAndView modelView = new ModelAndView("listaAlumno");
		modelView.addObject("listadoAlumno", alumnoService.mostrarAlumnos());

		return modelView;
	}
}

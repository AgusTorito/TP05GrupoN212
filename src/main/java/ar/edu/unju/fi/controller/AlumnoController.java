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
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;

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
		 modelView.addObject("nuevaAlumno", nuevoAlumnoDTO);
		 return modelView;
	}	
	
	@GetMapping("/ListaAlumnos")
    public ModelAndView getlistaAlumno() {
       ModelAndView modelView = new ModelAndView("listaDeAlumno");
       return modelView;
    }

	@PostMapping("/guardarAlumno")
	public ModelAndView saveAlumno(@Valid @ModelAttribute("nuevaAlumno") AlumnoDTO alumnoParaGuardar) {
	try {
	alumnoService.guardarAlumno(alumnoParaGuardar);
	}catch(Exception e){
		
	}
	
	ModelAndView modelView = new ModelAndView("listaDeAlumno");
	modelView.addObject("listadoAlumno", alumnoService.mostrarAlumnos());
		 
	 return modelView;
}


	@GetMapping("/quitarAlumno/{dni}")
	public ModelAndView deleteAlumnoListado(@PathVariable(name="dni") String dni) {
		
		alumnoService.borrarAlumno(dni);

   	ModelAndView modelView = new ModelAndView("listaDeAlumno");
   	modelView.addObject("listadoAlumno", alumnoService.mostrarAlumnos());
   	
   	return modelView;
	}

	@GetMapping("/modificarAlumno/{dni}")
	public ModelAndView getModificarAlumno(@PathVariable(name="dni") String dni) {
	
		AlumnoDTO alumnoDTOParaModificar = alumnoService.buscarAlumno(dni);

		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("nuevaAlumno", alumnoDTOParaModificar);

		return modelView;
	}



	@PostMapping("/modificarAlumno")
	public ModelAndView updateAlumno(@ModelAttribute("nuevaAlumno") AlumnoDTO alumnoDTOModificada) {
		alumnoService.modificarAlumno(alumnoDTOModificada);

		ModelAndView modelView = new ModelAndView("listaDeAlumno");
		modelView.addObject("listadoAlumno", alumnoService.mostrarAlumnos());

		return modelView;
	}
}

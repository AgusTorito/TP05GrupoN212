package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.AlumnoService;
import org.springframework.ui.Model;

//import java.util.Optional;

@Controller
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;
    
    @Autowired
    private MateriaRepository materiaRepository;
    
    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/formularioAlumno")
    public ModelAndView getFormAlumno() {
        ModelAndView modelView = new ModelAndView("formAlumno");
        modelView.addObject("nuevoAlumno", new Alumno()); // Se inicializa un nuevo objeto Alumno
        return modelView;
    }

    @GetMapping("/ListadoAlumno")
    public ModelAndView getListaAlumno() {
        ModelAndView modelView = new ModelAndView("listaAlumno");
        modelView.addObject("listadoAlumno", alumnoService.mostrarAlumnos());
        return modelView;
    }

    @PostMapping("/guardarAlumno")
    public ModelAndView saveAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoParaGuardar) {
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
        Alumno alumnoParaModificar = alumnoService.buscarAlumno(dni);
        ModelAndView modelView = new ModelAndView("formAlumno");
        modelView.addObject("nuevoAlumno", alumnoParaModificar);
        return modelView;
    }

    @PostMapping("/modificarAlumno")
    public ModelAndView updateAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoModificado) {
        alumnoService.modificarAlumno(alumnoModificado);
        ModelAndView modelView = new ModelAndView("listaAlumno");
        modelView.addObject("listadoAlumno", alumnoService.mostrarAlumnos());
        return modelView;
    }

    @PostMapping("/inscribirAlumnoMateria")
    public String inscribirAlumnoEnMateria(@RequestParam("alumno") String dniAlumno, @RequestParam("materia") String codigoMateria) {
        Alumno alumno = alumnoRepository.findById(dniAlumno).orElse(null);
        Materia materia = materiaRepository.findById(codigoMateria).orElse(null);
        if (alumno != null && materia != null) {
            materia.getAlumnos().add(alumno); // Añadir alumno a la lista de alumnos de la materia
            materiaRepository.save(materia); // Guardar la materia actualizada con el alumno inscrito
        }
        return "redirect:/inscripcionAlumnoMateria"; // Redirigir a la página de inscripción de alumnos en materia
    }



    @GetMapping("/alumnosPorCarrera")
    public String mostrarAlumnosPorCarrera(Model model) {
        model.addAttribute("carreras", carreraRepository.findAll());
        return "alumnosPorCarrera";
    }

    @GetMapping("/buscarAlumnosPorCarrera")
    public String buscarAlumnosPorCarrera(@RequestParam("carrera") Integer codigoCarrera, Model model) {
        Carrera carrera = carreraRepository.findById(codigoCarrera).orElse(null);
        if (carrera != null) {
            model.addAttribute("listadoAlumnos", carrera.getAlumnos());
        }
        model.addAttribute("carreras", carreraRepository.findAll());
        return "alumnosPorCarrera";
    }

    @GetMapping("/alumnosPorMateria")
    public String mostrarAlumnosPorMateria(Model model) {
        model.addAttribute("materias", materiaRepository.findAll());
        return "alumnosPorMateria";
    }

    @GetMapping("/buscarAlumnosPorMateria")
    public String buscarAlumnosPorMateria(@RequestParam("materia") String codigoMateria, Model model) {
        Materia materia = materiaRepository.findById(codigoMateria).orElse(null);
        if (materia != null) {
            model.addAttribute("listadoAlumnos", materia.getAlumnos());
        }
        model.addAttribute("materias", materiaRepository.findAll());
        return "alumnosPorMateria";
    }
}


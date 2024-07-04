package ar.edu.unju.fi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.MateriaService;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.DocenteService;

@Controller
public class MateriaController {
    
    @Autowired
    MateriaService materiaService;
    
    @Autowired
    AlumnoService alumnoService;
    
    @Autowired
    CarreraService carreraService;
    
    @Autowired
    DocenteService docenteService;
    
    @Autowired
    Materia nuevaMateria;

    @GetMapping("/formularioMateria")
    public ModelAndView getFormMateria() {
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", nuevaMateria);
        modelView.addObject("carreras", carreraService.mostrarCarreras());
        modelView.addObject("docentes", docenteService.mostrarDocentes());
        return modelView;
    }
    
    @GetMapping("/listaMaterias")
    public ModelAndView getListaMateria() {
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMateria", materiaService.mostrarMaterias());
        return modelView;
    }

    @PostMapping("/guardarMateria")
    public ModelAndView saveMateria(@ModelAttribute("nuevaMateria") Materia materiaParaGuardar) {
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
        modelView.addObject("carreras", carreraService.mostrarCarreras());
        modelView.addObject("docentes", docenteService.mostrarDocentes());
        return modelView;
    }

    @PostMapping("/modificarMateria")
    public ModelAndView updateMateria(@ModelAttribute("nuevaMateria") Materia materiaModificada) {
        materiaService.modificarMateria(materiaModificada);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMateria", materiaService.mostrarMaterias());
        return modelView;
    }

    @GetMapping("/inscripcionAlumnoMateria")
    public ModelAndView inscripcionAlumnoMateria() {
        ModelAndView modelView = new ModelAndView("inscripcionAlumnoMateria");
        modelView.addObject("alumnos", alumnoService.mostrarAlumnos());
        modelView.addObject("materias", materiaService.mostrarMaterias());
        return modelView;
    }

    @PostMapping("/inscribirAlumno")
    public ModelAndView inscribirAlumno(@RequestParam("dniAlumno") String dniAlumno, @RequestParam("codigoMateria") String codigoMateria) {
        materiaService.inscribirAlumnoEnMateria(dniAlumno, codigoMateria);
        ModelAndView modelView = new ModelAndView("alumnosPorMateria");
        modelView.addObject("listadoAlumnos", materiaService.filtrarAlumnosPorMateria(codigoMateria));
        return modelView;
    }



    @GetMapping("/alumnosPorCarrera/{codigoCarrera}")
    public ModelAndView alumnosPorCarrera(@PathVariable("codigoCarrera") Integer codigoCarrera) {
        Carrera carrera = carreraService.buscarCarrera(codigoCarrera);
        ModelAndView modelView = new ModelAndView("alumnosPorCarrera");
        if (carrera != null) {
            modelView.addObject("listadoAlumnos", carrera.getAlumnos());
        }
        return modelView;
    }

    @GetMapping("/filtrarAlumnosPorMateria/{codigo}")
    public ModelAndView filtrarAlumnosPorMateria(@PathVariable("codigo") String codigo) {
        List<Alumno> alumnos = materiaService.filtrarAlumnosPorMateria(codigo);
        ModelAndView modelView = new ModelAndView("alumnosPorMateria");
        modelView.addObject("listadoAlumnos", alumnos);
        return modelView;
    }



}


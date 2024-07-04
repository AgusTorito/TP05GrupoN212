package ar.edu.unju.fi.service;

import ar.edu.unju.fi.model.Alumno;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AlumnoService {
    public void guardarAlumno(Alumno alumno);
    public List<Alumno> mostrarAlumnos();
    public void borrarAlumno(String dni);
    public void modificarAlumno(Alumno alumno);
    public Alumno buscarAlumno(String dni);
}

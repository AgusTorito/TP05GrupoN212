package ar.edu.unju.fi.service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AlumnoService {
	public void guardarAlumno(AlumnoDTO alumnoDTO);
	public List<Alumno> mostrarAlumnos();
	public void borrarAlumno(String dni);
	public void modificarAlumno(AlumnoDTO alumnoDTO);
	public AlumnoDTO buscarAlumno(String dni);
}

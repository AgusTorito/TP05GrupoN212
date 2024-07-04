package ar.edu.unju.fi.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Override
    public void guardarAlumno(Alumno alumno) {
        alumno.setEstado(true);
        alumnoRepository.save(alumno);
    }

    @Override
    public List<Alumno> mostrarAlumnos() {
        return alumnoRepository.findAlumnoByEstado(true);
    }

    @Override
    public void borrarAlumno(String dni) {
        Alumno alumno = alumnoRepository.findById(dni).orElse(null);
        if (alumno != null) {
            alumno.setEstado(false);
            alumnoRepository.save(alumno);
        }
    }

    @Override
    public void modificarAlumno(Alumno alumno) {
        Alumno alumnoExistente = alumnoRepository.findById(alumno.getDni()).orElse(null);
        if (alumnoExistente != null) {
            alumnoExistente.setNombre(alumno.getNombre());
            alumnoExistente.setApellido(alumno.getApellido());
            alumnoExistente.setEmail(alumno.getEmail());
            alumnoExistente.setTelefono(alumno.getTelefono());
            alumnoExistente.setFechaNacimiento(alumno.getFechaNacimiento());
            alumnoExistente.setDomicilio(alumno.getDomicilio());
            alumnoExistente.setLU(alumno.getLU());
            alumnoRepository.save(alumnoExistente);
        }
    }

    @Override
    public Alumno buscarAlumno(String dni) {
        return alumnoRepository.findById(dni).orElse(null);
    }
}

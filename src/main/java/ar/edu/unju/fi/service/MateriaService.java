package ar.edu.unju.fi.service;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.model.Alumno;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MateriaService {

    public void guardarMateria(Materia materia);
    public List<Materia> mostrarMaterias();
    public void borrarMateria(String codigo);
    public void modificarMateria(Materia materia);
    public Materia buscarMateria(String codigo);
    public List<Alumno> filtrarAlumnosPorMateria(String codigo);
    public void inscribirAlumnoEnMateria(String dniAlumno, String codigoMateria);
}

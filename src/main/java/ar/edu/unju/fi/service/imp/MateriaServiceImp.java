package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.MateriaService;

@Service
public class MateriaServiceImp implements MateriaService {

    @Autowired
    MateriaRepository materiaRepository;

    @Autowired
    AlumnoRepository alumnoRepository;

    @Override
    public void guardarMateria(Materia materia) {
        materia.setEstado(true);
        materiaRepository.save(materia);
    }

    @Override
    public List<Materia> mostrarMaterias() {
        return materiaRepository.findMateriaByEstado(true);
    }

    @Override
    public void borrarMateria(String codigo) {
        Optional<Materia> optionalMateria = materiaRepository.findById(codigo);
        if (optionalMateria.isPresent()) {
            Materia materia = optionalMateria.get();
            materia.setEstado(false);
            materiaRepository.save(materia);
        }
    }

    @Override
    public void modificarMateria(Materia materia) {
        Optional<Materia> optionalMateria = materiaRepository.findById(materia.getCodigo());
        if (optionalMateria.isPresent()) {
            Materia m = optionalMateria.get();
            m.setNombre(materia.getNombre());
            m.setCurso(materia.getCurso());
            m.setCantidadHoras(materia.getCantidadHoras());
            m.setModalidad(materia.getModalidad());
            materiaRepository.save(m);
        }
    }

    @Override
    public Materia buscarMateria(String codigo) {
        return materiaRepository.findById(codigo).orElse(null);
    }

    @Override
    public List<Alumno> filtrarAlumnosPorMateria(String codigo) {
        Materia materia = materiaRepository.findById(codigo).orElse(null);
        return materia != null ? materia.getAlumnos() : null;
    }

    @Override
    public void inscribirAlumnoEnMateria(String dniAlumno, String codigoMateria) {
        Alumno alumno = alumnoRepository.findById(dniAlumno).orElse(null);
        Materia materia = materiaRepository.findById(codigoMateria).orElse(null);
        if (alumno != null && materia != null) {
            materia.getAlumnos().add(alumno);
            materiaRepository.save(materia);
        }
    }
}


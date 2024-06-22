package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;

@Service
public class MateriaServiceImp implements MateriaService {

    @Autowired
    MateriaRepository materiaRepository;

    @Override
    public void guardarMateria(MateriaDTO materiaDTO) {
        Materia materia = convertirAEntidad(materiaDTO);
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
    public void modificarMateria(MateriaDTO materiaDTO) {
        Optional<Materia> optionalMateria = materiaRepository.findById(materiaDTO.getCodigo());
        if (optionalMateria.isPresent()) {
            Materia materia = optionalMateria.get();
            materia.setNombre(materiaDTO.getNombre());
            materia.setCurso(materiaDTO.getCurso());
            materia.setCantidadHoras(materiaDTO.getCantidadHoras());
            materia.setModalidad(materiaDTO.getModalidad());
            materiaRepository.save(materia);
        }
    }

    @Override
    public Materia buscarMateria(String codigo) {
        return materiaRepository.findById(codigo).orElse(null);
    }

    private Materia convertirAEntidad(MateriaDTO materiaDTO) {
        Materia materia = new Materia();
        materia.setCodigo(materiaDTO.getCodigo());
        materia.setNombre(materiaDTO.getNombre());
        materia.setCurso(materiaDTO.getCurso());
        materia.setCantidadHoras(materiaDTO.getCantidadHoras());
        materia.setModalidad(materiaDTO.getModalidad());
        return materia;
    }
}


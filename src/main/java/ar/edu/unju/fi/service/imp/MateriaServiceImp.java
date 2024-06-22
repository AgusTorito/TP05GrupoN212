package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;
import ar.edu.unju.fi.map.MateriaMapDTO;

@Service
public class MateriaServiceImp implements MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private MateriaMapDTO materiaMapDTO;

    @Override
    public void guardarMateria(MateriaDTO materiaDTO) {
        Materia materia = materiaMapDTO.convertirMateriaDTOAMateria(materiaDTO);
        materia.setEstado(true);
        materiaRepository.save(materia);
    }

    @Override
    public List<MateriaDTO> mostrarMaterias() {
        return materiaMapDTO.convertirListaMateriaAListaMateriaDTO(
                materiaRepository.findMateriaByEstado(true)
        );
    }

    @Override
    public void borrarMateria(String codigo) {
        Materia materia = materiaRepository.findById(codigo).orElse(null);
        if (materia != null) {
            materia.setEstado(false);
            materiaRepository.save(materia);
        }
    }

    @Override
    public void modificarMateria(MateriaDTO materiaDTO) {
        Materia materia = materiaRepository.findById(materiaDTO.getCodigo()).orElse(null);
        if (materia != null) {
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
}



package ar.edu.unju.fi.service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;
import java.util.List;

public interface MateriaService {
    void guardarMateria(MateriaDTO materiaDTO);
    List<MateriaDTO> mostrarMaterias();
    void borrarMateria(String codigo);
    void modificarMateria(MateriaDTO materiaDTO);
    Materia buscarMateria(String codigo);
}


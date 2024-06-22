package ar.edu.unju.fi.service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MateriaService {

	public void guardarMateria(MateriaDTO materiaDTO);
	public List<Materia> mostrarMaterias();
	public void borrarMateria(String codigo);
	public void modificarMateria(MateriaDTO materiaDTO);
	public Materia buscarMateria(String codigo);
}

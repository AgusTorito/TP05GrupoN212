package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.DocenteDTO;

@Service
public interface DocenteService {
	public void guardarDocente(DocenteDTO docenteDTO);
	public List<DocenteDTO> mostrarDocentes();
	public void borrarDocente(String legajo);
	public void modificarDocente(DocenteDTO docenteDTO);
	public DocenteDTO buscarDocente(String legajo);
	
}

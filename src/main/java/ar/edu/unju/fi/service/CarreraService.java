package ar.edu.unju.fi.service;

import ar.edu.unju.fi.model.Carrera;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CarreraService {
	public void guardarCarrera(Carrera carrera);
	public List<Carrera> mostrarCarreras();
	public void borrarCarrera(String codigo);
	public void modificarCarrera(Carrera carrera);
	public Carrera buscarCarrera(String codigo);
}

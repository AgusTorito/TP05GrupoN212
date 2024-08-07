package ar.edu.unju.fi.service;

//import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CarreraService {
	public void guardarCarrera(Carrera carrera);
	public List<Carrera> mostrarCarreras();
	public void borrarCarrera(Integer codigo);
	public void modificarCarrera(Carrera carrera);
	public Carrera buscarCarrera(Integer codigo);
}

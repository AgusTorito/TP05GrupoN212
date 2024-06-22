package ar.edu.unju.fi.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

@Service
public class CarreraServiceImp implements CarreraService {

	@Autowired
	CarreraMapDTO carreraMapDTO;
	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Override	
	public void guardarCarrera(CarreraDTO carreraDTO)
	{
		carreraDTO.setEstado(true);
		carreraRepository.save(carreraMapDTO.convertirCarreraDTOAcarrera(carreraDTO));
	}
	
	@Override
	public List<Carrera> mostrarCarreras()
	{
		return carreraRepository.findCarreraByEstado(true);
	}
	
	public void borrarCarrera(String codigo)
	{
		List<Carrera> todasLasCarreras = carreraRepository.findAll();
		
		for(int i = 0; i < todasLasCarreras.size(); i++)
		{
			Carrera carrera = todasLasCarreras.get(i);
			if(carrera.getCodigo().equals(codigo))
			{
				carrera.setEstado(false);
				carreraRepository.save(carrera);
				break;
			}
		}
	}

	@Override
	public void modificarCarrera(CarreraDTO carreraDTO) {
		
		Carrera carreraModificada = carreraMapDTO.convertirCarreraDTOAcarrera(carreraDTO);
		
		List<Carrera> lasCarreras = carreraRepository.findAll();
		
		for(int i=0; i<lasCarreras.size(); i++)
		{	
			Carrera carreras = lasCarreras.get(i);
			if(carreras.getCodigo().equals(carreraModificada.getCodigo())) {
				lasCarreras.set(i, carreraModificada);
				break;
			}
		}
		
	}

	@Override
	public CarreraDTO buscarCarrera(String codigo) {

		List<Carrera> Carreras = carreraRepository.findAll();
		
		for (CarreraDTO c : carreraMapDTO.convertirListaCarreraAListaCarreraDTO(Carreras))
		{
			if(c.getCodigo().equals(codigo))
			{
				return c;
			}
		}
		return null;
	}	
	
}

package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

@Service
public class CarreraServiceImp implements CarreraService {

	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Override	
	public void guardarCarrera(Carrera carrera)
	{
		carrera.setEstado(true);
		carreraRepository.save(carrera);
	}
	
	@Override
	public List<Carrera> mostrarCarreras()
	{
		//return carreraRepository.findAll();
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
	public void modificarCarrera(Carrera carrera) {
		List<Carrera> lasCarreras = carreraRepository.findAll();
	
		for(int i=0; i<lasCarreras.size(); i++)
		{
			Carrera carreras = lasCarreras.get(i);
			if(carreras.getCodigo().equals(carrera.getCodigo())) {
				lasCarreras.set(i, carrera);
				break;
			}
		}
		
	}

	@Override
	public Carrera buscarCarrera(String codigo) {

		List<Carrera> Carreras = carreraRepository.findAll();
		
		for (Carrera c : Carreras)
		{
			if(c.getCodigo().equals(codigo))
			{
				return c;
			}
		}
		return null;
	}	
	
}

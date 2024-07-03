package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService {

	
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Autowired
	AlumnoRepository alumnoRepository;

	@Override
	public void guardarAlumno(AlumnoDTO alumnoDTO) {
		Alumno nuevoAlumno;
		nuevoAlumno = alumnoMapDTO.convertirAlumnoDTOAAlumno(alumnoDTO);
		nuevoAlumno.setEstado(true);
		alumnoRepository.save(nuevoAlumno);
		
	}

	@Override
	public List<Alumno> mostrarAlumnos() {
		//return alumnoRepository.findAll();
		return alumnoRepository.findAlumnoByEstado(true);
	}

	@Override
	public void borrarAlumno(String dni) {
		List<Alumno> todasLasAlumnos = alumnoRepository.findAll();
		
		for(int i = 0; i < todasLasAlumnos.size(); i++)
		{
			Alumno alumno = todasLasAlumnos.get(i);
			if(alumno.getDni().equals(dni))
			{
				alumno.setEstado(false);
				alumnoRepository.save(alumno);
				break;
			}
		}
		
	}

	@Override
	public void modificarAlumno(AlumnoDTO alumnoDTO) {
		alumnoRepository.save(alumnoMapDTO.convertirAlumnoDTOAAlumno(alumnoDTO));
	}

	@Override
	public AlumnoDTO buscarAlumno(String dni) {
		List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
		for(Alumno a:todosLosAlumnos) {
			if(a.getDni().equals(dni)) {
				AlumnoDTO dDTO= alumnoMapDTO.convertirAlumnoAAlumnoDTO(a);
				return dDTO;
			}
		}
		return null;
	}

}

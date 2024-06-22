package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;


@Service
public class DocenteServiceImp implements DocenteService{
	
	@Autowired
	DocenteRepository docenteRepository;
	
	@Autowired
	DocenteMapDTO docenteMapDTO;
	
	@Override
	public void guardarDocente(DocenteDTO docenteDTO) {
		// TODO Auto-generated method stub
		Docente nuevoDocente;
		nuevoDocente = docenteMapDTO.convertirDocenteDTOADocente(docenteDTO);
		nuevoDocente.setEstado(true);
		docenteRepository.save(nuevoDocente);
	}

	@Override
	public List<DocenteDTO> mostrarDocentes() {
		List<DocenteDTO> docentesDTO = docenteMapDTO.convertirListaDocentesAListaDocentesDTO(docenteRepository.findDocenteByEstado(true));
		
		return docentesDTO;
	}

	@Override
	public void borrarDocente(String legajo) {
		List<Docente> todosLosDocentes = docenteRepository.findAll();
		for (int i = 0; i < todosLosDocentes.size(); i++) {
		      Docente docente = todosLosDocentes.get(i);
		      if (docente.getLegajo().equals(legajo)) {
		        docente.setEstado(false);
		        docenteRepository.save(docente);
		        break;
		      }
		    }
		
	}

	@Override
	public void modificarDocente(DocenteDTO docenteModificadoDTO) {
		//convertir de docenteDTO a docente
		Docente docenteModificado = docenteMapDTO.convertirDocenteDTOADocente(docenteModificadoDTO);
		//modifica el docente completo
		List<Docente> todosLosDocentes = docenteRepository.findAll();
	    for (int i = 0; i < todosLosDocentes.size(); i++) {
	    	Docente docente = todosLosDocentes.get(i);
	      if (docente.getLegajo().equals(docenteModificado.getLegajo()) ) {
	        todosLosDocentes.set(i, docenteModificado);
	        break;
	      }
	    }
		
		
	}

	@Override
	public DocenteDTO buscarDocente(String legajo) {
		//busca el docente
		List<Docente> todosLosDocentes = docenteRepository.findAll();
			for(Docente d:todosLosDocentes) {
				if(d.getLegajo().equals(legajo)) {
					//comvierte docente para devolverlo en docenteDTO
					DocenteDTO dDTO= docenteMapDTO.convertirDocenteADocenteDTO(d);
					return dDTO;
				}
		}
		return null;
	}


	
}
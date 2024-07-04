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
	public void guardarDocente(Docente docente) {
		docente.setEstado(true);
		docenteRepository.save(docente);
	}

	@Override
	public List<Docente> mostrarDocentes() {
		
		return docenteRepository.findDocenteByEstado(true);
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
		Docente docenteModificado = docenteMapDTO.convertirDocenteDTOADocente(docenteModificadoDTO);

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
		List<Docente> todosLosDocentes = docenteRepository.findAll();
			for(DocenteDTO d : docenteMapDTO.convertirListaDocentesAListaDocentesDTO(todosLosDocentes)) {
				if(d.getLegajo().equals(legajo)) {
					return d;
			}
		}
		return null;
	}


}

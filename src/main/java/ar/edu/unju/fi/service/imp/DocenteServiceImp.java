package ar.edu.unju.fi.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;

@Service
public class DocenteServiceImp implements DocenteService {

    @Autowired
    DocenteRepository docenteRepository;

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
        for (Docente docente : todosLosDocentes) {
            if (docente.getLegajo().equals(legajo)) {
                docente.setEstado(false);
                docenteRepository.save(docente);
                break;
            }
        }
    }

    @Override
    public void modificarDocente(Docente docenteModificado) {
        List<Docente> todosLosDocentes = docenteRepository.findAll();
        for (int i = 0; i < todosLosDocentes.size(); i++) {
            Docente docente = todosLosDocentes.get(i);
            if (docente.getLegajo().equals(docenteModificado.getLegajo())) {
                todosLosDocentes.set(i, docenteModificado);
                break;
            }
        }
        docenteRepository.saveAll(todosLosDocentes);
    }

    @Override
    public Docente buscarDocente(String legajo) {
        List<Docente> todosLosDocentes = docenteRepository.findAll();
        for (Docente docente : todosLosDocentes) {
            if (docente.getLegajo().equals(legajo)) {
                return docente;
            }
        }
        return null;
    }
}

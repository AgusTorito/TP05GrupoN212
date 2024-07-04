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
    private CarreraRepository carreraRepository;

    @Override
    public void guardarCarrera(Carrera carrera) {
        carrera.setEstado(true);
        carreraRepository.save(carrera);
    }

    @Override
    public List<Carrera> mostrarCarreras() {
        return carreraRepository.findCarreraByEstado(true);
    }

    @Override
    public void borrarCarrera(Integer codigo) {
        List<Carrera> todasLasCarreras = carreraRepository.findAll();
        for (Carrera carrera : todasLasCarreras) {
            if (carrera.getCodigo().equals(codigo)) {
                carrera.setEstado(false);
                carreraRepository.save(carrera);
                break;
            }
        }
    }

    @Override
    public void modificarCarrera(Carrera carrera) {
        List<Carrera> todasLasCarreras = carreraRepository.findAll();
        for (int i = 0; i < todasLasCarreras.size(); i++) {
            Carrera c = todasLasCarreras.get(i);
            if (c.getCodigo().equals(carrera.getCodigo())) {
                todasLasCarreras.set(i, carrera);
                break;
            }
        }
        carreraRepository.saveAll(todasLasCarreras);
    }

    @Override
    public Carrera buscarCarrera(Integer codigo) {
        List<Carrera> todasLasCarreras = carreraRepository.findAll();
        for (Carrera carrera : todasLasCarreras) {
            if (carrera.getCodigo().equals(codigo)) {
                return carrera;
            }
        }
        return null;
    }
}


package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

/*
 * Esta configuración permite inyectar automáticamente el mapeador en otros componentes de Spring (por ejemplo, servicios o controladores), aprovechando la gestión de dependencias de Spring.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarreraMapDTO {
	
	@Mapping(source="nombre", target="nombre")
	@Mapping(source="cantAnios", target="aniosCarrera")
	@Mapping(source="codigo", target="codigo")
	@Mapping(source="estado", target="estado")
	CarreraDTO convertirCarreraAcarreraDTO(Carrera c);

	
	@InheritInverseConfiguration
	Carrera convertirCarreraDTOAcarrera(CarreraDTO cdto);
	
	List<CarreraDTO> convertirListaCarreraAListaCarreraDTO (List<Carrera> listaC); 
	
	List<Carrera> convertirListaCarreraDTOAListaCarrera (List<CarreraDTO> listaCDTO); 
}

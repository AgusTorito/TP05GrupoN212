package ar.edu.unju.fi.map;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlumnoMapDTO {
	
	@Mapping(source = "dni", target = "dni")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "apellido", target = "apellido")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefono", target = "celular")
    @Mapping(source = "fechaNacimiento", target = "fechaN")
    @Mapping(source = "domicilio", target = "direccion")
	@Mapping(source = "LU", target = "legajo")
	@Mapping(source = "estado", target = "estado")
	AlumnoDTO convertirAlumnoAAlumnoDTO(Alumno a);
	

	@InheritInverseConfiguration
	Alumno convertirAlumnoDTOAAlumno(AlumnoDTO adto);
	
	List<AlumnoDTO> convertirListaAlumnosAListaAlumnosDTO (List<Alumno> listaA);
	
	List<Alumno> convertirListaAlumnosDTOAListaAlumnos (List<AlumnoDTO> listaADTO);
}
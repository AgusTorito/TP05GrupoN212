package ar.edu.unju.fi.DTO;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AlumnoDTO {
	
	private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private LocalDate fechaN;
    private String direccion;
    private String legajo;
    private Boolean estado;
	
}

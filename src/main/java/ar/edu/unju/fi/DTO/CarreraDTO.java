package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CarreraDTO {
	
	private String nombre;
	private int aniosCarrera;
	private String codigo;
	private Boolean estado;
}

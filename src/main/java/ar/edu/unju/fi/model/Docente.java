package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
public class Docente {
	
    @Id
    @Column(unique = true)
    @Size(min=3, max=20, message="El Legajo ingresado debe contener como mínimo 3 caracteres y "
            + "como máximo 20 caracteres")
    private String legajo;
    
    @Size(min=3, max=20, message="El Nombre ingresado debe contener como mínimo 3 caracteres y "
            + "como máximo 20 caracteres")
    @Pattern(regexp= "[a-zA-Z]*", message="Debe ingresar únicamente letras")
    private String nombre;
    
    @Size(min=3, max=20, message="El Apellido ingresado debe contener como mínimo 3 caracteres y "
            + "como máximo 20 caracteres")
    @Pattern(regexp= "[a-zA-Z]*", message="Debe ingresar únicamente letras")
    private String apellido;
    
    @Email
    private String email;
    
    @Size(min=7, max=20, message="El Telefono ingresado debe contener como mínimo 7 caracteres y "
            + "como máximo 20 caracteres")
    private String telefono;
    
    private Boolean estado;   
	
}

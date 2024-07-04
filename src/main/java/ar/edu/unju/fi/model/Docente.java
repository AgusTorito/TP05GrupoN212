package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
@Component
@Entity
public class Docente {

    @Id
    @NotBlank(message = "El legajo no puede ser nulo")
    private String legajo;

    @NotBlank(message = "El nombre no puede ser nulo")
    private String nombre;

    @NotBlank(message = "El apellido no puede ser nulo")
    private String apellido;

    @NotBlank(message = "El email no puede ser nulo")
    @Email(message = "Debe ser una dirección de correo válida")
    private String email;

    @NotBlank(message = "El teléfono no puede ser nulo")
    private String telefono;

    private Boolean estado;

    @OneToOne(mappedBy = "docente")
    private Materia materia;
}


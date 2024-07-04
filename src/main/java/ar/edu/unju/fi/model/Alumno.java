package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Component
@Entity
public class Alumno {

    @Id
    @NotBlank(message = "El DNI no puede ser nulo")
    private String dni;

    @NotBlank(message = "El nombre no puede ser nulo")
    private String nombre;

    @NotBlank(message = "El apellido no puede ser nulo")
    private String apellido;

    @NotBlank(message = "El email no puede ser nulo")
    @Email(message = "Debe ingresar un email válido")
    private String email;

    @NotBlank(message = "El teléfono no puede ser nulo")
    private String telefono;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El domicilio no puede ser nulo")
    private String domicilio;

    @NotBlank(message = "La LU no puede ser nula")
    private String LU;

    private Boolean estado;

    @ManyToMany
    @JoinTable(
        name = "alumno_materia",
        joinColumns = @JoinColumn(name = "alumno_dni"),
        inverseJoinColumns = @JoinColumn(name = "materia_codigo")
    )
    private List<Materia> materias;

    @ManyToOne
    @JoinColumn(name = "car_codigo")
    private Carrera carrera;
}


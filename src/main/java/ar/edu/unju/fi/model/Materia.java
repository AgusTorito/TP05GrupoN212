package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;

import java.util.List;

@Data
@Component
@Entity(name = "materias")
public class Materia {

    @Id
    @Column(name = "mat_codigo")
    @NotBlank(message = "El código no puede ser nulo")
    private String codigo;

    @Column(name = "mat_nombre", nullable = false)
    @NotBlank(message = "Ingrese el nombre de la Materia")
    @Size(min = 5, max = 20, message = "El nombre de la Materia debe tener entre 5 y 20 caracteres")
    private String nombre;

    @Column(name = "mat_curso")
    @NotBlank(message = "Ingrese el curso de la Materia")
    private String curso;

    @Column(name = "mat_cantidad_horas")
    @Min(value = 3, message = "Debe tener al menos 3 horas")
    @Max(value = 20, message = "No puede tener más de 20 horas")
    @NotNull(message = "La cantidad de horas no puede ser nula")
    private Integer cantidadHoras;

    @Column(name = "mat_modalidad")
    @NotBlank(message = "Ingrese la modalidad de la Materia")
    private String modalidad;

    @Column(name = "mat_estado")
    private Boolean estado;

    @ManyToMany(mappedBy = "materias")
    private List<Alumno> alumnos;

    @OneToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "car_codigo")
    private Carrera carrera;
}


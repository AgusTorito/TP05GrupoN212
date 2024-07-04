package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;

import java.util.List;

@Data
@Component
@Entity(name = "carreras")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_codigo", nullable = false)
    private Integer codigo;

    @Column(name = "car_nombre", nullable = false)
    @NotBlank(message = "Ingrese el nombre de la Carrera")
    @Size(min = 4, max = 20, message = "El nombre de la Carrera debe tener entre 4 y 20 caracteres")
    private String nombre;

    @Column(name = "car_cant_anios")
    @Min(value = 3, message = "Tiene que ser mayor o igual a 3 años")
    @Max(value = 6, message = "Tiene que ser menor o igual a 6 años")
    @NotNull(message = "La cantidad de años no puede ser nula")
    private Integer cantAnios;

    @Column(name = "car_estado")
    private Boolean estado;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Materia> materias;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Alumno> alumnos;
}

package ar.edu.unju.fi.DTO;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AlumnoDTO {
	
	@NonNull
	@Column(name = "alu_DNI", nullable = false)
	@NotBlank(message = "Debe ingresar el DNI")
	@Size(min=6, max=8, message = "El DNI debe contener entre 6 y 8 caracteres")
	private String dni;
    
	@NonNull
	@Column(name = "alu_Nombre", nullable = false)
	@NotBlank(message = "Debe ingresar el nombre del alumno")
    @Size(min=2,max = 50, message = "El nombre debe contener entre 2 y 50 caracteres")
	private String nombre;
    
	@NonNull
	@Column(name = "Apellido", nullable = false)
	@NotBlank(message = "Debe ingresar el apellido del alumno")
    @Size(min=2,max = 50, message = "El apellido debe contener entre 2 y 50 caracteres")
	private String apellido;
    
	@NonNull
	@Column(name = "Email", nullable = false)
	@NotBlank(message = "Debe ingresar el email")
    @Email(message = "Debe ingresar un email válido")
    private String email;
    
    @NonNull
	@Column(name = "Telefono", nullable = false)
	@NotBlank(message = "Debe ingresar el teléfono")
	@Size(min=10, max=15, message = "El teléfono debe contener entre 10 y 15 caracteres")
    private String telefono;
    
    @NonNull
	@Column(name = "FechaNacimiento", nullable = false)
	@NotNull(message = "Debe ingresar la fecha de nacimiento")
	@Past(message = "La fecha de nacimiento debe estar en el pasado")
    private LocalDate fechaNacimiento;
    
    @NonNull
	@Column(name = "Domicilio", nullable = false)
	@NotBlank(message = "Debe ingresar el domicilio")
	@Size(min=2, max=100, message = "El domicilio debe contener entre 2 y 100 caracteres")
    private String domicilio;
    
    private Long lu;
	private boolean estado;
	private Carrera carrera;
	private List<Materia> materias;
	
}

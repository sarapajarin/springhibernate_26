package init.mappers;

import org.springframework.stereotype.Component;

import init.dtos.EstudianteDto;
import init.model.Alumno;

@Component
public class Mapeador {
	public EstudianteDto alumnoToEstudiante(Alumno alumno) {
		return new EstudianteDto(alumno.getNombre(), alumno.getCurso(), alumno.getEmail(), alumno.getNota());
	}
	public Alumno estudianteToAlumno(EstudianteDto alumno) {
		return new Alumno(alumno.getNombre(), alumno.getCurso(), alumno.getEmail(), alumno.getCalificacion());
	}
}

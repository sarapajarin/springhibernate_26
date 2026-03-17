package init.mapper;

import org.springframework.stereotype.Component;

import init.dtos.AlumnoDto;
import init.model.Alumno;

@Component
public class Mapeador {

	public AlumnoDto alumnoEntityToDto(Alumno alumno) {
		return new AlumnoDto(alumno.getIdAlumno(), alumno.getNombre(), alumno.getCurso(), alumno.getEmail(), alumno.getNota());
	}
	public Alumno alumnoDtoToEntity(AlumnoDto alumno) {
		return new Alumno(alumno.getIdAlumno(), alumno.getNombre(), alumno.getCurso(), alumno.getEmail(), alumno.getNota());
	}
}

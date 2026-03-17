package init.mapper;

import org.springframework.stereotype.Component;

import init.dtos.AlumnoDto;
import init.model.Alumno;

@Component
public class Mapeador {

	public AlumnoDto entityAlumnoToDto(Alumno al) {
		return new AlumnoDto(al.getIdAlumno(),al.getNombre(),al.getCurso(),al.getEmail(),al.getNota());
	}
	
	public Alumno dtoAlumnoToEntity(AlumnoDto al) {
		return new Alumno(al.getIdAlumno(),al.getNombre(),al.getCurso(),al.getEmail(),al.getNota());
	}
}
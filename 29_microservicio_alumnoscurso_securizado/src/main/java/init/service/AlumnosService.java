package init.service;

import java.util.List;

import init.dtos.AlumnoDto;
import init.model.Alumno;

public interface AlumnosService {
	boolean guardar(AlumnoDto alumno);
	List<String>mostrarCursos();
	List<AlumnoDto>mostrarAlumnosCurso(String curso);
	AlumnoDto eliminar(String email);
}

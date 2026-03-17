package init.service;

import java.util.List;

import init.dtos.AlumnoDto;
import init.model.Alumno;

public interface AlumnosService {
	boolean guardar(AlumnoDto alumno);
	List<AlumnoDto> getAllAlumnos();
	List<AlumnoDto> alumnosByCurso(String curso);
	AlumnoDto deleteAlumnoByEmail(String email);
	List<String> getAllCursos();
	AlumnoDto findByEmail(String email);
}
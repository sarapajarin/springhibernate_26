package service;

import java.util.List;

import model.Alumno;

public interface AlumnosService {
	boolean guardar(Alumno alumno);
	List<String>mostrarCursos();
	List<Alumno>mostrarAlumnosCurso(String curso);
}

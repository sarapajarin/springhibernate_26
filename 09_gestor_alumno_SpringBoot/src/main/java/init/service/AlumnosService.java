package init.service;

import java.util.List;

import init.model.Alumno;

public interface AlumnosService {
	boolean guardar(Alumno alumno);
	List<String>mostrarCursos();
	List<Alumno>mostrarAlumnosCurso(String curso);
	void eliminar(int idAlumno);
}

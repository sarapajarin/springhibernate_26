package init.repository;



import java.util.List;

import init.model.Alumno;
//NO ANOTACIONES
public interface AlumnosRepository {
	//hay que hacer metodos pensado en base de datos, en como se haria; hay que hacer metodos de acciones simples.
	//CREAR NUEVO ALUMNO
	void save (Alumno alumno);
	Alumno findByNombreAndCurso(String nombre, String curso);
	//BUSCADOR DE CURSOS
	List<String> findAllCursos();
	//MOSTRAR ALUMNOS CURSO
	List<Alumno> findByCurso (String curso);
	//ELIMINAR ALUMNO
	void removeById(int idAlumno);
	//NOTA MEDIA
	double averageByCurso(String curso);
	
}

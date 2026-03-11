package init.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import init.model.Alumno;
//NO ANOTACIONES
public interface AlumnosRepository extends JpaRepository<Alumno, Integer> {
	//hay que hacer metodos pensado en base de datos, en como se haria; hay que hacer metodos de acciones simples.

	Alumno findFirstByNombreandCurso(String nombre, String curso);
	//BUSCADOR DE CURSOS
	@Query("select distinct(a.curso) from Alumno a")
	List<String> findAllCursos();
	//MOSTRAR ALUMNOS CURSO
	List<Alumno> findByCurso (String curso);
	@Query(value="select avg(nota) from alumnos where curso=?",nativeQuery = true)
	double averageByCurso(String curso);
	Alumno findFirstByEmail(String email);
}

package init.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import init.model.Alumno;

@Repository
public interface AlumnosRepository extends JpaRepository<Alumno, Integer>{
	List<Alumno> findByNombreAndCurso(String nombre,String curso);
	
	List<Alumno> findByCurso(String curso);
	List<Alumno> findAll();
	@Query("SELECT distinct (a.curso) FROM Alumno a ")
	List<String> findCurso();
	Alumno deleteFirstAlumnoByEmail(String email);
	
	Alumno findFirstByEmail(String email);
}
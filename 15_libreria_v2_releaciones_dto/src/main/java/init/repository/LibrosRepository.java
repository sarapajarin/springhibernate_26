package init.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import init.model.Libro;

public interface LibrosRepository extends JpaRepository<Libro, Integer> {
	
	
	List<Libro> findByTemaRelacionadoIdTema(int idTema);
	
	
	//lista de libros por el nombre de la temática
	@Query("select l from Libro l where l.temaRelacionado.tema=?1")
	List<Libro> findByNombreTema(String nombre);
	
}

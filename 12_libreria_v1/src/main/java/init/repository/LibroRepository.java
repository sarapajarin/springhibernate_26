package init.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
	
	List<Libro> findByIdTema(int idTema);
}
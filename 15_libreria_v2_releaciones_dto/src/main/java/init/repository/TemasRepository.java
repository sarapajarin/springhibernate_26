package init.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import init.model.Tema;

public interface TemasRepository extends JpaRepository<Tema, Integer> {

	//método que devuelva el objeto tema al que pertenece un determinado libro,
	//cuyo isbn se proporciona como parámetro
	@Query("select t from Tema t join t.libros l where l.isbn=?1")
	Tema findByIsbn(int isbn);
}

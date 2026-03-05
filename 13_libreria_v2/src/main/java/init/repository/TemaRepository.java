package init.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Integer>{
	
}

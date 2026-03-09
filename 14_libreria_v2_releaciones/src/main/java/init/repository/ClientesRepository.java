package init.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
	boolean existsByUsuario(String usuario);
	
	Cliente findFirstByUsuarioAndPassword(String usuario,String password);
}

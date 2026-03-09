package init.service;

import init.model.Cliente;

public interface ClientesService {
	
	Cliente autenticarUsuario(String usuario, String password);
	boolean guardar(Cliente cliente);
}

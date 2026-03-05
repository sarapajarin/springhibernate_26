package init.service;

import init.model.Cliente;

public interface ClienteService {
	
	Cliente autenticarUsuario(String usuario, String password);
	boolean guardar(Cliente cliente);
}
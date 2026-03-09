package init.service;

import init.dtos.ClienteDto;

public interface ClientesService {
	
	ClienteDto autenticarUsuario(String usuario, String password);
	boolean guardar(ClienteDto cliente);
}

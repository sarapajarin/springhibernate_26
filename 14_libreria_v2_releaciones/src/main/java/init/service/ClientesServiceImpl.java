package init.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.model.Cliente;
import init.repository.ClientesRepository;
@Service
public class ClientesServiceImpl implements ClientesService {
	
	@Autowired
	ClientesRepository clientesRepository;

	

	@Override
	public Cliente autenticarUsuario(String usuario, String password) {
		return clientesRepository.findFirstByUsuarioAndPassword(usuario, password);
	}

	@Override
	public boolean guardar(Cliente cliente) {
		if(!clientesRepository.existsByUsuario(cliente.getUsuario())) {
			clientesRepository.save(cliente);
			return true;
		}
		return false;
	}

}

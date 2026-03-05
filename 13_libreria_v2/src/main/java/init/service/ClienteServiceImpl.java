package init.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.repository.ClienteRepository;
import init.model.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	ClienteRepository clientesRepository;

	

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

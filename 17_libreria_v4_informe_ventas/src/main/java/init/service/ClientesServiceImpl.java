package init.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dtos.ClienteDto;
import init.mappers.Mapeador;
import init.repository.ClientesRepository;
@Service
public class ClientesServiceImpl implements ClientesService {
	
	@Autowired
	ClientesRepository clientesRepository;

	@Autowired
	Mapeador mapeador;
	

	@Override
	public ClienteDto autenticarUsuario(String usuario, String password) {
		return mapeador.clienteEntityToDto(clientesRepository.findFirstByUsuarioAndPassword(usuario, password));
	}

	@Override
	public boolean guardar(ClienteDto cliente) {
		if(!clientesRepository.existsByUsuario(cliente.getUsuario())) {
			clientesRepository.save(mapeador.clienteDtoToEntity(cliente));
			return true;
		}
		return false;
	}

}

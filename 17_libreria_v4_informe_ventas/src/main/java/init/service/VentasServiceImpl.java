package init.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dtos.LibroDto;
import init.mappers.Mapeador;
import init.model.Cliente;
import init.model.Venta;
import init.repository.ClientesRepository;
import init.repository.VentasRepository;
@Service
public class VentasServiceImpl implements VentasService {
	@Autowired
	VentasRepository ventasRepository;
	@Autowired
	ClientesRepository clientesRepository;
	@Autowired
	Mapeador mapeador;

	@Override
	public void nuevaVenta(int idCliente, List<LibroDto> libros) {
		Cliente cliente=clientesRepository.findById(idCliente).orElse(null);
		if(cliente!=null) {
			for(LibroDto l:libros) {
				ventasRepository.save(new Venta(cliente,mapeador.libroDtoToEntity(l),LocalDateTime.now()));
			}
		}

	}

}

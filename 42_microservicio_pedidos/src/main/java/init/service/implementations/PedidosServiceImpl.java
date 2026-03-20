package init.service.implementations;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import init.dtos.ProductoDto;
import init.model.Pedido;
import init.repository.PedidosRepository;
import init.service.interfaces.PedidosService;
@Service
public class PedidosServiceImpl implements PedidosService {
	@Autowired
	RestClient restClient;
	@Autowired
	PedidosRepository pedidosDao;
	@Value("${remote.url}")
	String urlBase;
	@Value("${remote.user}")
	String user;
	@Value("${remote.password}")
	String pass;
	@Override
	public List<Pedido> pedidos() {
		return pedidosDao.findAll();
	}

	@Override
	public boolean registrarPedido(Pedido pedido) {
		
		pedido.setFechaPedido(new Date());
		try {
			//obtenemos precio producto llamado al recurso remoto
			double precio=restClient.get()
					.uri(urlBase+"productos/"+pedido.getCodigoProducto())
					.header("Authorization","Basic "+ getBase64())
					.retrieve()
					.body(ProductoDto.class)
					.getPrecioUnitario();
			pedido.setTotal(pedido.getUnidades()*precio);
			//actualizamos stock llamado a recurso remoto
			restClient.put()
				.uri(urlBase+"productos/"+pedido.getCodigoProducto()+"/"+pedido.getUnidades())
				.header("Authorization","Basic "+ getBase64())
				.retrieve()
				.toBodilessEntity();
			//salvamos pedido
			pedidosDao.save(pedido);
			return true;
		}catch(HttpClientErrorException ex) {
			ex.printStackTrace();
			return false;
		}
		

	}
	private String getBase64() {
		String texto=user+":"+pass;
		return Base64.getEncoder().encodeToString(texto.getBytes());
	}

}

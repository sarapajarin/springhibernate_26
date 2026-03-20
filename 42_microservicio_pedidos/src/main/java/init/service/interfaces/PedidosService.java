package init.service.interfaces;

import java.util.List;

import init.model.Pedido;

public interface PedidosService {
	List<Pedido> pedidos();
	boolean registrarPedido(Pedido pedido);
}

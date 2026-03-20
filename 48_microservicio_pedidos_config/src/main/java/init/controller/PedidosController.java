package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.model.Pedido;
import init.service.interfaces.PedidosService;
@CrossOrigin("*")
@RestController
public class PedidosController {
	@Autowired
	PedidosService pedidosService;
	@GetMapping(value="pedidos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pedido>> pedidos(){
		return new ResponseEntity<>(pedidosService.pedidos(), HttpStatus.OK);
	}
	@PostMapping(value="pedidos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> altaPedido(@RequestBody Pedido pedido){
		if(pedidosService.registrarPedido(pedido)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}

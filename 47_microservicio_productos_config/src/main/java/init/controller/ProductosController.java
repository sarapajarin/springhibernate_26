package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import init.dtos.ProductoDto;
import init.service.interfaces.ProductosService;
@CrossOrigin("*")
@RestController
public class ProductosController {
	@Autowired
	ProductosService productosService;
	@GetMapping(value="productos", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductoDto>> productos(){
		return new ResponseEntity<>(productosService.productos(),HttpStatus.OK);
	}
	@GetMapping(value="productos/{codigo}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductoDto> buscarPorCodigo(@PathVariable("codigo") int codigoProducto) {
		return new ResponseEntity<>(productosService.productoCodigo(codigoProducto),HttpStatus.OK);
	}
	@PutMapping(value="productos/{codigo}/{unidades}")
	public ResponseEntity<ProductoDto> actualizarStock(@PathVariable("codigo") int codigoProducto,@PathVariable("unidades") int unidades){
		ProductoDto producto=productosService.actualizarStock(codigoProducto, unidades);
		if(producto!=null) {
			return ResponseEntity.ok(producto);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
}






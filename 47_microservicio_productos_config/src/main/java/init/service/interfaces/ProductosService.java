package init.service.interfaces;

import java.util.List;

import init.dtos.ProductoDto;

public interface ProductosService {
	List<ProductoDto> productos();
	ProductoDto productoCodigo(int codigoProducto);
	boolean nuevoProducto(ProductoDto producto);
	ProductoDto actualizarStock(int codigoProducto, int unidades);
}

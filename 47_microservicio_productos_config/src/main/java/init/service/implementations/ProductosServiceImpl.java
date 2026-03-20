package init.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dtos.ProductoDto;
import init.mappers.Mapeador;
import init.model.Producto;
import init.repository.ProductosRepository;
import init.service.interfaces.ProductosService;
@Service
public class ProductosServiceImpl implements ProductosService {
	@Autowired
	ProductosRepository productosDao;
	@Autowired
	Mapeador mapeador;
	@Override
	public List<ProductoDto> productos() {
		return productosDao.findAll()
				.stream().map(mapeador::productoEntityToDto)
				.toList();
	}

	

	@Override
	public ProductoDto actualizarStock(int codigoProducto, int unidades) {
		Optional<Producto> prodOp=productosDao.findById(codigoProducto);
		if(prodOp.isPresent()&&prodOp.get().getStock()>unidades) {
			Producto p=prodOp.get();
			p.setStock(p.getStock()-unidades);
			return mapeador.productoEntityToDto(productosDao.save(p));
		}
		return null;

	}

	@Override
	public ProductoDto productoCodigo(int codigoProducto) {
		Optional<Producto> prodOp=productosDao.findById(codigoProducto);
		
		return prodOp.map(p->mapeador.productoEntityToDto(p)).orElse(new ProductoDto());
	}

	@Override
	public boolean nuevoProducto(ProductoDto producto) {
		Optional<Producto> prodOp=productosDao.findById(producto.getCodigoProducto());
		if(prodOp.isEmpty()) {
			productosDao.save(mapeador.productoDtoToEntity(producto));
			return true;
		}
		return false;
	}

}

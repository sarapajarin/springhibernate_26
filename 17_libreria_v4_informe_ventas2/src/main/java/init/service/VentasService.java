package init.service;

import java.time.LocalDateTime;
import java.util.List;

import init.dtos.LibroDto;
import init.dtos.VentaDto;

public interface VentasService {
	public void nuevaVenta(int idCliente,List<LibroDto> libros);
	public List<VentaDto> ventasPorFechas(LocalDateTime f1, LocalDateTime f2);
}
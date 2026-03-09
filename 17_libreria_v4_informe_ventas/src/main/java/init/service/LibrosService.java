package init.service;

import java.util.List;

import init.dtos.LibroDto;
import init.dtos.TemaDto;

public interface LibrosService {
	List<TemaDto> temas();
	
	List<LibroDto> librosTema(int idTema);
	
	LibroDto buscarLibroPorIsbn(int isbn);
}

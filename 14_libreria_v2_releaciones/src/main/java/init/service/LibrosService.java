package init.service;

import java.util.List;

import init.model.Libro;
import init.model.Tema;

public interface LibrosService {
	List<Tema> temas();
	
	List<Libro> librosTema(int idTema);
	
	Libro buscarLibroPorIsbn(int isbn);
}

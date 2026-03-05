package init.service;

import java.util.List;

import org.springframework.stereotype.Service;

import init.model.Libro;
import init.model.Tema;


public interface LibroService {
	
	List<Libro> librosTema(int idTema);
	List<Tema> temas();
	Libro buscarLibroPorIsbn(int isbn);
}

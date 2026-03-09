package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.model.Libro;
import init.model.Tema;
import init.repository.LibrosRepository;
import init.repository.TemasRepository;
@Service
public class LibrosServiceImpl implements LibrosService {
	@Autowired
	TemasRepository temasRepository;
	@Autowired
	LibrosRepository librosRepository;
	@Override
	public List<Tema> temas() {
		return temasRepository.findAll();
	}
	@Override
	public List<Libro> librosTema(int idTema) {
		if(idTema==0) {
			return librosRepository.findAll();
		}
		return librosRepository.findByTemaRelacionadoIdTema(idTema);
	}
	@Override
	public Libro buscarLibroPorIsbn(int isbn) {
		return librosRepository.findById(isbn).orElse(null);
	}
}

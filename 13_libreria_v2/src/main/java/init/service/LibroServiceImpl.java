package init.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.model.Libro;
import init.model.Tema;
import init.repository.LibroRepository;
import init.repository.TemaRepository;
@Service
public class LibroServiceImpl implements LibroService {
	@Autowired
	TemaRepository temasRepository;
	@Autowired
	LibroRepository librosRepository;
	
	@Override
	public List<Tema> temas() {
		return temasRepository.findAll();
	}
	
	@Override
	public List<Libro> librosTema(int idTema) {
		if(idTema==0) {
			return librosRepository.findAll();
		}
		return librosRepository.findByIdTema(idTema);
	}
	
	@Override
	public Libro buscarLibroPorIsbn(int isbn) {
		
		return librosRepository.findById(isbn).orElse(null);
	}
	
	
}
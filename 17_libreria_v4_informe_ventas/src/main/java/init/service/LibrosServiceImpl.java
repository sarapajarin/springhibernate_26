package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dtos.LibroDto;
import init.dtos.TemaDto;
import init.mappers.Mapeador;
import init.model.Libro;
import init.repository.LibrosRepository;
import init.repository.TemasRepository;
@Service
public class LibrosServiceImpl implements LibrosService {
	@Autowired
	TemasRepository temasRepository;
	@Autowired
	LibrosRepository librosRepository;
	
	@Autowired
	Mapeador mapeador;
	
	
	@Override
	public List<TemaDto> temas() {
		return temasRepository.findAll()  //List<Tema>
				.stream() //Stream<Tema>
				.map(t->mapeador.temaEntityToDto(t)) //Stream<TemaDto>
				.toList();
				
	}
	@Override
	public List<LibroDto> librosTema(int idTema) {
		if(idTema==0) {
			return librosRepository.findAll()
					.stream()
					.map(l->mapeador.libroEntityToDto(l))
					.toList();
		}
		return librosRepository.findByTemaRelacionadoIdTema(idTema)
				.stream()
				.map(l->mapeador.libroEntityToDto(l))
				.toList();
	}
	@Override
	public LibroDto buscarLibroPorIsbn(int isbn) {
		return librosRepository.findById(isbn) //Optional<Libro>
				.map(l->mapeador.libroEntityToDto(l))//Optional<LibroDto>
				.orElse(null);			
	}
}

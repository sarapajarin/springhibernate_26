package init.mappers;

import org.springframework.stereotype.Component;

import init.dtos.ClienteDto;
import init.dtos.LibroDto;
import init.dtos.TemaDto;
import init.model.Cliente;
import init.model.Libro;
import init.model.Tema;

@Component
public class Mapeador {
	
	public Cliente clienteDtoToEntity(ClienteDto dto) {
		return new Cliente(dto.getUsuario(),dto.getPassword(),dto.getEmail(),dto.getTelefono());
	}
	
	public ClienteDto clienteEntityToDto(Cliente cliente) {
		return new ClienteDto(cliente.getUsuario(),cliente.getPassword(),cliente.getEmail(),cliente.getTelefono());
	}
	
	public Tema temaDtoToEntity(TemaDto tema) {
		return new Tema(tema.getIdTema(),tema.getTema());
	}
	
	public TemaDto temaEntityToDto(Tema tema) {
		return new TemaDto(tema.getIdTema(),tema.getTema());
	}
	//solo tiene sentido si se van a enviar objetos libro del exterior hacia la aplicación
	public Libro libroDtoToEntity(LibroDto libro) {
		return null;
	}
	
	public LibroDto libroEntityToDto(Libro libro) {
		return new LibroDto(libro.getIsbn(),libro.getTitulo(),libro.getAutor(),libro.getPaginas(),libro.getPrecio(),libro.getTemaRelacionado().getTema());
	}
}
